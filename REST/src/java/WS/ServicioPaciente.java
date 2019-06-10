package WS;

import DataAccess.controladores.PacientesJpaController;
import DataAccess.entidades.Pacientes;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import org.json.JSONObject;

/**
 *
 * @author Victor Javier
 */
@Path("Paciente")
public class ServicioPaciente extends ServicioSeguro {
    @Context
    private UriInfo contexto;
    
    public ServicioPaciente() {
        
    }
    
    @GET
    @Path("obtener/{numero}/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param numeroSeguro: recibe el número de seguro.
     * @param token: recibe el token de sesión.
     */
    public String obtener(@PathParam("numero") int numeroSeguro, @PathParam("token") String token) {
        boolean resultadoToken = this.tokenValido(token);
        Respuesta respuesta = new Respuesta(resultadoToken);
        if (resultadoToken) {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ConsultaExterna_WSPU");
            PacientesJpaController pacientesJpaController = new PacientesJpaController(entityManagerFactory);
            try {
                respuesta.getJson().put("paciente", new JSONObject(pacientesJpaController.obtenerPorNumeroSeguro(numeroSeguro)));
            } catch(Exception excepcion) {
                respuesta.getJson().put("paciente", new JSONObject("{'pacNumSeguro': '0'}"));
            }
        }
        return respuesta.toString();
    }
    
    @POST
    @Path("registrar/{token}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param contenido: recibe el paciente en JSON.
     * @param token: recibe el token de sesión.
     */
    public String registrar(String contenido, @PathParam("token") String token) {
        boolean resultadoToken = this.tokenValido(token);
        Respuesta respuesta = new Respuesta(resultadoToken);
        if (resultadoToken) {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ConsultaExterna_WSPU");
            PacientesJpaController pacientesJpaController = new PacientesJpaController(entityManagerFactory);
            try {
                Pacientes paciente = new Pacientes(new JSONObject(contenido));
                if (pacientesJpaController.findPacientes(paciente.getPacNumSeguro()) == null) {
                    pacientesJpaController.create(paciente);
                    respuesta.getJson().put("registrado", true);
                } else {
                    respuesta.getJson().put("registrado", false);
                    respuesta.getJson().put("error", "existente");
                }
            } catch(Exception excepcion) {
                respuesta.getJson().put("registrado", false);
                respuesta.getJson().put("error", "registro");
            }
        }
        return respuesta.toString();
    }
    
    @PUT
    @Path("modificar/{token}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param contenido: recibe el paciente en JSON.
     * @param token: recibe el token de sesión.
     */
    public String modificar(String contenido, @PathParam("token") String token) {
        boolean resultadoToken = this.tokenValido(token);
        Respuesta respuesta = new Respuesta(resultadoToken);
        if (resultadoToken) {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ConsultaExterna_WSPU");
            PacientesJpaController pacientesJpaController = new PacientesJpaController(entityManagerFactory);
            try {
                pacientesJpaController.edit(new Pacientes(new JSONObject(contenido)));
                respuesta.getJson().put("actualizado", true);
            } catch(Exception excepcion) {
                respuesta.getJson().put("actualizado", false);
            }
        }
        return respuesta.toString();
    }
}
