package WS;

import DataAccess.controladores.ConsultasJpaController;
import DataAccess.controladores.MedicamentoJpaController;
import DataAccess.controladores.PacientesJpaController;
import DataAccess.controladores.PersonalJpaController;
import DataAccess.controladores.RecetasJpaController;
import DataAccess.entidades.Consultas;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Victor Javier
 */
@Path("Consulta")
public class ServicioConsulta extends ServicioSeguro {
    @Context
    private UriInfo contexto;
    
    public ServicioConsulta() {
        
    }
    
    @GET
    @Path("historicobiometrico/{numero}/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param numeroSeguro: recibe el número de seguro.
     * @param token: recibe el token de sesión.
     */
    public String obtenerBiometricos(@PathParam("numero") int numeroSeguro, @PathParam("token") String token) {
        boolean resultadoToken = this.tokenValido(token);
        Respuesta respuesta = new Respuesta(resultadoToken);
        if (resultadoToken) {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ConsultaExterna_WSPU");
            ConsultasJpaController consultasJpaController = new ConsultasJpaController(entityManagerFactory);
            try {
                respuesta.getJson().put("consultas", new JSONArray(consultasJpaController.obtenerPorPaciente(numeroSeguro)));
            } catch(Exception excepcion) {
                respuesta.getJson().put("consultas", new JSONArray("[]"));
            }
        }
        return respuesta.toString();
    }
    
    @GET
    @Path("historicoclinico/{numero}/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param numeroSeguro: recibe el número de seguro.
     * @param token: recibe el token de sesión.
     */
    public String obtenerConsultas(@PathParam("numero") int numeroSeguro, @PathParam("token") String token) {
        boolean resultadoToken = this.tokenValido(token);
        Respuesta respuesta = new Respuesta(resultadoToken);
        if (resultadoToken) {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ConsultaExterna_WSPU");
            ConsultasJpaController consultasJpaController = new ConsultasJpaController(entityManagerFactory);
            MedicamentoJpaController medicamentoJpaController = new MedicamentoJpaController(entityManagerFactory);
            try {
                JSONArray jArreglo = new JSONArray();
                for (Consultas consulta : consultasJpaController.obtenerPorPaciente(numeroSeguro)) {
                    JSONObject jObjeto = new JSONObject(consulta);
                    jObjeto.put("medicamentos", new JSONArray(medicamentoJpaController.obtenerPorConsulta(consulta.getConId())));
                    jArreglo.put(jObjeto);
                }
                respuesta.getJson().put("consultas", jArreglo);
            } catch(Exception excepcion) {
                respuesta.getJson().put("consultas", new JSONArray("[]"));
            }
        }
        return respuesta.toString();
    }
    
    @POST
    @Path("registrar/{numero}/{rfc}/{folio}/{token}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param contenido: recibe la consulta en JSON.
     * @param numeroSeguro: recibe el número de seguro.
     * @param rfc: recibe el rfc.
     * @param folio: recibe el folio de la receta.
     * @param token: recibe el token de sesión.
     */
    public String registrar(String contenido, @PathParam("numero") int numeroSeguro, @PathParam("rfc") String rfc, @PathParam("folio") int folio, @PathParam("token") String token) {
        boolean resultadoToken = this.tokenValido(token);
        Respuesta respuesta = new Respuesta(resultadoToken);
        if (resultadoToken) {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ConsultaExterna_WSPU");
            ConsultasJpaController consultaJpaController = new ConsultasJpaController(entityManagerFactory);
            PacientesJpaController pacientesJpaController = new PacientesJpaController(entityManagerFactory);
            PersonalJpaController personalJpaController = new PersonalJpaController(entityManagerFactory);
            RecetasJpaController recetasJpaController = new RecetasJpaController(entityManagerFactory);
            try {
                Consultas consulta = new Consultas(new JSONObject(contenido));
                consulta.setConSeguroPaciente(pacientesJpaController.findPacientes(numeroSeguro));
                consulta.setConPrRfc(personalJpaController.findPersonal(rfc));
                consulta.setConFolioReceta(recetasJpaController.findRecetas(folio));
                consultaJpaController.create(consulta);
                respuesta.getJson().put("registrado", true);
            } catch(Exception excepcion) {
                respuesta.getJson().put("registrado", false);
            }
        }
        return respuesta.toString();
    }
}
