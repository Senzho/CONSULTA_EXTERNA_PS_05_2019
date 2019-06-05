/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.Controladores;

import DataAccess.Controladores.exceptions.IllegalOrphanException;
import DataAccess.Controladores.exceptions.NonexistentEntityException;
import DataAccess.Controladores.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DataAccess.entidades.Personal;
import DataAccess.entidades.Usuario;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Victor Javier
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) throws RollbackFailureException, Exception {
        if (usuario.getPersonalCollection() == null) {
            usuario.setPersonalCollection(new ArrayList<Personal>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Personal> attachedPersonalCollection = new ArrayList<Personal>();
            for (Personal personalCollectionPersonalToAttach : usuario.getPersonalCollection()) {
                personalCollectionPersonalToAttach = em.getReference(personalCollectionPersonalToAttach.getClass(), personalCollectionPersonalToAttach.getPersonalPK());
                attachedPersonalCollection.add(personalCollectionPersonalToAttach);
            }
            usuario.setPersonalCollection(attachedPersonalCollection);
            em.persist(usuario);
            for (Personal personalCollectionPersonal : usuario.getPersonalCollection()) {
                Usuario oldUsuarioOfPersonalCollectionPersonal = personalCollectionPersonal.getUsuario();
                personalCollectionPersonal.setUsuario(usuario);
                personalCollectionPersonal = em.merge(personalCollectionPersonal);
                if (oldUsuarioOfPersonalCollectionPersonal != null) {
                    oldUsuarioOfPersonalCollectionPersonal.getPersonalCollection().remove(personalCollectionPersonal);
                    oldUsuarioOfPersonalCollectionPersonal = em.merge(oldUsuarioOfPersonalCollectionPersonal);
                }
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

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getUsuId());
            Collection<Personal> personalCollectionOld = persistentUsuario.getPersonalCollection();
            Collection<Personal> personalCollectionNew = usuario.getPersonalCollection();
            List<String> illegalOrphanMessages = null;
            for (Personal personalCollectionOldPersonal : personalCollectionOld) {
                if (!personalCollectionNew.contains(personalCollectionOldPersonal)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Personal " + personalCollectionOldPersonal + " since its usuario field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Personal> attachedPersonalCollectionNew = new ArrayList<Personal>();
            for (Personal personalCollectionNewPersonalToAttach : personalCollectionNew) {
                personalCollectionNewPersonalToAttach = em.getReference(personalCollectionNewPersonalToAttach.getClass(), personalCollectionNewPersonalToAttach.getPersonalPK());
                attachedPersonalCollectionNew.add(personalCollectionNewPersonalToAttach);
            }
            personalCollectionNew = attachedPersonalCollectionNew;
            usuario.setPersonalCollection(personalCollectionNew);
            usuario = em.merge(usuario);
            for (Personal personalCollectionNewPersonal : personalCollectionNew) {
                if (!personalCollectionOld.contains(personalCollectionNewPersonal)) {
                    Usuario oldUsuarioOfPersonalCollectionNewPersonal = personalCollectionNewPersonal.getUsuario();
                    personalCollectionNewPersonal.setUsuario(usuario);
                    personalCollectionNewPersonal = em.merge(personalCollectionNewPersonal);
                    if (oldUsuarioOfPersonalCollectionNewPersonal != null && !oldUsuarioOfPersonalCollectionNewPersonal.equals(usuario)) {
                        oldUsuarioOfPersonalCollectionNewPersonal.getPersonalCollection().remove(personalCollectionNewPersonal);
                        oldUsuarioOfPersonalCollectionNewPersonal = em.merge(oldUsuarioOfPersonalCollectionNewPersonal);
                    }
                }
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
                Integer id = usuario.getUsuId();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getUsuId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Personal> personalCollectionOrphanCheck = usuario.getPersonalCollection();
            for (Personal personalCollectionOrphanCheckPersonal : personalCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Personal " + personalCollectionOrphanCheckPersonal + " in its personalCollection field has a non-nullable usuario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(usuario);
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

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
