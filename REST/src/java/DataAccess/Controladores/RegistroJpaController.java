/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.Controladores;

import DataAccess.Controladores.exceptions.NonexistentEntityException;
import DataAccess.Controladores.exceptions.PreexistingEntityException;
import DataAccess.Controladores.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DataAccess.entidades.Personal;
import DataAccess.entidades.Registro;
import DataAccess.entidades.RegistroPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Victor Javier
 */
public class RegistroJpaController implements Serializable {

    public RegistroJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Registro registro) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (registro.getRegistroPK() == null) {
            registro.setRegistroPK(new RegistroPK());
        }
        registro.getRegistroPK().setPersonalUsuariosUsuId(registro.getPersonal().getPersonalPK().getUsuariosUsuId());
        registro.getRegistroPK().setPersonalPerId(registro.getPersonal().getPersonalPK().getPerId());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Personal personal = registro.getPersonal();
            if (personal != null) {
                personal = em.getReference(personal.getClass(), personal.getPersonalPK());
                registro.setPersonal(personal);
            }
            em.persist(registro);
            if (personal != null) {
                personal.getRegistroCollection().add(registro);
                personal = em.merge(personal);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findRegistro(registro.getRegistroPK()) != null) {
                throw new PreexistingEntityException("Registro " + registro + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Registro registro) throws NonexistentEntityException, RollbackFailureException, Exception {
        registro.getRegistroPK().setPersonalUsuariosUsuId(registro.getPersonal().getPersonalPK().getUsuariosUsuId());
        registro.getRegistroPK().setPersonalPerId(registro.getPersonal().getPersonalPK().getPerId());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Registro persistentRegistro = em.find(Registro.class, registro.getRegistroPK());
            Personal personalOld = persistentRegistro.getPersonal();
            Personal personalNew = registro.getPersonal();
            if (personalNew != null) {
                personalNew = em.getReference(personalNew.getClass(), personalNew.getPersonalPK());
                registro.setPersonal(personalNew);
            }
            registro = em.merge(registro);
            if (personalOld != null && !personalOld.equals(personalNew)) {
                personalOld.getRegistroCollection().remove(registro);
                personalOld = em.merge(personalOld);
            }
            if (personalNew != null && !personalNew.equals(personalOld)) {
                personalNew.getRegistroCollection().add(registro);
                personalNew = em.merge(personalNew);
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
                RegistroPK id = registro.getRegistroPK();
                if (findRegistro(id) == null) {
                    throw new NonexistentEntityException("The registro with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(RegistroPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Registro registro;
            try {
                registro = em.getReference(Registro.class, id);
                registro.getRegistroPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The registro with id " + id + " no longer exists.", enfe);
            }
            Personal personal = registro.getPersonal();
            if (personal != null) {
                personal.getRegistroCollection().remove(registro);
                personal = em.merge(personal);
            }
            em.remove(registro);
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

    public List<Registro> findRegistroEntities() {
        return findRegistroEntities(true, -1, -1);
    }

    public List<Registro> findRegistroEntities(int maxResults, int firstResult) {
        return findRegistroEntities(false, maxResults, firstResult);
    }

    private List<Registro> findRegistroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Registro.class));
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

    public Registro findRegistro(RegistroPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Registro.class, id);
        } finally {
            em.close();
        }
    }

    public int getRegistroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Registro> rt = cq.from(Registro.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
