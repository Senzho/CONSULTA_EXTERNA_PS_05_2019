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
public class ServicioMedicamento extends ServicioSeguro {
    @Context
    private UriInfo contexto;
    
    public ServicioMedicamento() {
        
    }
    
    @GET
    @Path("obtener/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param token: recibe el token de sesión.
     */
    public String obtener(@PathParam("token") String token) {
        if (this.tokenValido(token)) {
            
        }
        return null;
    }
    
    @GET
    @Path("obtenerrecetados/{desde}/{hasta}/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param fechaMenor: recibe la fecha desde la cual se quiere el histórico.
     * @param fechaMayor: recibe la fecha hasta la cual se requiere el histórico.
     * @param token: recibe el token de sesión.
     */
    public String obtenerRecetados(@PathParam("desde") String fechaMenor, @PathParam("hasta") String fechaMayor, @PathParam("token") String token) {
        if (this.tokenValido(token)) {
            
        }
        return null;
    }
}
