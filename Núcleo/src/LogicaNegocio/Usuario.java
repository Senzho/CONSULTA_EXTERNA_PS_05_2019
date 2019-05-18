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
	public Personal m_Personal;

	public Usuario(){

	}

	public boolean registrarUsuario(){
            return false;
	}

	public boolean modificar(){
            return false;
	}

	/**
	 * 
	 * @param nombre
	 * @param contraseña
	 */
	public int iniciarSesion(String nombre, String contraseña){
            return 0;
	}
}//end Usuario