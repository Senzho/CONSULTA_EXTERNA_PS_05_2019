package WS;

import DataAccess.controladores.MedicamentoJpaController;
import DataAccess.entidades.Medicamento;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import org.json.JSONArray;

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
        boolean resultadoToken = this.tokenValido(token);
        Respuesta respuesta = new Respuesta(resultadoToken);
        if (resultadoToken) {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ConsultaExterna_WSPU");
            MedicamentoJpaController medicamentoJpaController = new MedicamentoJpaController(entityManagerFactory);
            try {
                List<Medicamento> medicamentos = medicamentoJpaController.findMedicamentoEntities();
                JSONArray jArreglo = new JSONArray(medicamentos);
                respuesta.getJson().put("medicamentos", jArreglo);
            } catch(Exception exception) {
                respuesta.getJson().put("medicamentos", new JSONArray("[]"));
            }
        }
        return respuesta.toString();
    }
    
    @GET
    @Path("obtenerrecetados/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param token: recibe el token de sesión.
     */
    public String obtenerRecetados(@PathParam("token") String token) {
        if (this.tokenValido(token)) {
            
        }
        return null;
    }
}
