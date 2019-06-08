/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.controladores;

import DataAccess.controladores.exceptions.NonexistentEntityException;
import DataAccess.controladores.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DataAccess.entidades.Personal;
import DataAccess.entidades.Registros;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

/**
 *
 * @author Victor Javier
 */
public class RegistrosJpaController implements Serializable {

    public RegistrosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Registros registros) throws RollbackFailureException, Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(registros);
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

    public void edit(Registros registros) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            registros = em.merge(registros);
            em.getTransaction().commit();
        } catch (Exception ex) {
            try {
                em.getTransaction().rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = registros.getRegId();
                if (findRegistros(id) == null) {
                    throw new NonexistentEntityException("The registros with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public void registrarSalida(Registros registros) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Query consulta = em.createNamedQuery("Registros.salida");
            consulta.setParameter("regHoraSalida", registros.getRegHoraSalida());
            consulta.setParameter("regId", registros.getRegId());
            consulta.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception ex) {
            try {
                em.getTransaction().rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = registros.getRegId();
                if (findRegistros(id) == null) {
                    throw new NonexistentEntityException("The registros with id " + id + " no longer exists.");
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
            Registros registros;
            try {
                registros = em.getReference(Registros.class, id);
                registros.getRegId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The registros with id " + id + " no longer exists.", enfe);
            }
            Personal personalPrRfc = registros.getPersonalPrRfc();
            if (personalPrRfc != null) {
                personalPrRfc.getRegistrosCollection().remove(registros);
                personalPrRfc = em.merge(personalPrRfc);
            }
            em.remove(registros);
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

    public List<Registros> findRegistrosEntities() {
        return findRegistrosEntities(true, -1, -1);
    }

    public List<Registros> findRegistrosEntities(int maxResults, int firstResult) {
        return findRegistrosEntities(false, maxResults, firstResult);
    }

    private List<Registros> findRegistrosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Registros.class));
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

    public Registros findRegistros(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Registros.class, id);
        } finally {
            em.close();
        }
    }
    
    public Registros obtenerSalidaNula(String rfc) {
        Registros registros;
        EntityManager em = getEntityManager();
        try {
            Query consulta = em.createNamedQuery("Registros.findBySalidaNula");
            consulta.setParameter("prRfc", rfc);
            registros = (Registros) consulta.getSingleResult();
        } finally {
            em.close();
        }
        return registros;
    }
    
    public boolean existeSalidaNula(String rfc) {
        boolean existe = true;
        EntityManager em = getEntityManager();
        try {
            Query consulta = em.createNamedQuery("Registros.findBySalidaNula");
            consulta.setParameter("prRfc", rfc);
            consulta.getSingleResult();
        } catch(NoResultException exception) {
            existe = false;
        } finally {
            em.close();
        }
        return existe;
    }

    public int getRegistrosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Registros> rt = cq.from(Registros.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
