package WS;

import DataAccess.controladores.PersonalJpaController;
import DataAccess.controladores.RegistrosJpaController;
import DataAccess.controladores.UsuariosJpaController;
import DataAccess.entidades.Personal;
import DataAccess.entidades.Registros;
import DataAccess.entidades.Usuarios;
import java.util.Date;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Victor Javier
 */
@Path("Personal")
public class ServicioPersonal extends ServicioSeguro {
    @Context
    private UriInfo contexto;
    
    public ServicioPersonal() {
        
    }
    
    @DELETE
    @Path("estado/{rfc}/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param rfc: recibe el rfc del recepcionista.
     * @param token: recibe el token de sesión.
     */
    public String cambiarEstado(@PathParam("rfc") String rfc, @PathParam("token") String token) {
        boolean resultadoToken = this.tokenValido(token);
        Respuesta respuesta = new Respuesta(resultadoToken);
        if (resultadoToken) {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ConsultaExterna_WSPU");
            PersonalJpaController personalJpaController = new PersonalJpaController(entityManagerFactory);
            try {
                respuesta.getJson().put("cambiado", personalJpaController.cambiarEstado(personalJpaController.findPersonal(rfc)));
            } catch(Exception excepcion) {
                respuesta.getJson().put("cambiado", false);
            }
        }
        return respuesta.toString();
    }
    
    @GET
    @Path("obtenerrfc/{rfc}/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param rfc: recibe el rfc del personal.
     * @param token: recibe el token de sesión.
     */
    public String obtenerRfc(@PathParam("rfc") String rfc, @PathParam("token") String token) {
        boolean resultadoToken = this.tokenValido(token);
        Respuesta respuesta = new Respuesta(resultadoToken);
        if (resultadoToken) {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ConsultaExterna_WSPU");
            PersonalJpaController personalJpaController = new PersonalJpaController(entityManagerFactory);
            try {
                respuesta.getJson().put("personal", new JSONObject(personalJpaController.obtenerPorRfc(rfc)));
            } catch(Exception excepcion) {
                System.out.println(excepcion.getMessage());
                respuesta.getJson().put("personal", new JSONObject("{'prRfc': ''}"));
            }
        }
        return respuesta.toString();
    }
    
    @GET
    @Path("obteneridusuario/{id}/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param idUsuario: recibe el rfc del personal.
     * @param token: recibe el token de sesión.
     */
    public String obtenerIdUsuario(@PathParam("id") int idUsuario, @PathParam("token") String token) {
        boolean resultadoToken = this.tokenValido(token);
        Respuesta respuesta = new Respuesta(resultadoToken);
        if (resultadoToken) {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ConsultaExterna_WSPU");
            PersonalJpaController personalJpaController = new PersonalJpaController(entityManagerFactory);
            UsuariosJpaController usuariosJpaController = new UsuariosJpaController(entityManagerFactory);
            try {
                Usuarios usuario = usuariosJpaController.findUsuarios(idUsuario);
                Personal personal = personalJpaController.obtenerPorIdUsuario(usuario);
                respuesta.getJson().put("personal", new JSONObject(personal));
            } catch(Exception excepcion) {
                respuesta.getJson().put("personal", new JSONObject("{'prRfc': ''}"));
            }
        }
        return respuesta.toString();
    }
    
    @GET
    @Path("obtenerrol/{rol}/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param rol: recibe el rol.
     * @param token: recibe el token de sesión.
     */
    public String obtenerRol(@PathParam("rol") String rol, @PathParam("token") String token) {
        boolean resultadoToken = this.tokenValido(token);
        Respuesta respuesta = new Respuesta(resultadoToken);
        if (resultadoToken) {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ConsultaExterna_WSPU");
            PersonalJpaController personalJpaController = new PersonalJpaController(entityManagerFactory);
            try {
                respuesta.getJson().put("personales", new JSONArray(personalJpaController.obtenerPorRol(rol)));
            } catch(Exception exception) {
                respuesta.getJson().put("personales", new JSONArray("[]"));
            }
        }
        return respuesta.toString();
    }
    
    @POST
    @Path("registrar/{id}/{token}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param contenido: recibe el personal en JSON.
     * @param idUsuario: recibe el identificador del usuario.
     * @param token: recibe el token de sesión.
     */
    public String registrar(String contenido, @PathParam("id") int idUsuario, @PathParam("token") String token) {
        boolean resultadoToken = this.tokenValido(token);
        Respuesta respuesta = new Respuesta(resultadoToken);
        if (resultadoToken) {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ConsultaExterna_WSPU");
            UsuariosJpaController usuariosJpaController = new UsuariosJpaController(entityManagerFactory);
            PersonalJpaController personalJpaController = new PersonalJpaController(entityManagerFactory);
            try {
                Personal personal = new Personal(new JSONObject(contenido));
                personal.setUsuariosUsuId(usuariosJpaController.findUsuarios(idUsuario));
                personalJpaController.create(personal);
                respuesta.getJson().put("registrado", true);
            } catch(Exception excepcion) {
                respuesta.getJson().put("registrado", false);
            }
        }
        return respuesta.toString();
    }
    
    @POST
    @Path("registrarentrada/{numero}/{rfc}/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param numeroConsultorio: recibe el número de consultorio.
     * @param rfc: recibe el rfc del recepcionista.
     * @param token: recibe el token de sesión.
     */
    public String registrarEntrada(@PathParam("numero") String numeroConsultorio, @PathParam("rfc") String rfc, @PathParam("token") String token) {
        boolean resultadoToken = this.tokenValido(token);
        Respuesta respuesta = new Respuesta(resultadoToken);
        if (resultadoToken) {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ConsultaExterna_WSPU");
            PersonalJpaController personalJpaController = new PersonalJpaController(entityManagerFactory);
            RegistrosJpaController registrosJpaController = new RegistrosJpaController(entityManagerFactory);
            Registros registros = new Registros();
            registros.setRegHoraEntrada(new Date());
            registros.setRegLugarEstadia(numeroConsultorio);
            registros.setPersonalPrRfc(personalJpaController.findPersonal(rfc));
            try {
                if (!registrosJpaController.existeSalidaNula(rfc)) {
                    registrosJpaController.create(registros);
                    respuesta.getJson().put("registrada", true);
                } else {
                    respuesta.getJson().put("registrada", false);
                    respuesta.getJson().put("error", "noSalida");
                }
            } catch(Exception excepcion) {
                respuesta.getJson().put("registrada", false);
                respuesta.getJson().put("error", "registro");
            }
        }
        return respuesta.toString();
    }
    
    @POST
    @Path("registrarsalida/{rfc}/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param rfc: recibe el rfc del recepcionista.
     * @param token: recibe el token de sesión.
     */
    public String registrarSalida(@PathParam("rfc") String rfc, @PathParam("token") String token) {
        boolean resultadoToken = this.tokenValido(token);
        Respuesta respuesta = new Respuesta(resultadoToken);
        if (resultadoToken) {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ConsultaExterna_WSPU");
            RegistrosJpaController registrosJpaController = new RegistrosJpaController(entityManagerFactory);
            try {
                if (!registrosJpaController.existeSalidaNula(rfc)) {
                    respuesta.getJson().put("registrada", false);
                    respuesta.getJson().put("error", "noEntrada");
                } else {
                    Registros registros = registrosJpaController.obtenerSalidaNula(rfc);
                    registros.setRegHoraSalida(new Date());
                    registrosJpaController.registrarSalida(registros);
                    respuesta.getJson().put("registrada", true);
                }
            } catch(Exception excepcion) {
                respuesta.getJson().put("registrada", false);
                respuesta.getJson().put("error", "registro");
            }
        }
        return respuesta.toString();
    }
    
    @PUT
    @Path("modificar/{token}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * @param contenido: recibe el recepcionista en JSON.
     * @param token: recibe el token de sesión.
     */
    public String modificar(String contenido, @PathParam("token") String token) {
        boolean resultadoToken = this.tokenValido(token);
        Respuesta respuesta = new Respuesta(resultadoToken);
        if (resultadoToken) {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ConsultaExterna_WSPU");
            PersonalJpaController personalJpaController = new PersonalJpaController(entityManagerFactory);
            try {
                respuesta.getJson().put("actualizado", personalJpaController.edit(new Personal(new JSONObject(contenido))));
            } catch(Exception excepcion) {
                respuesta.getJson().put("actualizado", false);
            }
        }
        return respuesta.toString();
    }
}
