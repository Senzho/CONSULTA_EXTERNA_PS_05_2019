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
@Path("Cita")
public class ServicioCita {
    @Context
    private UriInfo contexto;
    
    public ServicioCita() {
        
    }
    
    @GET
    @Path("obtener/{fecha}")
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param fecha: recibe la fecha donde buscar las citas.
     */
    public String obtener(@PathParam("fecha") String fecha) {
        return null;
    }
    
    @POST
    @Path("registrar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param contenido: recibe la cita en JSON.
     */
    public String registrar(String contenido) {
        return null;
    }
}
