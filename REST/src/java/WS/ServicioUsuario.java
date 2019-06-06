package WS;

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

/**
 *
 * @author Victor Javier
 */
@Path("Usuario")
public class ServicioUsuario extends ServicioSeguro {
    @Context
    private UriInfo contexto;
    
    public ServicioUsuario() {
        
    }
    
    @GET
    @Path("obtener/{nombre}/{contraseña}/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param numeroUsuario: recibe el nombre de usuario.
     * @param contraseña: recibe la contraseña del usuario.
     * @param token: recibe el token de sesión.
     */
    public String obtener(@PathParam("nombre") String nombreUsuario, @PathParam("contraseña") String contraseña, @PathParam("token") String token) {
        if (this.tokenValido(token)) {
            
        }
        return null;
    }
    
    @POST
    @Path("registrar/{token}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param contenido: recibe el usuario en JSON.
     * @param token: recibe el token de sesión.
     */
    public String registrar(String contenido, @PathParam("token") String token) {
        if (this.tokenValido(token)) {
            
        }
        return null;
    }
    
    @PUT
    @Path("modificar/{token}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param contenido: recibe el usuario en JSON.
     * @param token: recibe el token de sesión.
     */
    public String modificar(String contenido, @PathParam("token") String token) {
        if (this.tokenValido(token)) {
            
        }
        return null;
    }
}
