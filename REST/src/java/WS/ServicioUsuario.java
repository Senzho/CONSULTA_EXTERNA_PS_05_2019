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
public class ServicioUsuario {
    @Context
    private UriInfo contexto;
    
    public ServicioUsuario() {
        
    }
    
    @GET
    @Path("obtener/{nombre}/{contraseña}")
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param numeroUsuario: recibe el nombre de usuario.
     * @param contraseña: recibe la contraseña del usuario.
     */
    public String obtener(@PathParam("nombre") String nombreUsuario, @PathParam("contraseña") String contraseña) {
        return null;
    }
    
    @POST
    @Path("registrar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param contenido: recibe el usuario en JSON.
     */
    public String registrar(String contenido) {
        return null;
    }
    
    @PUT
    @Path("modificar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param contenido: recibe el usuario en JSON.
     */
    public String modificar(String contenido) {
        return null;
    }
}
