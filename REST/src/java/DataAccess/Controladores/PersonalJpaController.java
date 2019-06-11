/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.controladores;

import DataAccess.controladores.exceptions.IllegalOrphanException;
import DataAccess.controladores.exceptions.NonexistentEntityException;
import DataAccess.controladores.exceptions.PreexistingEntityException;
import DataAccess.controladores.exceptions.RollbackFailureException;
import DataAccess.entidades.Personal;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DataAccess.entidades.Usuarios;
import DataAccess.entidades.Registros;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Victor Javier
 */
public class PersonalJpaController implements Serializable {

    public PersonalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Personal personal) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(personal);
            em.getTransaction().commit();
        } catch (Exception ex) {
            try {
                em.getTransaction().rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findPersonal(personal.getPrRfc()) != null) {
                throw new PreexistingEntityException("Personal " + personal + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public boolean edit(Personal personal) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        boolean editado = false;
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Query consulta = em.createNamedQuery("Personal.actualizar");
            consulta.setParameter("prRfc", personal.getPrRfc());
            consulta.setParameter("perApellidos", personal.getPerApellidos());
            consulta.setParameter("perEstado", personal.getPerEstado());
            consulta.setParameter("perNombres", personal.getPerNombres());
            consulta.setParameter("perNumPersonal", personal.getPerNumPersonal());
            consulta.setParameter("perNumTelefono", personal.getPerNumTelefono());
            consulta.setParameter("perSexo", personal.getPerSexo());
            consulta.setParameter("perTurno", personal.getPerTurno());
            consulta.setParameter("perFechaNac", personal.getPerFechaNac());
            editado = consulta.executeUpdate() == 1;
            em.getTransaction().commit();
        } catch (Exception ex) {
            try {
                em.getTransaction().rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = personal.getPrRfc();
                if (findPersonal(id) == null) {
                    throw new NonexistentEntityException("The personal with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            em.close();
        }
        return editado;
    }
    
    public boolean cambiarEstado(Personal personal) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        boolean cambiado = false;
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Query consulta = em.createNamedQuery("Personal.estado");
            consulta.setParameter("perEstado", 0);
            consulta.setParameter("prRfc", personal.getPrRfc());
            cambiado = consulta.executeUpdate() == 1;
            em.getTransaction().commit();
        } catch (Exception ex) {
            try {
                em.getTransaction().rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = personal.getPrRfc();
                if (findPersonal(id) == null) {
                    throw new NonexistentEntityException("The personal with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            em.close();
        }
        return cambiado;
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Personal personal;
            try {
                personal = em.getReference(Personal.class, id);
                personal.getPrRfc();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The personal with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Registros> registrosCollectionOrphanCheck = personal.getRegistrosCollection();
            for (Registros registrosCollectionOrphanCheckRegistros : registrosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Personal (" + personal + ") cannot be destroyed since the Registros " + registrosCollectionOrphanCheckRegistros + " in its registrosCollection field has a non-nullable personalUsuariosUsuId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(personal);
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

    public List<Personal> findPersonalEntities() {
        return findPersonalEntities(true, -1, -1);
    }

    public List<Personal> findPersonalEntities(int maxResults, int firstResult) {
        return findPersonalEntities(false, maxResults, firstResult);
    }

    private List<Personal> findPersonalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Personal.class));
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

    public Personal findPersonal(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Personal.class, id);
        } finally {
            em.close();
        }
    }
    
    public Personal obtenerPorRfc(String rfc) {
        Personal personal;
        EntityManager em = getEntityManager();
        try {
            Query consulta = em.createNamedQuery("Personal.findByPrRfc");
            consulta.setParameter("prRfc", rfc);
            personal = (Personal) consulta.getSingleResult();
            personal.setUsuariosUsuId(null);
            personal.setRegistrosCollection(null);
            personal.setCitasCollection(null);
        } finally {
            em.close();
        }
        return personal;
    }
    
    public Personal obtenerPorIdUsuario(Usuarios usuario) {
        Personal personal;
        EntityManager em = getEntityManager();
        try {
            Query consulta = em.createNamedQuery("Personal.findByUsuariosUsuId");
            consulta.setParameter("usuId", usuario.getUsuId());
            personal = (Personal) consulta.getSingleResult();
            personal.setUsuariosUsuId(null);
            personal.setRegistrosCollection(null);
            personal.setCitasCollection(null);
        } finally {
            em.close();
        }
        return personal;
    }
    
    public List<Personal> obtenerPorRol(String rol) {
        List<Personal> personales;
        EntityManager em = getEntityManager();
        try {
            Query consulta = em.createNamedQuery("Personal.findByUsuRol");
            consulta.setParameter("usuRol", rol);
            personales = consulta.getResultList();
            personales.forEach((personal) -> {
                personal.setUsuariosUsuId(null);
                personal.setRegistrosCollection(null);
                personal.setCitasCollection(null);
            });
        } finally {
            em.close();
        }
        return personales;
    }

    public int getPersonalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Personal> rt = cq.from(Personal.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
