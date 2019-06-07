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
import javax.transaction.UserTransaction;

/**
 *
 * @author Victor Javier
 */
public class PersonalJpaController implements Serializable {

    public PersonalJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Personal personal) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (personal.getRegistrosCollection() == null) {
            personal.setRegistrosCollection(new ArrayList<Registros>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Usuarios usuariosUsuId = personal.getUsuariosUsuId();
            if (usuariosUsuId != null) {
                usuariosUsuId = em.getReference(usuariosUsuId.getClass(), usuariosUsuId.getUsuId());
                personal.setUsuariosUsuId(usuariosUsuId);
            }
            Collection<Registros> attachedRegistrosCollection = new ArrayList<Registros>();
            for (Registros registrosCollectionRegistrosToAttach : personal.getRegistrosCollection()) {
                registrosCollectionRegistrosToAttach = em.getReference(registrosCollectionRegistrosToAttach.getClass(), registrosCollectionRegistrosToAttach.getRegId());
                attachedRegistrosCollection.add(registrosCollectionRegistrosToAttach);
            }
            personal.setRegistrosCollection(attachedRegistrosCollection);
            em.persist(personal);
            if (usuariosUsuId != null) {
                usuariosUsuId.getPersonalCollection().add(personal);
                usuariosUsuId = em.merge(usuariosUsuId);
            }
            for (Registros registrosCollectionRegistros : personal.getRegistrosCollection()) {
                Personal oldPersonalUsuariosUsuIdOfRegistrosCollectionRegistros = registrosCollectionRegistros.getPersonalUsuariosUsuId();
                registrosCollectionRegistros.setPersonalUsuariosUsuId(personal);
                registrosCollectionRegistros = em.merge(registrosCollectionRegistros);
                if (oldPersonalUsuariosUsuIdOfRegistrosCollectionRegistros != null) {
                    oldPersonalUsuariosUsuIdOfRegistrosCollectionRegistros.getRegistrosCollection().remove(registrosCollectionRegistros);
                    oldPersonalUsuariosUsuIdOfRegistrosCollectionRegistros = em.merge(oldPersonalUsuariosUsuIdOfRegistrosCollectionRegistros);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
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

    public void edit(Personal personal) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Personal persistentPersonal = em.find(Personal.class, personal.getPrRfc());
            Usuarios usuariosUsuIdOld = persistentPersonal.getUsuariosUsuId();
            Usuarios usuariosUsuIdNew = personal.getUsuariosUsuId();
            Collection<Registros> registrosCollectionOld = persistentPersonal.getRegistrosCollection();
            Collection<Registros> registrosCollectionNew = personal.getRegistrosCollection();
            List<String> illegalOrphanMessages = null;
            for (Registros registrosCollectionOldRegistros : registrosCollectionOld) {
                if (!registrosCollectionNew.contains(registrosCollectionOldRegistros)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Registros " + registrosCollectionOldRegistros + " since its personalUsuariosUsuId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (usuariosUsuIdNew != null) {
                usuariosUsuIdNew = em.getReference(usuariosUsuIdNew.getClass(), usuariosUsuIdNew.getUsuId());
                personal.setUsuariosUsuId(usuariosUsuIdNew);
            }
            Collection<Registros> attachedRegistrosCollectionNew = new ArrayList<Registros>();
            for (Registros registrosCollectionNewRegistrosToAttach : registrosCollectionNew) {
                registrosCollectionNewRegistrosToAttach = em.getReference(registrosCollectionNewRegistrosToAttach.getClass(), registrosCollectionNewRegistrosToAttach.getRegId());
                attachedRegistrosCollectionNew.add(registrosCollectionNewRegistrosToAttach);
            }
            registrosCollectionNew = attachedRegistrosCollectionNew;
            personal.setRegistrosCollection(registrosCollectionNew);
            personal = em.merge(personal);
            if (usuariosUsuIdOld != null && !usuariosUsuIdOld.equals(usuariosUsuIdNew)) {
                usuariosUsuIdOld.getPersonalCollection().remove(personal);
                usuariosUsuIdOld = em.merge(usuariosUsuIdOld);
            }
            if (usuariosUsuIdNew != null && !usuariosUsuIdNew.equals(usuariosUsuIdOld)) {
                usuariosUsuIdNew.getPersonalCollection().add(personal);
                usuariosUsuIdNew = em.merge(usuariosUsuIdNew);
            }
            for (Registros registrosCollectionNewRegistros : registrosCollectionNew) {
                if (!registrosCollectionOld.contains(registrosCollectionNewRegistros)) {
                    Personal oldPersonalUsuariosUsuIdOfRegistrosCollectionNewRegistros = registrosCollectionNewRegistros.getPersonalUsuariosUsuId();
                    registrosCollectionNewRegistros.setPersonalUsuariosUsuId(personal);
                    registrosCollectionNewRegistros = em.merge(registrosCollectionNewRegistros);
                    if (oldPersonalUsuariosUsuIdOfRegistrosCollectionNewRegistros != null && !oldPersonalUsuariosUsuIdOfRegistrosCollectionNewRegistros.equals(personal)) {
                        oldPersonalUsuariosUsuIdOfRegistrosCollectionNewRegistros.getRegistrosCollection().remove(registrosCollectionNewRegistros);
                        oldPersonalUsuariosUsuIdOfRegistrosCollectionNewRegistros = em.merge(oldPersonalUsuariosUsuIdOfRegistrosCollectionNewRegistros);
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
                String id = personal.getPrRfc();
                if (findPersonal(id) == null) {
                    throw new NonexistentEntityException("The personal with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
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
            Usuarios usuariosUsuId = personal.getUsuariosUsuId();
            if (usuariosUsuId != null) {
                usuariosUsuId.getPersonalCollection().remove(personal);
                usuariosUsuId = em.merge(usuariosUsuId);
            }
            em.remove(personal);
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
