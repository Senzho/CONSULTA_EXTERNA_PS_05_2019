/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.controladores;

import DataAccess.controladores.exceptions.NonexistentEntityException;
import DataAccess.controladores.exceptions.RollbackFailureException;
import DataAccess.entidades.Personal;
import DataAccess.entidades.Sesiones;
import WS.Hasher;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Victor Javier
 */
public class SesionesJpaController implements Serializable {

    public SesionesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Personal personal, Sesiones sesion) throws RollbackFailureException, Exception {
        EntityManager em = getEntityManager();
        sesion.setToken(Hasher.hash("ceht" + personal.getPerNombres() + personal.getPerApellidos() + personal.getPrRfc() + new SimpleDateFormat("yyyy-MM-dd").format(new Date())));
        try {
            em.getTransaction().begin();
            em.persist(sesion);
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

    public void edit(Sesiones sesiones) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            sesiones = em.merge(sesiones);
            em.getTransaction().commit();
        } catch (Exception ex) {
            try {
                em.getTransaction().rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = sesiones.getSesId();
                if (findSesiones(id) == null) {
                    throw new NonexistentEntityException("The sesiones with id " + id + " no longer exists.");
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
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Sesiones sesiones;
            try {
                sesiones = em.getReference(Sesiones.class, id);
                sesiones.getSesId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sesiones with id " + id + " no longer exists.", enfe);
            }
            em.remove(sesiones);
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

    public List<Sesiones> findSesionesEntities() {
        return findSesionesEntities(true, -1, -1);
    }

    public List<Sesiones> findSesionesEntities(int maxResults, int firstResult) {
        return findSesionesEntities(false, maxResults, firstResult);
    }

    private List<Sesiones> findSesionesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Sesiones.class));
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

    public Sesiones findSesiones(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Sesiones.class, id);
        } finally {
            em.close();
        }
    }
    
    public Sesiones obtenerPorToken(String token) {
        Sesiones sesion;
        EntityManager em = getEntityManager();
        try {
            Query consulta = em.createNamedQuery("Sesiones.findByToken");
            consulta.setParameter("token", token);
            sesion = (Sesiones) consulta.getSingleResult();
        } finally {
            em.close();
        }
        return sesion;
    }

    public int getSesionesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Sesiones> rt = cq.from(Sesiones.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
