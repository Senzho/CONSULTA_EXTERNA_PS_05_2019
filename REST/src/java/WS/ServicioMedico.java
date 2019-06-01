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
@Path("Medico")
public class ServicioMedico {
    @Context
    private UriInfo contexto;
    
    public ServicioMedico() {
        
    }
    
    @DELETE
    @Path("eliminar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param idMedico: recibe el identificador del medico.
     */
    public String cambiarEstado(@PathParam("id") int idMedico) {
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
     * @param contenido: recibe el medico en JSON.
     */
    public String registrar(String contenido) {
        return null;
    }
    
    @POST
    @Path("registrarentrada/{numero}/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param numeroConsultorio: recibe el número de consultorio.
     * @param idMedico: recibe el identificador del medico.
     */
    public String registrarEntrada(@PathParam("numero") String numeroConsultorio, @PathParam("id") int idMedico) {
        return null;
    }
    
    @POST
    @Path("registrarsalida/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param idMedico: recibe el identificador del medico.
     */
    public String registrarSalida(@PathParam("id") int idMedico) {
        return null;
    }
    
    @PUT
    @Path("modificar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param contenido: recibe el medico en JSON.
     */
    public String modificar(String contenido) {
        return null;
    }
}
