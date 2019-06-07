/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.controladores;

import DataAccess.controladores.exceptions.NonexistentEntityException;
import DataAccess.controladores.exceptions.RollbackFailureException;
import DataAccess.entidades.Citas;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DataAccess.entidades.Pacientes;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Victor Javier
 */
public class CitasJpaController implements Serializable {

    public CitasJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Citas citas) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Pacientes citNumSeguroPaciente = citas.getCitNumSeguroPaciente();
            if (citNumSeguroPaciente != null) {
                citNumSeguroPaciente = em.getReference(citNumSeguroPaciente.getClass(), citNumSeguroPaciente.getPacNumSeguro());
                citas.setCitNumSeguroPaciente(citNumSeguroPaciente);
            }
            em.persist(citas);
            if (citNumSeguroPaciente != null) {
                citNumSeguroPaciente.getCitasCollection().add(citas);
                citNumSeguroPaciente = em.merge(citNumSeguroPaciente);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
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

    public void edit(Citas citas) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Citas persistentCitas = em.find(Citas.class, citas.getCitId());
            Pacientes citNumSeguroPacienteOld = persistentCitas.getCitNumSeguroPaciente();
            Pacientes citNumSeguroPacienteNew = citas.getCitNumSeguroPaciente();
            if (citNumSeguroPacienteNew != null) {
                citNumSeguroPacienteNew = em.getReference(citNumSeguroPacienteNew.getClass(), citNumSeguroPacienteNew.getPacNumSeguro());
                citas.setCitNumSeguroPaciente(citNumSeguroPacienteNew);
            }
            citas = em.merge(citas);
            if (citNumSeguroPacienteOld != null && !citNumSeguroPacienteOld.equals(citNumSeguroPacienteNew)) {
                citNumSeguroPacienteOld.getCitasCollection().remove(citas);
                citNumSeguroPacienteOld = em.merge(citNumSeguroPacienteOld);
            }
            if (citNumSeguroPacienteNew != null && !citNumSeguroPacienteNew.equals(citNumSeguroPacienteOld)) {
                citNumSeguroPacienteNew.getCitasCollection().add(citas);
                citNumSeguroPacienteNew = em.merge(citNumSeguroPacienteNew);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = citas.getCitId();
                if (findCitas(id) == null) {
                    throw new NonexistentEntityException("The citas with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Citas citas;
            try {
                citas = em.getReference(Citas.class, id);
                citas.getCitId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The citas with id " + id + " no longer exists.", enfe);
            }
            Pacientes citNumSeguroPaciente = citas.getCitNumSeguroPaciente();
            if (citNumSeguroPaciente != null) {
                citNumSeguroPaciente.getCitasCollection().remove(citas);
                citNumSeguroPaciente = em.merge(citNumSeguroPaciente);
            }
            em.remove(citas);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
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

    public List<Citas> findCitasEntities() {
        return findCitasEntities(true, -1, -1);
    }

    public List<Citas> findCitasEntities(int maxResults, int firstResult) {
        return findCitasEntities(false, maxResults, firstResult);
    }

    private List<Citas> findCitasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Citas.class));
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

    public Citas findCitas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Citas.class, id);
        } finally {
            em.close();
        }
    }

    public int getCitasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Citas> rt = cq.from(Citas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
