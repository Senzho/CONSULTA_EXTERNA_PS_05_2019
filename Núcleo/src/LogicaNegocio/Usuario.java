package LogicaNegocio;

/**
 * @author Victor Javier
 * @version 1.0
 * @created 18-may.-2019 06:27:03 p. m.
 */
public class Usuario {

    private int idUsuario;
    private String nombreUsuario;
    private String contraseña;
    private Personal personalUsuario;

    public Usuario() {

    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Personal getPersonalUsuario() {
        return personalUsuario;
    }

    public void setPersonalUsuario(Personal personalUsuario) {
        this.personalUsuario = personalUsuario;
    }

    public boolean registrarUsuario() {
        return false;
    }

    public boolean modificar() {
        return false;
    }

    /**
     *
     * @param nombre
     * @param contraseña
     */
    public int iniciarSesion(String nombre, String contraseña) {
        return 0;
    }
}//end Usuario
