/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.Controladores;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DataAccess.entidades.Medicamento;
import java.util.ArrayList;
import java.util.Collection;
import DataAccess.entidades.Consulta;
import DataAccess.Controladores.exceptions.IllegalOrphanException;
import DataAccess.Controladores.exceptions.NonexistentEntityException;
import DataAccess.Controladores.exceptions.RollbackFailureException;
import DataAccess.entidades.Receta;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Victor Javier
 */
public class RecetaJpaController implements Serializable {

    public RecetaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Receta receta) throws RollbackFailureException, Exception {
        if (receta.getMedicamentoCollection() == null) {
            receta.setMedicamentoCollection(new ArrayList<Medicamento>());
        }
        if (receta.getConsultaCollection() == null) {
            receta.setConsultaCollection(new ArrayList<Consulta>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Medicamento> attachedMedicamentoCollection = new ArrayList<Medicamento>();
            for (Medicamento medicamentoCollectionMedicamentoToAttach : receta.getMedicamentoCollection()) {
                medicamentoCollectionMedicamentoToAttach = em.getReference(medicamentoCollectionMedicamentoToAttach.getClass(), medicamentoCollectionMedicamentoToAttach.getMedCodigo());
                attachedMedicamentoCollection.add(medicamentoCollectionMedicamentoToAttach);
            }
            receta.setMedicamentoCollection(attachedMedicamentoCollection);
            Collection<Consulta> attachedConsultaCollection = new ArrayList<Consulta>();
            for (Consulta consultaCollectionConsultaToAttach : receta.getConsultaCollection()) {
                consultaCollectionConsultaToAttach = em.getReference(consultaCollectionConsultaToAttach.getClass(), consultaCollectionConsultaToAttach.getConsultaPK());
                attachedConsultaCollection.add(consultaCollectionConsultaToAttach);
            }
            receta.setConsultaCollection(attachedConsultaCollection);
            em.persist(receta);
            for (Medicamento medicamentoCollectionMedicamento : receta.getMedicamentoCollection()) {
                medicamentoCollectionMedicamento.getRecetaCollection().add(receta);
                medicamentoCollectionMedicamento = em.merge(medicamentoCollectionMedicamento);
            }
            for (Consulta consultaCollectionConsulta : receta.getConsultaCollection()) {
                Receta oldRecetaOfConsultaCollectionConsulta = consultaCollectionConsulta.getReceta();
                consultaCollectionConsulta.setReceta(receta);
                consultaCollectionConsulta = em.merge(consultaCollectionConsulta);
                if (oldRecetaOfConsultaCollectionConsulta != null) {
                    oldRecetaOfConsultaCollectionConsulta.getConsultaCollection().remove(consultaCollectionConsulta);
                    oldRecetaOfConsultaCollectionConsulta = em.merge(oldRecetaOfConsultaCollectionConsulta);
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

    public void edit(Receta receta) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Receta persistentReceta = em.find(Receta.class, receta.getRecFolio());
            Collection<Medicamento> medicamentoCollectionOld = persistentReceta.getMedicamentoCollection();
            Collection<Medicamento> medicamentoCollectionNew = receta.getMedicamentoCollection();
            Collection<Consulta> consultaCollectionOld = persistentReceta.getConsultaCollection();
            Collection<Consulta> consultaCollectionNew = receta.getConsultaCollection();
            List<String> illegalOrphanMessages = null;
            for (Consulta consultaCollectionOldConsulta : consultaCollectionOld) {
                if (!consultaCollectionNew.contains(consultaCollectionOldConsulta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Consulta " + consultaCollectionOldConsulta + " since its receta field is not nullable.");
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
            receta.setMedicamentoCollection(medicamentoCollectionNew);
            Collection<Consulta> attachedConsultaCollectionNew = new ArrayList<Consulta>();
            for (Consulta consultaCollectionNewConsultaToAttach : consultaCollectionNew) {
                consultaCollectionNewConsultaToAttach = em.getReference(consultaCollectionNewConsultaToAttach.getClass(), consultaCollectionNewConsultaToAttach.getConsultaPK());
                attachedConsultaCollectionNew.add(consultaCollectionNewConsultaToAttach);
            }
            consultaCollectionNew = attachedConsultaCollectionNew;
            receta.setConsultaCollection(consultaCollectionNew);
            receta = em.merge(receta);
            for (Medicamento medicamentoCollectionOldMedicamento : medicamentoCollectionOld) {
                if (!medicamentoCollectionNew.contains(medicamentoCollectionOldMedicamento)) {
                    medicamentoCollectionOldMedicamento.getRecetaCollection().remove(receta);
                    medicamentoCollectionOldMedicamento = em.merge(medicamentoCollectionOldMedicamento);
                }
            }
            for (Medicamento medicamentoCollectionNewMedicamento : medicamentoCollectionNew) {
                if (!medicamentoCollectionOld.contains(medicamentoCollectionNewMedicamento)) {
                    medicamentoCollectionNewMedicamento.getRecetaCollection().add(receta);
                    medicamentoCollectionNewMedicamento = em.merge(medicamentoCollectionNewMedicamento);
                }
            }
            for (Consulta consultaCollectionNewConsulta : consultaCollectionNew) {
                if (!consultaCollectionOld.contains(consultaCollectionNewConsulta)) {
                    Receta oldRecetaOfConsultaCollectionNewConsulta = consultaCollectionNewConsulta.getReceta();
                    consultaCollectionNewConsulta.setReceta(receta);
                    consultaCollectionNewConsulta = em.merge(consultaCollectionNewConsulta);
                    if (oldRecetaOfConsultaCollectionNewConsulta != null && !oldRecetaOfConsultaCollectionNewConsulta.equals(receta)) {
                        oldRecetaOfConsultaCollectionNewConsulta.getConsultaCollection().remove(consultaCollectionNewConsulta);
                        oldRecetaOfConsultaCollectionNewConsulta = em.merge(oldRecetaOfConsultaCollectionNewConsulta);
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
                Integer id = receta.getRecFolio();
                if (findReceta(id) == null) {
                    throw new NonexistentEntityException("The receta with id " + id + " no longer exists.");
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
            Receta receta;
            try {
                receta = em.getReference(Receta.class, id);
                receta.getRecFolio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The receta with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Consulta> consultaCollectionOrphanCheck = receta.getConsultaCollection();
            for (Consulta consultaCollectionOrphanCheckConsulta : consultaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Receta (" + receta + ") cannot be destroyed since the Consulta " + consultaCollectionOrphanCheckConsulta + " in its consultaCollection field has a non-nullable receta field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Medicamento> medicamentoCollection = receta.getMedicamentoCollection();
            for (Medicamento medicamentoCollectionMedicamento : medicamentoCollection) {
                medicamentoCollectionMedicamento.getRecetaCollection().remove(receta);
                medicamentoCollectionMedicamento = em.merge(medicamentoCollectionMedicamento);
            }
            em.remove(receta);
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

    public List<Receta> findRecetaEntities() {
        return findRecetaEntities(true, -1, -1);
    }

    public List<Receta> findRecetaEntities(int maxResults, int firstResult) {
        return findRecetaEntities(false, maxResults, firstResult);
    }

    private List<Receta> findRecetaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Receta.class));
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

    public Receta findReceta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Receta.class, id);
        } finally {
            em.close();
        }
    }

    public int getRecetaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Receta> rt = cq.from(Receta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
