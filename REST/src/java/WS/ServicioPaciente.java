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
@Path("Paciente")
public class ServicioPaciente {
    @Context
    private UriInfo contexto;
    
    public ServicioPaciente() {
        
    }
    
    @GET
    @Path("obtener/{numero}")
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param numeroSeguro: recibe el número de seguro.
     */
    public String obtener(@PathParam("numero") String numeroSeguro) {
        return null;
    }
    
    @POST
    @Path("registrar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param contenido: recibe el paciente en JSON.
     */
    public String registrar(String contenido) {
        return null;
    }
    
    @PUT
    @Path("modificar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param contenido: recibe el paciente en JSON.
     */
    public String modificar(String contenido) {
        return null;
    }
}
