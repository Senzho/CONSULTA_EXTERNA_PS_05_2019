/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.controladores;

import DataAccess.controladores.exceptions.IllegalOrphanException;
import DataAccess.controladores.exceptions.NonexistentEntityException;
import DataAccess.controladores.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DataAccess.entidades.Citas;
import java.util.ArrayList;
import java.util.Collection;
import DataAccess.entidades.Consultas;
import DataAccess.entidades.Pacientes;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Victor Javier
 */
public class PacientesJpaController implements Serializable {

    public PacientesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pacientes pacientes) throws RollbackFailureException, Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(pacientes);
            em.getTransaction().commit();
        } catch (Exception ex) {
            try {
                em.getTransaction().rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pacientes pacientes) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            pacientes = em.merge(pacientes);
            em.getTransaction().commit();
        } catch (Exception ex) {
            try {
                em.getTransaction().rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pacientes.getPacNumSeguro();
                if (findPacientes(id) == null) {
                    throw new NonexistentEntityException("The pacientes with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Pacientes pacientes;
            try {
                pacientes = em.getReference(Pacientes.class, id);
                pacientes.getPacNumSeguro();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pacientes with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Citas> citasCollectionOrphanCheck = pacientes.getCitasCollection();
            for (Citas citasCollectionOrphanCheckCitas : citasCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pacientes (" + pacientes + ") cannot be destroyed since the Citas " + citasCollectionOrphanCheckCitas + " in its citasCollection field has a non-nullable citNumSeguroPaciente field.");
            }
            Collection<Consultas> consultasCollectionOrphanCheck = pacientes.getConsultasCollection();
            for (Consultas consultasCollectionOrphanCheckConsultas : consultasCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pacientes (" + pacientes + ") cannot be destroyed since the Consultas " + consultasCollectionOrphanCheckConsultas + " in its consultasCollection field has a non-nullable conSeguroPaciente field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(pacientes);
            em.getTransaction().commit();
        } catch (Exception ex) {
            try {
                em.getTransaction().rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pacientes> findPacientesEntities() {
        return findPacientesEntities(true, -1, -1);
    }

    public List<Pacientes> findPacientesEntities(int maxResults, int firstResult) {
        return findPacientesEntities(false, maxResults, firstResult);
    }

    private List<Pacientes> findPacientesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pacientes.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Pacientes findPacientes(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pacientes.class, id);
        } finally {
            em.close();
        }
    }
    
    public Pacientes obtenerPorNumeroSeguro(int numeroSeguro) {
        Pacientes paciente;
        EntityManager em = getEntityManager();
        try {
            Query consulta = em.createNamedQuery("Pacientes.findByPacNumSeguro");
            consulta.setParameter("pacNumSeguro", numeroSeguro);
            paciente = (Pacientes) consulta.getSingleResult();
        } finally {
            em.close();
        }
        return paciente;
    }

    public int getPacientesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pacientes> rt = cq.from(Pacientes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
