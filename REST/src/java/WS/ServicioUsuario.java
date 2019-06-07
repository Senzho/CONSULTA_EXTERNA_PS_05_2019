package WS;

import DataAccess.controladores.UsuariosJpaController;
import DataAccess.entidades.Usuarios;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
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
import org.json.JSONObject;

/**
 *
 * @author Victor Javier
 */
@Path("Usuario")
public class ServicioUsuario extends ServicioSeguro {
    @Context
    private UriInfo contexto;
    
    public ServicioUsuario() {
        
    }
    
    @GET
    @Path("obtener/{nombre}/{contraseña}/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param numeroUsuario: recibe el nombre de usuario.
     * @param contraseña: recibe la contraseña del usuario.
     * @param token: recibe el token de sesión.
     */
    public String obtener(@PathParam("nombre") String nombreUsuario, @PathParam("contraseña") String contraseña, @PathParam("token") String token) {
        boolean resultadoToken = this.tokenValido(token);
        Respuesta respuesta = new Respuesta(resultadoToken);
        if (resultadoToken) {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ConsultaExterna_WSPU");
            Query query = entityManagerFactory.createEntityManager().createNamedQuery("Usuarios.findByUsuNombreAndUsuContrasena");
            query.setParameter("usuNombre", nombreUsuario);
            query.setParameter("usuContrasena", contraseña);
            Usuarios usuario = (Usuarios) query.getSingleResult();
            JSONObject jObjeto = new JSONObject(usuario);
            respuesta.getJson().put("usuario", jObjeto);
        }
        return respuesta.toString();
    }
    
    @POST
    @Path("registrar/{token}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param contenido: recibe el usuario en JSON.
     * @param token: recibe el token de sesión.
     */
    public String registrar(String contenido, @PathParam("token") String token) {
        boolean resultadoToken = this.tokenValido(token);
        Respuesta respuesta = new Respuesta(resultadoToken);
        if (resultadoToken) {
            JSONObject jObjeto = new JSONObject(contenido);
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ConsultaExterna_WSPU");
            Usuarios usuario = new Usuarios();
            usuario.setUsuId(0);
            usuario.setUsuNombre(jObjeto.getString("nombreUsuario"));
            usuario.setUsuContrasena(jObjeto.getString("contraseña"));
            usuario.setUsuRol(jObjeto.getString("rol"));
            UsuariosJpaController controladorUsuario = new UsuariosJpaController(entityManagerFactory);
            try {
                controladorUsuario.create(usuario);
                respuesta.getJson().put("registrado", true);
            } catch(Exception excepcion) {
                System.out.println(excepcion.getMessage());
                respuesta.getJson().put("registrado", false);
            }
        }
        return respuesta.toString();
    }
    
    @PUT
    @Path("modificar/{token}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param contenido: recibe el usuario en JSON.
     * @param token: recibe el token de sesión.
     */
    public String modificar(String contenido, @PathParam("token") String token) {
        boolean resultadoToken = this.tokenValido(token);
        Respuesta respuesta = new Respuesta(resultadoToken);
        if (resultadoToken) {
            JSONObject jObjeto = new JSONObject(contenido);
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ConsultaExterna_WSPU");
            Usuarios usuario = new Usuarios();
            usuario.setUsuId(jObjeto.getInt("id"));
            usuario.setUsuNombre(jObjeto.getString("nombreUsuario"));
            usuario.setUsuContrasena(jObjeto.getString("contraseña"));
            usuario.setUsuRol(jObjeto.getString("rol"));
            UsuariosJpaController controladorUsuario = new UsuariosJpaController(entityManagerFactory);
            try {
                controladorUsuario.edit(usuario);
                respuesta.getJson().put("actualizado", true);
            } catch(Exception excepcion) {
                System.out.println(excepcion.getMessage());
                respuesta.getJson().put("actualizado", false);
            }
        }
        return respuesta.toString();
    }
}
