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
    public String obtenerBiometricos(@PathParam("numero") String numeroSeguro, @PathParam("token") String token) {
        if (this.tokenValido(token)) {
            
        }
        return null;
    }
    
    @GET
    @Path("historicoclinico/{numero}/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param numeroSeguro: recibe el número de seguro.
     * @param token: recibe el token de sesión.
     */
    public String obtenerConsultas(@PathParam("numero") String numeroSeguro, @PathParam("token") String token) {
        if (this.tokenValido(token)) {
            
        }
        return null;
    }
    
    @POST
    @Path("registrar/{token}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param contenido: recibe la consulta en JSON.
     * @param token: recibe el token de sesión.
     */
    public String registrar(String contenido, @PathParam("token") String token) {
        if (this.tokenValido(token)) {
            
        }
        return null;
    }
    
    @POST
    @Path("agregar/{token}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param contenido: recibe la consulta en JSON.
     * @param token: recibe el token de sesión.
     */
    public String agregar(String contenido, @PathParam("token") String token) {
        if (this.tokenValido(token)) {
            
        }
        return null;
    }
}
