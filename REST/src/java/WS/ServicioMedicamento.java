package WS;

import javax.ws.rs.GET;
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
@Path("Medicamento")
public class ServicioMedicamento {
    @Context
    private UriInfo contexto;
    
    public ServicioMedicamento() {
        
    }
    
    @GET
    @Path("obtener")
    @Produces(MediaType.APPLICATION_JSON)
    public String obtener() {
        return null;
    }
    
    @GET
    @Path("obtenerrecetados/{desde}/{hasta}")
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param fechaMenor: recibe la fecha desde la cual se quiere el histórico.
     * @param fechaMayor: recibe la fecha hasta la cual se requiere el histórico.
     */
    public String obtenerRecetados(@PathParam("desde") String fechaMenor, @PathParam("hasta") String fechaMayor) {
        return null;
    }
}
