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
import DataAccess.entidades.Citas;
import java.util.ArrayList;
import java.util.Collection;
import DataAccess.entidades.Consultas;
import DataAccess.entidades.Pacientes;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Victor Javier
 */
public class PacientesJpaController implements Serializable {

    public PacientesJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pacientes pacientes) throws RollbackFailureException, Exception {
        if (pacientes.getCitasCollection() == null) {
            pacientes.setCitasCollection(new ArrayList<Citas>());
        }
        if (pacientes.getConsultasCollection() == null) {
            pacientes.setConsultasCollection(new ArrayList<Consultas>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Citas> attachedCitasCollection = new ArrayList<Citas>();
            for (Citas citasCollectionCitasToAttach : pacientes.getCitasCollection()) {
                citasCollectionCitasToAttach = em.getReference(citasCollectionCitasToAttach.getClass(), citasCollectionCitasToAttach.getCitId());
                attachedCitasCollection.add(citasCollectionCitasToAttach);
            }
            pacientes.setCitasCollection(attachedCitasCollection);
            Collection<Consultas> attachedConsultasCollection = new ArrayList<Consultas>();
            for (Consultas consultasCollectionConsultasToAttach : pacientes.getConsultasCollection()) {
                consultasCollectionConsultasToAttach = em.getReference(consultasCollectionConsultasToAttach.getClass(), consultasCollectionConsultasToAttach.getConId());
                attachedConsultasCollection.add(consultasCollectionConsultasToAttach);
            }
            pacientes.setConsultasCollection(attachedConsultasCollection);
            em.persist(pacientes);
            for (Citas citasCollectionCitas : pacientes.getCitasCollection()) {
                Pacientes oldCitNumSeguroPacienteOfCitasCollectionCitas = citasCollectionCitas.getCitNumSeguroPaciente();
                citasCollectionCitas.setCitNumSeguroPaciente(pacientes);
                citasCollectionCitas = em.merge(citasCollectionCitas);
                if (oldCitNumSeguroPacienteOfCitasCollectionCitas != null) {
                    oldCitNumSeguroPacienteOfCitasCollectionCitas.getCitasCollection().remove(citasCollectionCitas);
                    oldCitNumSeguroPacienteOfCitasCollectionCitas = em.merge(oldCitNumSeguroPacienteOfCitasCollectionCitas);
                }
            }
            for (Consultas consultasCollectionConsultas : pacientes.getConsultasCollection()) {
                Pacientes oldConSeguroPacienteOfConsultasCollectionConsultas = consultasCollectionConsultas.getConSeguroPaciente();
                consultasCollectionConsultas.setConSeguroPaciente(pacientes);
                consultasCollectionConsultas = em.merge(consultasCollectionConsultas);
                if (oldConSeguroPacienteOfConsultasCollectionConsultas != null) {
                    oldConSeguroPacienteOfConsultasCollectionConsultas.getConsultasCollection().remove(consultasCollectionConsultas);
                    oldConSeguroPacienteOfConsultasCollectionConsultas = em.merge(oldConSeguroPacienteOfConsultasCollectionConsultas);
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

    public void edit(Pacientes pacientes) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Pacientes persistentPacientes = em.find(Pacientes.class, pacientes.getPacNumSeguro());
            Collection<Citas> citasCollectionOld = persistentPacientes.getCitasCollection();
            Collection<Citas> citasCollectionNew = pacientes.getCitasCollection();
            Collection<Consultas> consultasCollectionOld = persistentPacientes.getConsultasCollection();
            Collection<Consultas> consultasCollectionNew = pacientes.getConsultasCollection();
            List<String> illegalOrphanMessages = null;
            for (Citas citasCollectionOldCitas : citasCollectionOld) {
                if (!citasCollectionNew.contains(citasCollectionOldCitas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Citas " + citasCollectionOldCitas + " since its citNumSeguroPaciente field is not nullable.");
                }
            }
            for (Consultas consultasCollectionOldConsultas : consultasCollectionOld) {
                if (!consultasCollectionNew.contains(consultasCollectionOldConsultas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Consultas " + consultasCollectionOldConsultas + " since its conSeguroPaciente field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Citas> attachedCitasCollectionNew = new ArrayList<Citas>();
            for (Citas citasCollectionNewCitasToAttach : citasCollectionNew) {
                citasCollectionNewCitasToAttach = em.getReference(citasCollectionNewCitasToAttach.getClass(), citasCollectionNewCitasToAttach.getCitId());
                attachedCitasCollectionNew.add(citasCollectionNewCitasToAttach);
            }
            citasCollectionNew = attachedCitasCollectionNew;
            pacientes.setCitasCollection(citasCollectionNew);
            Collection<Consultas> attachedConsultasCollectionNew = new ArrayList<Consultas>();
            for (Consultas consultasCollectionNewConsultasToAttach : consultasCollectionNew) {
                consultasCollectionNewConsultasToAttach = em.getReference(consultasCollectionNewConsultasToAttach.getClass(), consultasCollectionNewConsultasToAttach.getConId());
                attachedConsultasCollectionNew.add(consultasCollectionNewConsultasToAttach);
            }
            consultasCollectionNew = attachedConsultasCollectionNew;
            pacientes.setConsultasCollection(consultasCollectionNew);
            pacientes = em.merge(pacientes);
            for (Citas citasCollectionNewCitas : citasCollectionNew) {
                if (!citasCollectionOld.contains(citasCollectionNewCitas)) {
                    Pacientes oldCitNumSeguroPacienteOfCitasCollectionNewCitas = citasCollectionNewCitas.getCitNumSeguroPaciente();
                    citasCollectionNewCitas.setCitNumSeguroPaciente(pacientes);
                    citasCollectionNewCitas = em.merge(citasCollectionNewCitas);
                    if (oldCitNumSeguroPacienteOfCitasCollectionNewCitas != null && !oldCitNumSeguroPacienteOfCitasCollectionNewCitas.equals(pacientes)) {
                        oldCitNumSeguroPacienteOfCitasCollectionNewCitas.getCitasCollection().remove(citasCollectionNewCitas);
                        oldCitNumSeguroPacienteOfCitasCollectionNewCitas = em.merge(oldCitNumSeguroPacienteOfCitasCollectionNewCitas);
                    }
                }
            }
            for (Consultas consultasCollectionNewConsultas : consultasCollectionNew) {
                if (!consultasCollectionOld.contains(consultasCollectionNewConsultas)) {
                    Pacientes oldConSeguroPacienteOfConsultasCollectionNewConsultas = consultasCollectionNewConsultas.getConSeguroPaciente();
                    consultasCollectionNewConsultas.setConSeguroPaciente(pacientes);
                    consultasCollectionNewConsultas = em.merge(consultasCollectionNewConsultas);
                    if (oldConSeguroPacienteOfConsultasCollectionNewConsultas != null && !oldConSeguroPacienteOfConsultasCollectionNewConsultas.equals(pacientes)) {
                        oldConSeguroPacienteOfConsultasCollectionNewConsultas.getConsultasCollection().remove(consultasCollectionNewConsultas);
                        oldConSeguroPacienteOfConsultasCollectionNewConsultas = em.merge(oldConSeguroPacienteOfConsultasCollectionNewConsultas);
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
                Integer id = pacientes.getPacNumSeguro();
                if (findPacientes(id) == null) {
                    throw new NonexistentEntityException("The pacientes with id " + id + " no longer exists.");
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
            Pacientes pacientes;
            try {
                pacientes = em.getReference(Pacientes.class, id);
                pacientes.getPacNumSeguro();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pacientes with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Citas> citasCollectionOrphanCheck = pacientes.getCitasCollection();
            for (Citas citasCollectionOrphanCheckCitas : citasCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pacientes (" + pacientes + ") cannot be destroyed since the Citas " + citasCollectionOrphanCheckCitas + " in its citasCollection field has a non-nullable citNumSeguroPaciente field.");
            }
            Collection<Consultas> consultasCollectionOrphanCheck = pacientes.getConsultasCollection();
            for (Consultas consultasCollectionOrphanCheckConsultas : consultasCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pacientes (" + pacientes + ") cannot be destroyed since the Consultas " + consultasCollectionOrphanCheckConsultas + " in its consultasCollection field has a non-nullable conSeguroPaciente field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(pacientes);
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

    public List<Pacientes> findPacientesEntities() {
        return findPacientesEntities(true, -1, -1);
    }

    public List<Pacientes> findPacientesEntities(int maxResults, int firstResult) {
        return findPacientesEntities(false, maxResults, firstResult);
    }

    private List<Pacientes> findPacientesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pacientes.class));
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

    public Pacientes findPacientes(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pacientes.class, id);
        } finally {
            em.close();
        }
    }

    public int getPacientesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pacientes> rt = cq.from(Pacientes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
