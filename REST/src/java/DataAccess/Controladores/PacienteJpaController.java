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
import DataAccess.entidades.Cita;
import java.util.ArrayList;
import java.util.Collection;
import DataAccess.entidades.Consulta;
import DataAccess.Controladores.exceptions.IllegalOrphanException;
import DataAccess.Controladores.exceptions.NonexistentEntityException;
import DataAccess.Controladores.exceptions.RollbackFailureException;
import DataAccess.entidades.Paciente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Victor Javier
 */
public class PacienteJpaController implements Serializable {

    public PacienteJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Paciente paciente) throws RollbackFailureException, Exception {
        if (paciente.getCitaCollection() == null) {
            paciente.setCitaCollection(new ArrayList<Cita>());
        }
        if (paciente.getConsultaCollection() == null) {
            paciente.setConsultaCollection(new ArrayList<Consulta>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Cita> attachedCitaCollection = new ArrayList<Cita>();
            for (Cita citaCollectionCitaToAttach : paciente.getCitaCollection()) {
                citaCollectionCitaToAttach = em.getReference(citaCollectionCitaToAttach.getClass(), citaCollectionCitaToAttach.getCitaPK());
                attachedCitaCollection.add(citaCollectionCitaToAttach);
            }
            paciente.setCitaCollection(attachedCitaCollection);
            Collection<Consulta> attachedConsultaCollection = new ArrayList<Consulta>();
            for (Consulta consultaCollectionConsultaToAttach : paciente.getConsultaCollection()) {
                consultaCollectionConsultaToAttach = em.getReference(consultaCollectionConsultaToAttach.getClass(), consultaCollectionConsultaToAttach.getConsultaPK());
                attachedConsultaCollection.add(consultaCollectionConsultaToAttach);
            }
            paciente.setConsultaCollection(attachedConsultaCollection);
            em.persist(paciente);
            for (Cita citaCollectionCita : paciente.getCitaCollection()) {
                Paciente oldPacienteOfCitaCollectionCita = citaCollectionCita.getPaciente();
                citaCollectionCita.setPaciente(paciente);
                citaCollectionCita = em.merge(citaCollectionCita);
                if (oldPacienteOfCitaCollectionCita != null) {
                    oldPacienteOfCitaCollectionCita.getCitaCollection().remove(citaCollectionCita);
                    oldPacienteOfCitaCollectionCita = em.merge(oldPacienteOfCitaCollectionCita);
                }
            }
            for (Consulta consultaCollectionConsulta : paciente.getConsultaCollection()) {
                Paciente oldPacienteOfConsultaCollectionConsulta = consultaCollectionConsulta.getPaciente();
                consultaCollectionConsulta.setPaciente(paciente);
                consultaCollectionConsulta = em.merge(consultaCollectionConsulta);
                if (oldPacienteOfConsultaCollectionConsulta != null) {
                    oldPacienteOfConsultaCollectionConsulta.getConsultaCollection().remove(consultaCollectionConsulta);
                    oldPacienteOfConsultaCollectionConsulta = em.merge(oldPacienteOfConsultaCollectionConsulta);
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

    public void edit(Paciente paciente) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Paciente persistentPaciente = em.find(Paciente.class, paciente.getPacNumSeguro());
            Collection<Cita> citaCollectionOld = persistentPaciente.getCitaCollection();
            Collection<Cita> citaCollectionNew = paciente.getCitaCollection();
            Collection<Consulta> consultaCollectionOld = persistentPaciente.getConsultaCollection();
            Collection<Consulta> consultaCollectionNew = paciente.getConsultaCollection();
            List<String> illegalOrphanMessages = null;
            for (Cita citaCollectionOldCita : citaCollectionOld) {
                if (!citaCollectionNew.contains(citaCollectionOldCita)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cita " + citaCollectionOldCita + " since its paciente field is not nullable.");
                }
            }
            for (Consulta consultaCollectionOldConsulta : consultaCollectionOld) {
                if (!consultaCollectionNew.contains(consultaCollectionOldConsulta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Consulta " + consultaCollectionOldConsulta + " since its paciente field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Cita> attachedCitaCollectionNew = new ArrayList<Cita>();
            for (Cita citaCollectionNewCitaToAttach : citaCollectionNew) {
                citaCollectionNewCitaToAttach = em.getReference(citaCollectionNewCitaToAttach.getClass(), citaCollectionNewCitaToAttach.getCitaPK());
                attachedCitaCollectionNew.add(citaCollectionNewCitaToAttach);
            }
            citaCollectionNew = attachedCitaCollectionNew;
            paciente.setCitaCollection(citaCollectionNew);
            Collection<Consulta> attachedConsultaCollectionNew = new ArrayList<Consulta>();
            for (Consulta consultaCollectionNewConsultaToAttach : consultaCollectionNew) {
                consultaCollectionNewConsultaToAttach = em.getReference(consultaCollectionNewConsultaToAttach.getClass(), consultaCollectionNewConsultaToAttach.getConsultaPK());
                attachedConsultaCollectionNew.add(consultaCollectionNewConsultaToAttach);
            }
            consultaCollectionNew = attachedConsultaCollectionNew;
            paciente.setConsultaCollection(consultaCollectionNew);
            paciente = em.merge(paciente);
            for (Cita citaCollectionNewCita : citaCollectionNew) {
                if (!citaCollectionOld.contains(citaCollectionNewCita)) {
                    Paciente oldPacienteOfCitaCollectionNewCita = citaCollectionNewCita.getPaciente();
                    citaCollectionNewCita.setPaciente(paciente);
                    citaCollectionNewCita = em.merge(citaCollectionNewCita);
                    if (oldPacienteOfCitaCollectionNewCita != null && !oldPacienteOfCitaCollectionNewCita.equals(paciente)) {
                        oldPacienteOfCitaCollectionNewCita.getCitaCollection().remove(citaCollectionNewCita);
                        oldPacienteOfCitaCollectionNewCita = em.merge(oldPacienteOfCitaCollectionNewCita);
                    }
                }
            }
            for (Consulta consultaCollectionNewConsulta : consultaCollectionNew) {
                if (!consultaCollectionOld.contains(consultaCollectionNewConsulta)) {
                    Paciente oldPacienteOfConsultaCollectionNewConsulta = consultaCollectionNewConsulta.getPaciente();
                    consultaCollectionNewConsulta.setPaciente(paciente);
                    consultaCollectionNewConsulta = em.merge(consultaCollectionNewConsulta);
                    if (oldPacienteOfConsultaCollectionNewConsulta != null && !oldPacienteOfConsultaCollectionNewConsulta.equals(paciente)) {
                        oldPacienteOfConsultaCollectionNewConsulta.getConsultaCollection().remove(consultaCollectionNewConsulta);
                        oldPacienteOfConsultaCollectionNewConsulta = em.merge(oldPacienteOfConsultaCollectionNewConsulta);
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
                Integer id = paciente.getPacNumSeguro();
                if (findPaciente(id) == null) {
                    throw new NonexistentEntityException("The paciente with id " + id + " no longer exists.");
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
            Paciente paciente;
            try {
                paciente = em.getReference(Paciente.class, id);
                paciente.getPacNumSeguro();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The paciente with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Cita> citaCollectionOrphanCheck = paciente.getCitaCollection();
            for (Cita citaCollectionOrphanCheckCita : citaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Paciente (" + paciente + ") cannot be destroyed since the Cita " + citaCollectionOrphanCheckCita + " in its citaCollection field has a non-nullable paciente field.");
            }
            Collection<Consulta> consultaCollectionOrphanCheck = paciente.getConsultaCollection();
            for (Consulta consultaCollectionOrphanCheckConsulta : consultaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Paciente (" + paciente + ") cannot be destroyed since the Consulta " + consultaCollectionOrphanCheckConsulta + " in its consultaCollection field has a non-nullable paciente field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(paciente);
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

    public List<Paciente> findPacienteEntities() {
        return findPacienteEntities(true, -1, -1);
    }

    public List<Paciente> findPacienteEntities(int maxResults, int firstResult) {
        return findPacienteEntities(false, maxResults, firstResult);
    }

    private List<Paciente> findPacienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Paciente.class));
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

    public Paciente findPaciente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Paciente.class, id);
        } finally {
            em.close();
        }
    }

    public int getPacienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Paciente> rt = cq.from(Paciente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
