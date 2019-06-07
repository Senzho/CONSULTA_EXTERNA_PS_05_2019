/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.controladores;

import DataAccess.controladores.exceptions.NonexistentEntityException;
import DataAccess.controladores.exceptions.RollbackFailureException;
import DataAccess.entidades.Consultas;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DataAccess.entidades.Pacientes;
import DataAccess.entidades.Recetas;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Victor Javier
 */
public class ConsultasJpaController implements Serializable {

    public ConsultasJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Consultas consultas) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Pacientes conSeguroPaciente = consultas.getConSeguroPaciente();
            if (conSeguroPaciente != null) {
                conSeguroPaciente = em.getReference(conSeguroPaciente.getClass(), conSeguroPaciente.getPacNumSeguro());
                consultas.setConSeguroPaciente(conSeguroPaciente);
            }
            Recetas conFolioReceta = consultas.getConFolioReceta();
            if (conFolioReceta != null) {
                conFolioReceta = em.getReference(conFolioReceta.getClass(), conFolioReceta.getRecFolio());
                consultas.setConFolioReceta(conFolioReceta);
            }
            em.persist(consultas);
            if (conSeguroPaciente != null) {
                conSeguroPaciente.getConsultasCollection().add(consultas);
                conSeguroPaciente = em.merge(conSeguroPaciente);
            }
            if (conFolioReceta != null) {
                conFolioReceta.getConsultasCollection().add(consultas);
                conFolioReceta = em.merge(conFolioReceta);
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

    public void edit(Consultas consultas) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Consultas persistentConsultas = em.find(Consultas.class, consultas.getConId());
            Pacientes conSeguroPacienteOld = persistentConsultas.getConSeguroPaciente();
            Pacientes conSeguroPacienteNew = consultas.getConSeguroPaciente();
            Recetas conFolioRecetaOld = persistentConsultas.getConFolioReceta();
            Recetas conFolioRecetaNew = consultas.getConFolioReceta();
            if (conSeguroPacienteNew != null) {
                conSeguroPacienteNew = em.getReference(conSeguroPacienteNew.getClass(), conSeguroPacienteNew.getPacNumSeguro());
                consultas.setConSeguroPaciente(conSeguroPacienteNew);
            }
            if (conFolioRecetaNew != null) {
                conFolioRecetaNew = em.getReference(conFolioRecetaNew.getClass(), conFolioRecetaNew.getRecFolio());
                consultas.setConFolioReceta(conFolioRecetaNew);
            }
            consultas = em.merge(consultas);
            if (conSeguroPacienteOld != null && !conSeguroPacienteOld.equals(conSeguroPacienteNew)) {
                conSeguroPacienteOld.getConsultasCollection().remove(consultas);
                conSeguroPacienteOld = em.merge(conSeguroPacienteOld);
            }
            if (conSeguroPacienteNew != null && !conSeguroPacienteNew.equals(conSeguroPacienteOld)) {
                conSeguroPacienteNew.getConsultasCollection().add(consultas);
                conSeguroPacienteNew = em.merge(conSeguroPacienteNew);
            }
            if (conFolioRecetaOld != null && !conFolioRecetaOld.equals(conFolioRecetaNew)) {
                conFolioRecetaOld.getConsultasCollection().remove(consultas);
                conFolioRecetaOld = em.merge(conFolioRecetaOld);
            }
            if (conFolioRecetaNew != null && !conFolioRecetaNew.equals(conFolioRecetaOld)) {
                conFolioRecetaNew.getConsultasCollection().add(consultas);
                conFolioRecetaNew = em.merge(conFolioRecetaNew);
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
                Integer id = consultas.getConId();
                if (findConsultas(id) == null) {
                    throw new NonexistentEntityException("The consultas with id " + id + " no longer exists.");
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
            Consultas consultas;
            try {
                consultas = em.getReference(Consultas.class, id);
                consultas.getConId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The consultas with id " + id + " no longer exists.", enfe);
            }
            Pacientes conSeguroPaciente = consultas.getConSeguroPaciente();
            if (conSeguroPaciente != null) {
                conSeguroPaciente.getConsultasCollection().remove(consultas);
                conSeguroPaciente = em.merge(conSeguroPaciente);
            }
            Recetas conFolioReceta = consultas.getConFolioReceta();
            if (conFolioReceta != null) {
                conFolioReceta.getConsultasCollection().remove(consultas);
                conFolioReceta = em.merge(conFolioReceta);
            }
            em.remove(consultas);
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

    public List<Consultas> findConsultasEntities() {
        return findConsultasEntities(true, -1, -1);
    }

    public List<Consultas> findConsultasEntities(int maxResults, int firstResult) {
        return findConsultasEntities(false, maxResults, firstResult);
    }

    private List<Consultas> findConsultasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Consultas.class));
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

    public Consultas findConsultas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Consultas.class, id);
        } finally {
            em.close();
        }
    }

    public int getConsultasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Consultas> rt = cq.from(Consultas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
