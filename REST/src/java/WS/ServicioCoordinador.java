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
@Path("Coordinador")
public class ServicioCoordinador {
    @Context
    private UriInfo contexto;
    
    public ServicioCoordinador() {
        
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
     * @param contenido: recibe el coordinador en JSON.
     */
    public String registrar(String contenido) {
        return contenido;
    }
    
    @POST
    @Path("registrarentrada/{fecha}/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param fecha: recibe la fecha.
     * @param idCoordinador: recibe el identificador del coordinador.
     */
    public String registrarEntrada(@PathParam("fecha") String fecha, @PathParam("id") int idCoordinador) {
        return fecha + idCoordinador;
    }
    
    @POST
    @Path("registrarsalida/{fecha}/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param fecha: recibe la fecha.
     * @param idCoordinador: recibe el identificador del coordinador.
     */
    public String registrarSalida(@PathParam("fecha") String fecha, @PathParam("id") int idCoordinador) {
        return fecha + idCoordinador;
    }
    
    @PUT
    @Path("modificar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param contenido: recibe el coordinador en JSON.
     */
    public String modificar(String contenido) {
        return contenido;
    }
    
    @PUT
    @Path("cambiarestado/{estado}/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param estado: recibe el estado del coordinador.
     * @param idCoordinador: recibe el identificador del coordinador.
     */
    public String cambiarEstado(@PathParam("estado") boolean estado, @PathParam("id") int idCoordinador) {
        return estado ? "activo" : "inactivo" + idCoordinador;
    }
}
