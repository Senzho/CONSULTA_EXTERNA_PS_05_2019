package WS;

import javax.ws.rs.Consumes;
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
@Path("Receta")
public class ServicioReceta extends ServicioSeguro {
    @Context
    private UriInfo contexto;
    
    public ServicioReceta() {
        
    }
    
    @POST
    @Path("registrar/{id}/{token}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param contenido: recibe la receta en JSON.
     * @param idConsulta: recibe el identificador de la consulta.
     * @param token: recibe el token de sesión.
     */
    public String registrar(String contenido, @PathParam("id") int idConsulta, @PathParam("token") String token) {
        if (this.tokenValido(token)) {
            
        }
        return null;
    }
}
