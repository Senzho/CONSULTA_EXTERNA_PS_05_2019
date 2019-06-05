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
import DataAccess.entidades.Usuario;
import DataAccess.entidades.Cita;
import java.util.ArrayList;
import java.util.Collection;
import DataAccess.entidades.Consulta;
import DataAccess.Controladores.exceptions.IllegalOrphanException;
import DataAccess.Controladores.exceptions.NonexistentEntityException;
import DataAccess.Controladores.exceptions.PreexistingEntityException;
import DataAccess.Controladores.exceptions.RollbackFailureException;
import DataAccess.entidades.Personal;
import DataAccess.entidades.PersonalPK;
import DataAccess.entidades.Registro;
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
        if (personal.getPersonalPK() == null) {
            personal.setPersonalPK(new PersonalPK());
        }
        if (personal.getCitaCollection() == null) {
            personal.setCitaCollection(new ArrayList<Cita>());
        }
        if (personal.getConsultaCollection() == null) {
            personal.setConsultaCollection(new ArrayList<Consulta>());
        }
        if (personal.getRegistroCollection() == null) {
            personal.setRegistroCollection(new ArrayList<Registro>());
        }
        personal.getPersonalPK().setUsuariosUsuId(personal.getUsuario().getUsuId());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Usuario usuario = personal.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getUsuId());
                personal.setUsuario(usuario);
            }
            Collection<Cita> attachedCitaCollection = new ArrayList<Cita>();
            for (Cita citaCollectionCitaToAttach : personal.getCitaCollection()) {
                citaCollectionCitaToAttach = em.getReference(citaCollectionCitaToAttach.getClass(), citaCollectionCitaToAttach.getCitaPK());
                attachedCitaCollection.add(citaCollectionCitaToAttach);
            }
            personal.setCitaCollection(attachedCitaCollection);
            Collection<Consulta> attachedConsultaCollection = new ArrayList<Consulta>();
            for (Consulta consultaCollectionConsultaToAttach : personal.getConsultaCollection()) {
                consultaCollectionConsultaToAttach = em.getReference(consultaCollectionConsultaToAttach.getClass(), consultaCollectionConsultaToAttach.getConsultaPK());
                attachedConsultaCollection.add(consultaCollectionConsultaToAttach);
            }
            personal.setConsultaCollection(attachedConsultaCollection);
            Collection<Registro> attachedRegistroCollection = new ArrayList<Registro>();
            for (Registro registroCollectionRegistroToAttach : personal.getRegistroCollection()) {
                registroCollectionRegistroToAttach = em.getReference(registroCollectionRegistroToAttach.getClass(), registroCollectionRegistroToAttach.getRegistroPK());
                attachedRegistroCollection.add(registroCollectionRegistroToAttach);
            }
            personal.setRegistroCollection(attachedRegistroCollection);
            em.persist(personal);
            if (usuario != null) {
                usuario.getPersonalCollection().add(personal);
                usuario = em.merge(usuario);
            }
            for (Cita citaCollectionCita : personal.getCitaCollection()) {
                Personal oldPersonalOfCitaCollectionCita = citaCollectionCita.getPersonal();
                citaCollectionCita.setPersonal(personal);
                citaCollectionCita = em.merge(citaCollectionCita);
                if (oldPersonalOfCitaCollectionCita != null) {
                    oldPersonalOfCitaCollectionCita.getCitaCollection().remove(citaCollectionCita);
                    oldPersonalOfCitaCollectionCita = em.merge(oldPersonalOfCitaCollectionCita);
                }
            }
            for (Consulta consultaCollectionConsulta : personal.getConsultaCollection()) {
                Personal oldPersonalOfConsultaCollectionConsulta = consultaCollectionConsulta.getPersonal();
                consultaCollectionConsulta.setPersonal(personal);
                consultaCollectionConsulta = em.merge(consultaCollectionConsulta);
                if (oldPersonalOfConsultaCollectionConsulta != null) {
                    oldPersonalOfConsultaCollectionConsulta.getConsultaCollection().remove(consultaCollectionConsulta);
                    oldPersonalOfConsultaCollectionConsulta = em.merge(oldPersonalOfConsultaCollectionConsulta);
                }
            }
            for (Registro registroCollectionRegistro : personal.getRegistroCollection()) {
                Personal oldPersonalOfRegistroCollectionRegistro = registroCollectionRegistro.getPersonal();
                registroCollectionRegistro.setPersonal(personal);
                registroCollectionRegistro = em.merge(registroCollectionRegistro);
                if (oldPersonalOfRegistroCollectionRegistro != null) {
                    oldPersonalOfRegistroCollectionRegistro.getRegistroCollection().remove(registroCollectionRegistro);
                    oldPersonalOfRegistroCollectionRegistro = em.merge(oldPersonalOfRegistroCollectionRegistro);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findPersonal(personal.getPersonalPK()) != null) {
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
        personal.getPersonalPK().setUsuariosUsuId(personal.getUsuario().getUsuId());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Personal persistentPersonal = em.find(Personal.class, personal.getPersonalPK());
            Usuario usuarioOld = persistentPersonal.getUsuario();
            Usuario usuarioNew = personal.getUsuario();
            Collection<Cita> citaCollectionOld = persistentPersonal.getCitaCollection();
            Collection<Cita> citaCollectionNew = personal.getCitaCollection();
            Collection<Consulta> consultaCollectionOld = persistentPersonal.getConsultaCollection();
            Collection<Consulta> consultaCollectionNew = personal.getConsultaCollection();
            Collection<Registro> registroCollectionOld = persistentPersonal.getRegistroCollection();
            Collection<Registro> registroCollectionNew = personal.getRegistroCollection();
            List<String> illegalOrphanMessages = null;
            for (Cita citaCollectionOldCita : citaCollectionOld) {
                if (!citaCollectionNew.contains(citaCollectionOldCita)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cita " + citaCollectionOldCita + " since its personal field is not nullable.");
                }
            }
            for (Consulta consultaCollectionOldConsulta : consultaCollectionOld) {
                if (!consultaCollectionNew.contains(consultaCollectionOldConsulta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Consulta " + consultaCollectionOldConsulta + " since its personal field is not nullable.");
                }
            }
            for (Registro registroCollectionOldRegistro : registroCollectionOld) {
                if (!registroCollectionNew.contains(registroCollectionOldRegistro)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Registro " + registroCollectionOldRegistro + " since its personal field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getUsuId());
                personal.setUsuario(usuarioNew);
            }
            Collection<Cita> attachedCitaCollectionNew = new ArrayList<Cita>();
            for (Cita citaCollectionNewCitaToAttach : citaCollectionNew) {
                citaCollectionNewCitaToAttach = em.getReference(citaCollectionNewCitaToAttach.getClass(), citaCollectionNewCitaToAttach.getCitaPK());
                attachedCitaCollectionNew.add(citaCollectionNewCitaToAttach);
            }
            citaCollectionNew = attachedCitaCollectionNew;
            personal.setCitaCollection(citaCollectionNew);
            Collection<Consulta> attachedConsultaCollectionNew = new ArrayList<Consulta>();
            for (Consulta consultaCollectionNewConsultaToAttach : consultaCollectionNew) {
                consultaCollectionNewConsultaToAttach = em.getReference(consultaCollectionNewConsultaToAttach.getClass(), consultaCollectionNewConsultaToAttach.getConsultaPK());
                attachedConsultaCollectionNew.add(consultaCollectionNewConsultaToAttach);
            }
            consultaCollectionNew = attachedConsultaCollectionNew;
            personal.setConsultaCollection(consultaCollectionNew);
            Collection<Registro> attachedRegistroCollectionNew = new ArrayList<Registro>();
            for (Registro registroCollectionNewRegistroToAttach : registroCollectionNew) {
                registroCollectionNewRegistroToAttach = em.getReference(registroCollectionNewRegistroToAttach.getClass(), registroCollectionNewRegistroToAttach.getRegistroPK());
                attachedRegistroCollectionNew.add(registroCollectionNewRegistroToAttach);
            }
            registroCollectionNew = attachedRegistroCollectionNew;
            personal.setRegistroCollection(registroCollectionNew);
            personal = em.merge(personal);
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getPersonalCollection().remove(personal);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getPersonalCollection().add(personal);
                usuarioNew = em.merge(usuarioNew);
            }
            for (Cita citaCollectionNewCita : citaCollectionNew) {
                if (!citaCollectionOld.contains(citaCollectionNewCita)) {
                    Personal oldPersonalOfCitaCollectionNewCita = citaCollectionNewCita.getPersonal();
                    citaCollectionNewCita.setPersonal(personal);
                    citaCollectionNewCita = em.merge(citaCollectionNewCita);
                    if (oldPersonalOfCitaCollectionNewCita != null && !oldPersonalOfCitaCollectionNewCita.equals(personal)) {
                        oldPersonalOfCitaCollectionNewCita.getCitaCollection().remove(citaCollectionNewCita);
                        oldPersonalOfCitaCollectionNewCita = em.merge(oldPersonalOfCitaCollectionNewCita);
                    }
                }
            }
            for (Consulta consultaCollectionNewConsulta : consultaCollectionNew) {
                if (!consultaCollectionOld.contains(consultaCollectionNewConsulta)) {
                    Personal oldPersonalOfConsultaCollectionNewConsulta = consultaCollectionNewConsulta.getPersonal();
                    consultaCollectionNewConsulta.setPersonal(personal);
                    consultaCollectionNewConsulta = em.merge(consultaCollectionNewConsulta);
                    if (oldPersonalOfConsultaCollectionNewConsulta != null && !oldPersonalOfConsultaCollectionNewConsulta.equals(personal)) {
                        oldPersonalOfConsultaCollectionNewConsulta.getConsultaCollection().remove(consultaCollectionNewConsulta);
                        oldPersonalOfConsultaCollectionNewConsulta = em.merge(oldPersonalOfConsultaCollectionNewConsulta);
                    }
                }
            }
            for (Registro registroCollectionNewRegistro : registroCollectionNew) {
                if (!registroCollectionOld.contains(registroCollectionNewRegistro)) {
                    Personal oldPersonalOfRegistroCollectionNewRegistro = registroCollectionNewRegistro.getPersonal();
                    registroCollectionNewRegistro.setPersonal(personal);
                    registroCollectionNewRegistro = em.merge(registroCollectionNewRegistro);
                    if (oldPersonalOfRegistroCollectionNewRegistro != null && !oldPersonalOfRegistroCollectionNewRegistro.equals(personal)) {
                        oldPersonalOfRegistroCollectionNewRegistro.getRegistroCollection().remove(registroCollectionNewRegistro);
                        oldPersonalOfRegistroCollectionNewRegistro = em.merge(oldPersonalOfRegistroCollectionNewRegistro);
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
                PersonalPK id = personal.getPersonalPK();
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

    public void destroy(PersonalPK id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Personal personal;
            try {
                personal = em.getReference(Personal.class, id);
                personal.getPersonalPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The personal with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Cita> citaCollectionOrphanCheck = personal.getCitaCollection();
            for (Cita citaCollectionOrphanCheckCita : citaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Personal (" + personal + ") cannot be destroyed since the Cita " + citaCollectionOrphanCheckCita + " in its citaCollection field has a non-nullable personal field.");
            }
            Collection<Consulta> consultaCollectionOrphanCheck = personal.getConsultaCollection();
            for (Consulta consultaCollectionOrphanCheckConsulta : consultaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Personal (" + personal + ") cannot be destroyed since the Consulta " + consultaCollectionOrphanCheckConsulta + " in its consultaCollection field has a non-nullable personal field.");
            }
            Collection<Registro> registroCollectionOrphanCheck = personal.getRegistroCollection();
            for (Registro registroCollectionOrphanCheckRegistro : registroCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Personal (" + personal + ") cannot be destroyed since the Registro " + registroCollectionOrphanCheckRegistro + " in its registroCollection field has a non-nullable personal field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Usuario usuario = personal.getUsuario();
            if (usuario != null) {
                usuario.getPersonalCollection().remove(personal);
                usuario = em.merge(usuario);
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

    public Personal findPersonal(PersonalPK id) {
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
