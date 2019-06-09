package WS;

import DataAccess.controladores.MedicamentoJpaController;
import DataAccess.controladores.RecetasJpaController;
import DataAccess.entidades.Medicamento;
import DataAccess.entidades.Recetas;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
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
@Path("Receta")
public class ServicioReceta extends ServicioSeguro {
    @Context
    private UriInfo contexto;
    
    public ServicioReceta() {
        
    }
    
    @POST
    @Path("registrar/{token}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param contenido: recibe la receta en JSON.
     * @param token: recibe el token de sesión.
     */
    public String registrar(String contenido, @PathParam("token") String token) {
        boolean resultadoToken = this.tokenValido(token);
        Respuesta respuesta = new Respuesta(resultadoToken);
        if (resultadoToken) {
            JSONObject jObjeto = new JSONObject(contenido);
            Recetas receta = new Recetas(jObjeto.getJSONObject("receta"));
            JSONArray jArreglo = jObjeto.getJSONArray("medicamentos");
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ConsultaExterna_WSPU");
            MedicamentoJpaController medicamentoJpaController = new MedicamentoJpaController(entityManagerFactory);
            List<Medicamento> medicamentos = new ArrayList<>();
            for (int i = 0; i < jArreglo.length(); i ++) {
                JSONObject jsObjeto = jArreglo.getJSONObject(i);
                Medicamento medicamento = medicamentoJpaController.findMedicamento(jsObjeto.getInt("medCodigo"));
                medicamentos.add(medicamento);
            }
            receta.setMedicamentoCollection(medicamentos);
            RecetasJpaController recetasJpaController = new RecetasJpaController(entityManagerFactory);
            try {
                recetasJpaController.create(receta);
                respuesta.getJson().put("registrada", true);
            } catch(Exception excepcion) {
                respuesta.getJson().put("registrada", false);
            }
        }
        return respuesta.toString();
    }
}
