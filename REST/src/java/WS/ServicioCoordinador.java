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
@Path("Coordinador")
public class ServicioCoordinador {
    @Context
    private UriInfo contexto;
    
    public ServicioCoordinador() {
        
    }
    
    @DELETE
    @Path("eliminar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param idCoordinador: recibe el identificador del coordinador.
     */
    public String eliminar(@PathParam("id") int idCoordinador) {
        return null;
    }
    
    @GET
    @Path("obtener/{numero}")
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param numeroPersonal: recibe el número de personal.
     */
    public String obtener(@PathParam("numero") String numeroPersonal) {
        return null;
    }
    
    @POST
    @Path("registrar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param contenido: recibe el coordinador en JSON.
     */
    public String registrar(String contenido) {
        return null;
    }
    
    @POST
    @Path("registrarentrada/{numero}/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param numero: recibe el número de consultorio.
     * @param idCoordinador: recibe el identificador del coordinador.
     */
    public String registrarEntrada(@PathParam("numero") String numeroConsultorio, @PathParam("id") int idCoordinador) {
        return null;
    }
    
    @POST
    @Path("registrarsalida/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param idCoordinador: recibe el identificador del coordinador.
     */
    public String registrarSalida(@PathParam("id") int idCoordinador) {
        return null;
    }
    
    @PUT
    @Path("modificar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param contenido: recibe el coordinador en JSON.
     */
    public String modificar(String contenido) {
        return null;
    }
}
