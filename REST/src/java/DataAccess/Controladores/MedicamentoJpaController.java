/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.controladores;

import DataAccess.controladores.exceptions.NonexistentEntityException;
import DataAccess.controladores.exceptions.RollbackFailureException;
import DataAccess.entidades.Medicamento;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DataAccess.entidades.Recetas;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Victor Javier
 */
public class MedicamentoJpaController implements Serializable {

    public MedicamentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Medicamento medicamento) throws RollbackFailureException, Exception {
        if (medicamento.getRecetasCollection() == null) {
            medicamento.setRecetasCollection(new ArrayList<Recetas>());
        }
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Collection<Recetas> attachedRecetasCollection = new ArrayList<Recetas>();
            for (Recetas recetasCollectionRecetasToAttach : medicamento.getRecetasCollection()) {
                recetasCollectionRecetasToAttach = em.getReference(recetasCollectionRecetasToAttach.getClass(), recetasCollectionRecetasToAttach.getRecFolio());
                attachedRecetasCollection.add(recetasCollectionRecetasToAttach);
            }
            medicamento.setRecetasCollection(attachedRecetasCollection);
            em.persist(medicamento);
            for (Recetas recetasCollectionRecetas : medicamento.getRecetasCollection()) {
                recetasCollectionRecetas.getMedicamentoCollection().add(medicamento);
                recetasCollectionRecetas = em.merge(recetasCollectionRecetas);
            }
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

    public void edit(Medicamento medicamento) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Medicamento persistentMedicamento = em.find(Medicamento.class, medicamento.getMedCodigo());
            Collection<Recetas> recetasCollectionOld = persistentMedicamento.getRecetasCollection();
            Collection<Recetas> recetasCollectionNew = medicamento.getRecetasCollection();
            Collection<Recetas> attachedRecetasCollectionNew = new ArrayList<Recetas>();
            for (Recetas recetasCollectionNewRecetasToAttach : recetasCollectionNew) {
                recetasCollectionNewRecetasToAttach = em.getReference(recetasCollectionNewRecetasToAttach.getClass(), recetasCollectionNewRecetasToAttach.getRecFolio());
                attachedRecetasCollectionNew.add(recetasCollectionNewRecetasToAttach);
            }
            recetasCollectionNew = attachedRecetasCollectionNew;
            medicamento.setRecetasCollection(recetasCollectionNew);
            medicamento = em.merge(medicamento);
            for (Recetas recetasCollectionOldRecetas : recetasCollectionOld) {
                if (!recetasCollectionNew.contains(recetasCollectionOldRecetas)) {
                    recetasCollectionOldRecetas.getMedicamentoCollection().remove(medicamento);
                    recetasCollectionOldRecetas = em.merge(recetasCollectionOldRecetas);
                }
            }
            for (Recetas recetasCollectionNewRecetas : recetasCollectionNew) {
                if (!recetasCollectionOld.contains(recetasCollectionNewRecetas)) {
                    recetasCollectionNewRecetas.getMedicamentoCollection().add(medicamento);
                    recetasCollectionNewRecetas = em.merge(recetasCollectionNewRecetas);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            try {
                em.getTransaction().rollback();
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
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Medicamento medicamento;
            try {
                medicamento = em.getReference(Medicamento.class, id);
                medicamento.getMedCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The medicamento with id " + id + " no longer exists.", enfe);
            }
            Collection<Recetas> recetasCollection = medicamento.getRecetasCollection();
            for (Recetas recetasCollectionRecetas : recetasCollection) {
                recetasCollectionRecetas.getMedicamentoCollection().remove(medicamento);
                recetasCollectionRecetas = em.merge(recetasCollectionRecetas);
            }
            em.remove(medicamento);
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

    public List<Medicamento> findMedicamentoEntities() {
        List<Medicamento> medicamentos = findMedicamentoEntities(true, -1, -1);
        for (Medicamento medicamento : medicamentos) {
            medicamento.setRecetasCollection(null);
        }
        return medicamentos;
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
    
    public List<Medicamento> obtenerPorConsulta(int idConsulta) {
        List<Medicamento> medicamentos;
        EntityManager em = getEntityManager();
        try {
            Query consulta = em.createNamedQuery("Medicamento.findByConsulta");
            consulta.setParameter("conId", idConsulta);
            medicamentos = consulta.getResultList();
            for (Medicamento medicamento : medicamentos) {
                medicamento.setRecetasCollection(null);
            }
        } finally {
            em.close();
        }
        return medicamentos;
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
