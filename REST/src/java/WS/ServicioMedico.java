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
@Path("Medico")
public class ServicioMedico {
    @Context
    private UriInfo contexto;
    
    public ServicioMedico() {
        
    }
    
    @GET
    @Path("obtener/{numero}")
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param numeroPersonal: recibe el número de personal.
     */
    public String obtener(@PathParam("numero") String numeroPersonal) {
        return numeroPersonal;
    }
    
    @POST
    @Path("registrar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param contenido: recibe el medico en JSON.
     */
    public String registrar(String contenido) {
        return contenido;
    }
    
    @POST
    @Path("registrarentrada/{numero}/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param numeroConsultorio: recibe el número de consultorio.
     * @param idMedico: recibe el identificador del medico.
     */
    public String registrarEntrada(@PathParam("numero") String numeroConsultorio, @PathParam("id") int idMedico) {
        return numeroConsultorio + idMedico;
    }
    
    @POST
    @Path("registrarsalida/{fecha}/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param fecha: recibe la fecha.
     * @param idMedico: recibe el identificador del medico.
     */
    public String registrarSalida(@PathParam("fecha") String fecha, @PathParam("id") int idMedico) {
        return fecha + idMedico;
    }
    
    @PUT
    @Path("modificar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param contenido: recibe el medico en JSON.
     */
    public String modificar(String contenido) {
        return contenido;
    }
    
    @PUT
    @Path("cambiarestado/{estado}/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param estado: recibe el estado del medico.
     * @param idMedico: recibe el identificador del medico.
     */
    public String cambiarEstado(@PathParam("estado") boolean estado, @PathParam("id") int idMedico) {
        return estado ? "activo" : "inactivo" + idMedico;
    }
}
