package WS;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
@Path("Recepcionista")
public class ServicioRecepcionista extends ServicioSeguro {
    @Context
    private UriInfo contexto;
    
    public ServicioRecepcionista() {
        
    }
    
    @DELETE
    @Path("eliminar/{id}/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param idRecepcionista: recibe el identificador del recepcionista.
     * @param token: recibe el token de sesión.
     */
    public String cambiarEstado(@PathParam("id") int idRecepcionista, @PathParam("token") String token) {
        if (this.tokenValido(token)) {
            
        }
        return null;
    }
    
    @GET
    @Path("obtener/{numero}/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param numeroPersonal: recibe el número de personal.
     * @param token: recibe el token de sesión.
     */
    public String obtener(@PathParam("numero") String numeroPersonal, @PathParam("token") String token) {
        if (this.tokenValido(token)) {
            
        }
        return null;
    }
    
    @POST
    @Path("registrar/{token}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param contenido: recibe el recepcionista en JSON.
     * @param token: recibe el token de sesión.
     */
    public String registrar(String contenido, @PathParam("token") String token) {
        if (this.tokenValido(token)) {
            
        }
        return null;
    }
    
    @POST
    @Path("registrarentrada/{numero}/{id}/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param numeroConsultorio: recibe el número de consultorio.
     * @param idRecepcionista: recibe el identificador del recepcionista.
     * @param token: recibe el token de sesión.
     */
    public String registrarEntrada(@PathParam("numero") String numeroConsultorio, @PathParam("id") int idRecepcionista, @PathParam("token") String token) {
        if (this.tokenValido(token)) {
            
        }
        return null;
    }
    
    @POST
    @Path("registrarsalida/{id}/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param idRecepcionista: recibe el identificador del recepcionista.
     * @param token: recibe el token de sesión.
     */
    public String registrarSalida(@PathParam("id") int idRecepcionista, @PathParam("token") String token) {
        if (this.tokenValido(token)) {
            
        }
        return null;
    }
    
    @PUT
    @Path("modificar/{token}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param contenido: recibe el recepcionista en JSON.
     * @param token: recibe el token de sesión.
     */
    public String modificar(String contenido, @PathParam("token") String token) {
        if (this.tokenValido(token)) {
            
        }
        return null;
    }
}
