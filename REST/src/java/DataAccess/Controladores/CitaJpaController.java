/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.Controladores;

import DataAccess.entidades.Cita;
import DataAccess.entidades.CitaPK;
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
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Victor Javier
 */
public class CitaJpaController implements Serializable {

    public CitaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cita cita) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (cita.getCitaPK() == null) {
            cita.setCitaPK(new CitaPK());
        }
        cita.getCitaPK().setCitNumSeguroPaciente(cita.getPaciente().getPacNumSeguro());
        cita.getCitaPK().setCitIdPersonal(cita.getPersonal().getPersonalPK().getPerId());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Paciente paciente = cita.getPaciente();
            if (paciente != null) {
                paciente = em.getReference(paciente.getClass(), paciente.getPacNumSeguro());
                cita.setPaciente(paciente);
            }
            Personal personal = cita.getPersonal();
            if (personal != null) {
                personal = em.getReference(personal.getClass(), personal.getPersonalPK());
                cita.setPersonal(personal);
            }
            em.persist(cita);
            if (paciente != null) {
                paciente.getCitaCollection().add(cita);
                paciente = em.merge(paciente);
            }
            if (personal != null) {
                personal.getCitaCollection().add(cita);
                personal = em.merge(personal);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCita(cita.getCitaPK()) != null) {
                throw new PreexistingEntityException("Cita " + cita + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cita cita) throws NonexistentEntityException, RollbackFailureException, Exception {
        cita.getCitaPK().setCitNumSeguroPaciente(cita.getPaciente().getPacNumSeguro());
        cita.getCitaPK().setCitIdPersonal(cita.getPersonal().getPersonalPK().getPerId());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Cita persistentCita = em.find(Cita.class, cita.getCitaPK());
            Paciente pacienteOld = persistentCita.getPaciente();
            Paciente pacienteNew = cita.getPaciente();
            Personal personalOld = persistentCita.getPersonal();
            Personal personalNew = cita.getPersonal();
            if (pacienteNew != null) {
                pacienteNew = em.getReference(pacienteNew.getClass(), pacienteNew.getPacNumSeguro());
                cita.setPaciente(pacienteNew);
            }
            if (personalNew != null) {
                personalNew = em.getReference(personalNew.getClass(), personalNew.getPersonalPK());
                cita.setPersonal(personalNew);
            }
            cita = em.merge(cita);
            if (pacienteOld != null && !pacienteOld.equals(pacienteNew)) {
                pacienteOld.getCitaCollection().remove(cita);
                pacienteOld = em.merge(pacienteOld);
            }
            if (pacienteNew != null && !pacienteNew.equals(pacienteOld)) {
                pacienteNew.getCitaCollection().add(cita);
                pacienteNew = em.merge(pacienteNew);
            }
            if (personalOld != null && !personalOld.equals(personalNew)) {
                personalOld.getCitaCollection().remove(cita);
                personalOld = em.merge(personalOld);
            }
            if (personalNew != null && !personalNew.equals(personalOld)) {
                personalNew.getCitaCollection().add(cita);
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
                CitaPK id = cita.getCitaPK();
                if (findCita(id) == null) {
                    throw new NonexistentEntityException("The cita with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CitaPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Cita cita;
            try {
                cita = em.getReference(Cita.class, id);
                cita.getCitaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cita with id " + id + " no longer exists.", enfe);
            }
            Paciente paciente = cita.getPaciente();
            if (paciente != null) {
                paciente.getCitaCollection().remove(cita);
                paciente = em.merge(paciente);
            }
            Personal personal = cita.getPersonal();
            if (personal != null) {
                personal.getCitaCollection().remove(cita);
                personal = em.merge(personal);
            }
            em.remove(cita);
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

    public List<Cita> findCitaEntities() {
        return findCitaEntities(true, -1, -1);
    }

    public List<Cita> findCitaEntities(int maxResults, int firstResult) {
        return findCitaEntities(false, maxResults, firstResult);
    }

    private List<Cita> findCitaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cita.class));
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

    public Cita findCita(CitaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cita.class, id);
        } finally {
            em.close();
        }
    }

    public int getCitaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cita> rt = cq.from(Cita.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
