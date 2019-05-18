package LogicaNegocio;

import java.util.Date;

/**
 * @author Victor Javier
 * @version 1.0
 * @created 18-may.-2019 06:27:02 p. m.
 */
public abstract class Personal {

	private String rfc;
	private String numeroTelefono;
	private String numeroPersonal;
	private String nombre;
	private String apellido;
	private Date fechaNacimiento;
	private char sexo;
	private String turno;

	public Personal(){

	}

	public boolean registrar(){
            return false;
	}

	public boolean modificar(){
            return false;
	}

	/**
	 * 
	 * @param hora
	 * @param numeroConsultorio
	 */
	public boolean registrarEntrada(Time hora, String numeroConsultorio){
            return false;
	}

	/**
	 * 
	 * @param hora
	 */
	public boolean registrarSalida(Time hora){
            return false;
	}

	/**
	 * 
	 * @param estado
	 */
	public boolean cambiarEstado(boolean estado){
            return false;
	}

	/**
	 * 
	 * @param idUsuario
	 */
	public Personal obtenerPersonal(int idUsuario){
            return null;
	}
}//end Personal