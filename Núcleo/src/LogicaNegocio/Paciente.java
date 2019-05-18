package LogicaNegocio;

import java.util.Date;

/**
 * @author Victor Javier
 * @version 1.0
 * @created 18-may.-2019 06:27:02 p. m.
 */
public class Paciente {

	private String nombre;
	private String numeroSeguro;
	private String numeroTelefono;
	private Date fechaNacimiento;
	private char sexo;
	private String alergias;

	public Paciente(){

	}

	public boolean registrarPaciente(){
            return false;
	}

	public int obtenerEdad(){
            return 0;
	}

	public boolean modificarPaciente(){
            return false;
	}

	/**
	 * 
	 * @param numeroSeguro
	 */
	public Paciente obtenerPaciente(String numeroSeguro){
            return null;
	}
}//end Paciente