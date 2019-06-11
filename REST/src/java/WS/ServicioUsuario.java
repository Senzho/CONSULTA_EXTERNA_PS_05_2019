package WS;

import DataAccess.controladores.PersonalJpaController;
import DataAccess.controladores.SesionesJpaController;
import DataAccess.controladores.UsuariosJpaController;
import DataAccess.entidades.Sesiones;
import DataAccess.entidades.Usuarios;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
    @Path("obtener/{nombre}/{contraseña}")
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param nombreUsuario: recibe el nombre de usuario.
     * @param contraseña: recibe la contraseña del usuario.
     */
    public String obtener(@PathParam("nombre") String nombreUsuario, @PathParam("contraseña") String contraseña) {
        Respuesta respuesta = new Respuesta(true);
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ConsultaExterna_WSPU");
        UsuariosJpaController usuariosJpaController = new UsuariosJpaController(entityManagerFactory);
        PersonalJpaController personalJpaController = new PersonalJpaController(entityManagerFactory);
        SesionesJpaController sesionesJpaController = new SesionesJpaController(entityManagerFactory);
        try {
            Usuarios usuario = usuariosJpaController.obtenerPorCredenciales(nombreUsuario, contraseña);
            Sesiones sesion = new Sesiones();
            sesionesJpaController.create(personalJpaController.obtenerPorIdUsuario(usuario), sesion);
            respuesta.getJson().put("usuario", new JSONObject(usuario));
            respuesta.getJson().put("tokenGenerado", sesion.getToken());
        } catch (Exception excepcion) {
            respuesta.getJson().put("usuario", new JSONObject("{'usuId': '0'}"));
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
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ConsultaExterna_WSPU");
            UsuariosJpaController controladorUsuario = new UsuariosJpaController(entityManagerFactory);
            try {
                Usuarios usuario = new Usuarios(new JSONObject(contenido));
                controladorUsuario.create(usuario);
                respuesta.getJson().put("registrado", true);
                respuesta.getJson().put("usuario", new JSONObject(usuario));
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
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ConsultaExterna_WSPU");
            UsuariosJpaController controladorUsuario = new UsuariosJpaController(entityManagerFactory);
            try {
                controladorUsuario.edit(new Usuarios(new JSONObject(contenido)));
                respuesta.getJson().put("actualizado", true);
            } catch(Exception excepcion) {
                respuesta.getJson().put("actualizado", false);
            }
        }
        return respuesta.toString();
    }
}
