package WS;

import DataAccess.controladores.CitasJpaController;
import DataAccess.controladores.PacientesJpaController;
import DataAccess.controladores.PersonalJpaController;
import DataAccess.entidades.Citas;
import java.util.List;
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
@Path("Cita")
public class ServicioCita extends ServicioSeguro {
    @Context
    private UriInfo contexto;
    
    public ServicioCita() {
        
    }
    
    @GET
    @Path("obtener/{fecha}/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param fecha: recibe la fecha donde buscar las citas.
     * @param token: recibe el token de sesión.
     */
    public String obtener(@PathParam("fecha") String fecha, @PathParam("token") String token) {
        boolean resultadoToken = this.tokenValido(token);
        Respuesta respuesta = new Respuesta(resultadoToken);
        if (resultadoToken) {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ConsultaExterna_WSPU");
            CitasJpaController citasJpaController = new CitasJpaController(entityManagerFactory);
            try {
                List<Citas> citas = citasJpaController.obtenerPorFecha(fecha);
                JSONArray jArreglo = new JSONArray(citas);
                respuesta.getJson().put("citas", jArreglo);
            } catch(Exception excepcion) {
                System.out.println(excepcion.getMessage());
                respuesta.getJson().put("citas", new JSONArray("[]"));
            }
        }
        return respuesta.toString();
    }
    
    @POST
    @Path("registrar/{numero}/{rfc}/{token}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param contenido: recibe la cita en JSON.
     * @param numeroSeguro: recibe el número de seguro.
     * @param rfc: recibe el rfc.
     * @param token: recibe el token de sesión.
     */
    public String registrar(String contenido, @PathParam("numero") int numeroSeguro, @PathParam("rfc") String rfc, @PathParam("token") String token) {
        boolean resultadoToken = this.tokenValido(token);
        Respuesta respuesta = new Respuesta(resultadoToken);
        if (resultadoToken) {
            JSONObject jObjeto = new JSONObject(contenido);
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ConsultaExterna_WSPU");
            CitasJpaController citasJpaController = new CitasJpaController(entityManagerFactory);
            Citas cita = new Citas(jObjeto);
            PersonalJpaController personalJpaController = new PersonalJpaController(entityManagerFactory);
            cita.setCitPrRfc(personalJpaController.findPersonal(rfc));
            PacientesJpaController pacientesJpaController = new PacientesJpaController(entityManagerFactory);
            cita.setCitNumSeguroPaciente(pacientesJpaController.findPacientes(numeroSeguro));
            try {
                citasJpaController.create(cita);
                respuesta.getJson().put("registrada", true);
            } catch(Exception excepcion) {
                respuesta.getJson().put("registrado", false);
            }
        }
        return respuesta.toString();
    }
}
