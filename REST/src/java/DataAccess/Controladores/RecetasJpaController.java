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
import DataAccess.entidades.Medicamento;
import java.util.ArrayList;
import java.util.Collection;
import DataAccess.entidades.Consultas;
import DataAccess.entidades.Recetas;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Victor Javier
 */
public class RecetasJpaController implements Serializable {

    public RecetasJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Recetas recetas) throws RollbackFailureException, Exception {
        if (recetas.getMedicamentoCollection() == null) {
            recetas.setMedicamentoCollection(new ArrayList<Medicamento>());
        }
        if (recetas.getConsultasCollection() == null) {
            recetas.setConsultasCollection(new ArrayList<Consultas>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Medicamento> attachedMedicamentoCollection = new ArrayList<Medicamento>();
            for (Medicamento medicamentoCollectionMedicamentoToAttach : recetas.getMedicamentoCollection()) {
                medicamentoCollectionMedicamentoToAttach = em.getReference(medicamentoCollectionMedicamentoToAttach.getClass(), medicamentoCollectionMedicamentoToAttach.getMedCodigo());
                attachedMedicamentoCollection.add(medicamentoCollectionMedicamentoToAttach);
            }
            recetas.setMedicamentoCollection(attachedMedicamentoCollection);
            Collection<Consultas> attachedConsultasCollection = new ArrayList<Consultas>();
            for (Consultas consultasCollectionConsultasToAttach : recetas.getConsultasCollection()) {
                consultasCollectionConsultasToAttach = em.getReference(consultasCollectionConsultasToAttach.getClass(), consultasCollectionConsultasToAttach.getConId());
                attachedConsultasCollection.add(consultasCollectionConsultasToAttach);
            }
            recetas.setConsultasCollection(attachedConsultasCollection);
            em.persist(recetas);
            for (Medicamento medicamentoCollectionMedicamento : recetas.getMedicamentoCollection()) {
                medicamentoCollectionMedicamento.getRecetasCollection().add(recetas);
                medicamentoCollectionMedicamento = em.merge(medicamentoCollectionMedicamento);
            }
            for (Consultas consultasCollectionConsultas : recetas.getConsultasCollection()) {
                Recetas oldConFolioRecetaOfConsultasCollectionConsultas = consultasCollectionConsultas.getConFolioReceta();
                consultasCollectionConsultas.setConFolioReceta(recetas);
                consultasCollectionConsultas = em.merge(consultasCollectionConsultas);
                if (oldConFolioRecetaOfConsultasCollectionConsultas != null) {
                    oldConFolioRecetaOfConsultasCollectionConsultas.getConsultasCollection().remove(consultasCollectionConsultas);
                    oldConFolioRecetaOfConsultasCollectionConsultas = em.merge(oldConFolioRecetaOfConsultasCollectionConsultas);
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

    public void edit(Recetas recetas) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Recetas persistentRecetas = em.find(Recetas.class, recetas.getRecFolio());
            Collection<Medicamento> medicamentoCollectionOld = persistentRecetas.getMedicamentoCollection();
            Collection<Medicamento> medicamentoCollectionNew = recetas.getMedicamentoCollection();
            Collection<Consultas> consultasCollectionOld = persistentRecetas.getConsultasCollection();
            Collection<Consultas> consultasCollectionNew = recetas.getConsultasCollection();
            List<String> illegalOrphanMessages = null;
            for (Consultas consultasCollectionOldConsultas : consultasCollectionOld) {
                if (!consultasCollectionNew.contains(consultasCollectionOldConsultas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Consultas " + consultasCollectionOldConsultas + " since its conFolioReceta field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Medicamento> attachedMedicamentoCollectionNew = new ArrayList<Medicamento>();
            for (Medicamento medicamentoCollectionNewMedicamentoToAttach : medicamentoCollectionNew) {
                medicamentoCollectionNewMedicamentoToAttach = em.getReference(medicamentoCollectionNewMedicamentoToAttach.getClass(), medicamentoCollectionNewMedicamentoToAttach.getMedCodigo());
                attachedMedicamentoCollectionNew.add(medicamentoCollectionNewMedicamentoToAttach);
            }
            medicamentoCollectionNew = attachedMedicamentoCollectionNew;
            recetas.setMedicamentoCollection(medicamentoCollectionNew);
            Collection<Consultas> attachedConsultasCollectionNew = new ArrayList<Consultas>();
            for (Consultas consultasCollectionNewConsultasToAttach : consultasCollectionNew) {
                consultasCollectionNewConsultasToAttach = em.getReference(consultasCollectionNewConsultasToAttach.getClass(), consultasCollectionNewConsultasToAttach.getConId());
                attachedConsultasCollectionNew.add(consultasCollectionNewConsultasToAttach);
            }
            consultasCollectionNew = attachedConsultasCollectionNew;
            recetas.setConsultasCollection(consultasCollectionNew);
            recetas = em.merge(recetas);
            for (Medicamento medicamentoCollectionOldMedicamento : medicamentoCollectionOld) {
                if (!medicamentoCollectionNew.contains(medicamentoCollectionOldMedicamento)) {
                    medicamentoCollectionOldMedicamento.getRecetasCollection().remove(recetas);
                    medicamentoCollectionOldMedicamento = em.merge(medicamentoCollectionOldMedicamento);
                }
            }
            for (Medicamento medicamentoCollectionNewMedicamento : medicamentoCollectionNew) {
                if (!medicamentoCollectionOld.contains(medicamentoCollectionNewMedicamento)) {
                    medicamentoCollectionNewMedicamento.getRecetasCollection().add(recetas);
                    medicamentoCollectionNewMedicamento = em.merge(medicamentoCollectionNewMedicamento);
                }
            }
            for (Consultas consultasCollectionNewConsultas : consultasCollectionNew) {
                if (!consultasCollectionOld.contains(consultasCollectionNewConsultas)) {
                    Recetas oldConFolioRecetaOfConsultasCollectionNewConsultas = consultasCollectionNewConsultas.getConFolioReceta();
                    consultasCollectionNewConsultas.setConFolioReceta(recetas);
                    consultasCollectionNewConsultas = em.merge(consultasCollectionNewConsultas);
                    if (oldConFolioRecetaOfConsultasCollectionNewConsultas != null && !oldConFolioRecetaOfConsultasCollectionNewConsultas.equals(recetas)) {
                        oldConFolioRecetaOfConsultasCollectionNewConsultas.getConsultasCollection().remove(consultasCollectionNewConsultas);
                        oldConFolioRecetaOfConsultasCollectionNewConsultas = em.merge(oldConFolioRecetaOfConsultasCollectionNewConsultas);
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
                Integer id = recetas.getRecFolio();
                if (findRecetas(id) == null) {
                    throw new NonexistentEntityException("The recetas with id " + id + " no longer exists.");
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
            Recetas recetas;
            try {
                recetas = em.getReference(Recetas.class, id);
                recetas.getRecFolio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The recetas with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Consultas> consultasCollectionOrphanCheck = recetas.getConsultasCollection();
            for (Consultas consultasCollectionOrphanCheckConsultas : consultasCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Recetas (" + recetas + ") cannot be destroyed since the Consultas " + consultasCollectionOrphanCheckConsultas + " in its consultasCollection field has a non-nullable conFolioReceta field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Medicamento> medicamentoCollection = recetas.getMedicamentoCollection();
            for (Medicamento medicamentoCollectionMedicamento : medicamentoCollection) {
                medicamentoCollectionMedicamento.getRecetasCollection().remove(recetas);
                medicamentoCollectionMedicamento = em.merge(medicamentoCollectionMedicamento);
            }
            em.remove(recetas);
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

    public List<Recetas> findRecetasEntities() {
        return findRecetasEntities(true, -1, -1);
    }

    public List<Recetas> findRecetasEntities(int maxResults, int firstResult) {
        return findRecetasEntities(false, maxResults, firstResult);
    }

    private List<Recetas> findRecetasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Recetas.class));
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

    public Recetas findRecetas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Recetas.class, id);
        } finally {
            em.close();
        }
    }

    public int getRecetasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Recetas> rt = cq.from(Recetas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
