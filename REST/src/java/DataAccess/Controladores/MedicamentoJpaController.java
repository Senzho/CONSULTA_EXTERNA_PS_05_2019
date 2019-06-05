/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.Controladores;

import DataAccess.Controladores.exceptions.NonexistentEntityException;
import DataAccess.Controladores.exceptions.RollbackFailureException;
import DataAccess.entidades.Medicamento;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DataAccess.entidades.Receta;
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
public class MedicamentoJpaController implements Serializable {

    public MedicamentoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Medicamento medicamento) throws RollbackFailureException, Exception {
        if (medicamento.getRecetaCollection() == null) {
            medicamento.setRecetaCollection(new ArrayList<Receta>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Receta> attachedRecetaCollection = new ArrayList<Receta>();
            for (Receta recetaCollectionRecetaToAttach : medicamento.getRecetaCollection()) {
                recetaCollectionRecetaToAttach = em.getReference(recetaCollectionRecetaToAttach.getClass(), recetaCollectionRecetaToAttach.getRecFolio());
                attachedRecetaCollection.add(recetaCollectionRecetaToAttach);
            }
            medicamento.setRecetaCollection(attachedRecetaCollection);
            em.persist(medicamento);
            for (Receta recetaCollectionReceta : medicamento.getRecetaCollection()) {
                recetaCollectionReceta.getMedicamentoCollection().add(medicamento);
                recetaCollectionReceta = em.merge(recetaCollectionReceta);
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

    public void edit(Medicamento medicamento) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Medicamento persistentMedicamento = em.find(Medicamento.class, medicamento.getMedCodigo());
            Collection<Receta> recetaCollectionOld = persistentMedicamento.getRecetaCollection();
            Collection<Receta> recetaCollectionNew = medicamento.getRecetaCollection();
            Collection<Receta> attachedRecetaCollectionNew = new ArrayList<Receta>();
            for (Receta recetaCollectionNewRecetaToAttach : recetaCollectionNew) {
                recetaCollectionNewRecetaToAttach = em.getReference(recetaCollectionNewRecetaToAttach.getClass(), recetaCollectionNewRecetaToAttach.getRecFolio());
                attachedRecetaCollectionNew.add(recetaCollectionNewRecetaToAttach);
            }
            recetaCollectionNew = attachedRecetaCollectionNew;
            medicamento.setRecetaCollection(recetaCollectionNew);
            medicamento = em.merge(medicamento);
            for (Receta recetaCollectionOldReceta : recetaCollectionOld) {
                if (!recetaCollectionNew.contains(recetaCollectionOldReceta)) {
                    recetaCollectionOldReceta.getMedicamentoCollection().remove(medicamento);
                    recetaCollectionOldReceta = em.merge(recetaCollectionOldReceta);
                }
            }
            for (Receta recetaCollectionNewReceta : recetaCollectionNew) {
                if (!recetaCollectionOld.contains(recetaCollectionNewReceta)) {
                    recetaCollectionNewReceta.getMedicamentoCollection().add(medicamento);
                    recetaCollectionNewReceta = em.merge(recetaCollectionNewReceta);
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
                Integer id = medicamento.getMedCodigo();
                if (findMedicamento(id) == null) {
                    throw new NonexistentEntityException("The medicamento with id " + id + " no longer exists.");
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
            Medicamento medicamento;
            try {
                medicamento = em.getReference(Medicamento.class, id);
                medicamento.getMedCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The medicamento with id " + id + " no longer exists.", enfe);
            }
            Collection<Receta> recetaCollection = medicamento.getRecetaCollection();
            for (Receta recetaCollectionReceta : recetaCollection) {
                recetaCollectionReceta.getMedicamentoCollection().remove(medicamento);
                recetaCollectionReceta = em.merge(recetaCollectionReceta);
            }
            em.remove(medicamento);
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

    public List<Medicamento> findMedicamentoEntities() {
        return findMedicamentoEntities(true, -1, -1);
    }

    public List<Medicamento> findMedicamentoEntities(int maxResults, int firstResult) {
        return findMedicamentoEntities(false, maxResults, firstResult);
    }

    private List<Medicamento> findMedicamentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Medicamento.class));
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

    public Medicamento findMedicamento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Medicamento.class, id);
        } finally {
            em.close();
        }
    }

    public int getMedicamentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Medicamento> rt = cq.from(Medicamento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
