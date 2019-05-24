package LogicaNegocio;

/**
 *
 * @author Victor Javier
 */
public interface IUsuario {
    public boolean registrarUsuario(Usuario usuario);
    public boolean modificar(Usuario usuario);
    public int iniciarSesion(String nombre, String contraseña);
}
