package WS;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Victor Javier
 */
@Path("Consulta")
public class ServicioConsulta {
    @Context
    private UriInfo contexto;
    
    public ServicioConsulta() {
        
    }
    
    @GET
    @Path("historicobiometrico/{numero}")
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param numeroSeguro: recibe el número de seguro.
     */
    public String obtenerBiometricos(@PathParam("numero") String numeroSeguro) {
        return null;
    }
    
    @GET
    @Path("historicoclinico/{numero}")
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param numeroSeguro: recibe el número de seguro.
     */
    public String obtenerConsultas(@PathParam("numero") String numeroSeguro) {
        return null;
    }
    
    @POST
    @Path("registrar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param contenido: recibe la consulta en JSON.
     */
    public String registrar(String contenido) {
        return null;
    }
    
    @POST
    @Path("agregar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param contenido: recibe la consulta en JSON.
     */
    public String agregar(String contenido) {
        return null;
    }
}
