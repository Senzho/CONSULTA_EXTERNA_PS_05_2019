package WS;

import DataAccess.controladores.MedicamentoJpaController;
import DataAccess.controladores.RecetasJpaController;
import DataAccess.entidades.Medicamento;
import DataAccess.entidades.Recetas;
import java.util.Collection;
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
import org.json.JSONObject;

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
                respuesta.getJson().put("medicamentos", new JSONArray(medicamentoJpaController.findMedicamentoEntities()));
            } catch(Exception excepcion) {
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
        boolean resultadoToken = this.tokenValido(token);
        Respuesta respuesta = new Respuesta(resultadoToken);
        if (resultadoToken) {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ConsultaExterna_WSPU");
            RecetasJpaController recetasJpaController = new RecetasJpaController(entityManagerFactory);
            try {
                JSONArray jArreglo = new JSONArray();
                for (Recetas recetas : recetasJpaController.findRecetasEntities()) {
                    Collection<Medicamento> medicamentos = recetas.getMedicamentoCollection();
                    recetas.setMedicamentoCollection(null);
                    JSONObject jObjeto = new JSONObject(recetas);
                    JSONArray jsArreglo = new JSONArray(medicamentos);
                    jObjeto.put("medicamentos", jsArreglo);
                    jArreglo.put(jObjeto);
                }
                respuesta.getJson().put("recetas", jArreglo);
            } catch(Exception excepcion) {
                respuesta.getJson().put("recetas", new JSONArray("[]"));
            }
        }
        return respuesta.toString();
    }
}
