/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.Controladores;

import DataAccess.entidades.Consulta;
import DataAccess.entidades.ConsultaPK;
import DataAccess.Controladores.exceptions.NonexistentEntityException;
import DataAccess.Controladores.exceptions.PreexistingEntityException;
import DataAccess.Controladores.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DataAccess.entidades.Paciente;
import DataAccess.entidades.Personal;
import DataAccess.entidades.Receta;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Victor Javier
 */
public class ConsultaJpaController implements Serializable {

    public ConsultaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Consulta consulta) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (consulta.getConsultaPK() == null) {
            consulta.setConsultaPK(new ConsultaPK());
        }
        consulta.getConsultaPK().setConIdPersonal(consulta.getPersonal().getPersonalPK().getPerId());
        consulta.getConsultaPK().setConSeguroPaciente(consulta.getPaciente().getPacNumSeguro());
        consulta.getConsultaPK().setConFolioReceta(consulta.getReceta().getRecFolio());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Paciente paciente = consulta.getPaciente();
            if (paciente != null) {
                paciente = em.getReference(paciente.getClass(), paciente.getPacNumSeguro());
                consulta.setPaciente(paciente);
            }
            Personal personal = consulta.getPersonal();
            if (personal != null) {
                personal = em.getReference(personal.getClass(), personal.getPersonalPK());
                consulta.setPersonal(personal);
            }
            Receta receta = consulta.getReceta();
            if (receta != null) {
                receta = em.getReference(receta.getClass(), receta.getRecFolio());
                consulta.setReceta(receta);
            }
            em.persist(consulta);
            if (paciente != null) {
                paciente.getConsultaCollection().add(consulta);
                paciente = em.merge(paciente);
            }
            if (personal != null) {
                personal.getConsultaCollection().add(consulta);
                personal = em.merge(personal);
            }
            if (receta != null) {
                receta.getConsultaCollection().add(consulta);
                receta = em.merge(receta);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findConsulta(consulta.getConsultaPK()) != null) {
                throw new PreexistingEntityException("Consulta " + consulta + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Consulta consulta) throws NonexistentEntityException, RollbackFailureException, Exception {
        consulta.getConsultaPK().setConIdPersonal(consulta.getPersonal().getPersonalPK().getPerId());
        consulta.getConsultaPK().setConSeguroPaciente(consulta.getPaciente().getPacNumSeguro());
        consulta.getConsultaPK().setConFolioReceta(consulta.getReceta().getRecFolio());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Consulta persistentConsulta = em.find(Consulta.class, consulta.getConsultaPK());
            Paciente pacienteOld = persistentConsulta.getPaciente();
            Paciente pacienteNew = consulta.getPaciente();
            Personal personalOld = persistentConsulta.getPersonal();
            Personal personalNew = consulta.getPersonal();
            Receta recetaOld = persistentConsulta.getReceta();
            Receta recetaNew = consulta.getReceta();
            if (pacienteNew != null) {
                pacienteNew = em.getReference(pacienteNew.getClass(), pacienteNew.getPacNumSeguro());
                consulta.setPaciente(pacienteNew);
            }
            if (personalNew != null) {
                personalNew = em.getReference(personalNew.getClass(), personalNew.getPersonalPK());
                consulta.setPersonal(personalNew);
            }
            if (recetaNew != null) {
                recetaNew = em.getReference(recetaNew.getClass(), recetaNew.getRecFolio());
                consulta.setReceta(recetaNew);
            }
            consulta = em.merge(consulta);
            if (pacienteOld != null && !pacienteOld.equals(pacienteNew)) {
                pacienteOld.getConsultaCollection().remove(consulta);
                pacienteOld = em.merge(pacienteOld);
            }
            if (pacienteNew != null && !pacienteNew.equals(pacienteOld)) {
                pacienteNew.getConsultaCollection().add(consulta);
                pacienteNew = em.merge(pacienteNew);
            }
            if (personalOld != null && !personalOld.equals(personalNew)) {
                personalOld.getConsultaCollection().remove(consulta);
                personalOld = em.merge(personalOld);
            }
            if (personalNew != null && !personalNew.equals(personalOld)) {
                personalNew.getConsultaCollection().add(consulta);
                personalNew = em.merge(personalNew);
            }
            if (recetaOld != null && !recetaOld.equals(recetaNew)) {
                recetaOld.getConsultaCollection().remove(consulta);
                recetaOld = em.merge(recetaOld);
            }
            if (recetaNew != null && !recetaNew.equals(recetaOld)) {
                recetaNew.getConsultaCollection().add(consulta);
                recetaNew = em.merge(recetaNew);
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
                ConsultaPK id = consulta.getConsultaPK();
                if (findConsulta(id) == null) {
                    throw new NonexistentEntityException("The consulta with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ConsultaPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Consulta consulta;
            try {
                consulta = em.getReference(Consulta.class, id);
                consulta.getConsultaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The consulta with id " + id + " no longer exists.", enfe);
            }
            Paciente paciente = consulta.getPaciente();
            if (paciente != null) {
                paciente.getConsultaCollection().remove(consulta);
                paciente = em.merge(paciente);
            }
            Personal personal = consulta.getPersonal();
            if (personal != null) {
                personal.getConsultaCollection().remove(consulta);
                personal = em.merge(personal);
            }
            Receta receta = consulta.getReceta();
            if (receta != null) {
                receta.getConsultaCollection().remove(consulta);
                receta = em.merge(receta);
            }
            em.remove(consulta);
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

    public List<Consulta> findConsultaEntities() {
        return findConsultaEntities(true, -1, -1);
    }

    public List<Consulta> findConsultaEntities(int maxResults, int firstResult) {
        return findConsultaEntities(false, maxResults, firstResult);
    }

    private List<Consulta> findConsultaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Consulta.class));
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

    public Consulta findConsulta(ConsultaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Consulta.class, id);
        } finally {
            em.close();
        }
    }

    public int getConsultaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Consulta> rt = cq.from(Consulta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
