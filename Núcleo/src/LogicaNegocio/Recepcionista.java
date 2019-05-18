package LogicaNegocio;

import java.util.Date;
import java.util.List;

/**
 * @author Victor Javier
 * @version 1.0
 * @created 18-may.-2019 06:27:03 p. m.
 */
public class Recepcionista extends Personal {

	public Recepcionista(){

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
	 * @param numeroSeguro
	 */
	public boolean agregarConsulta(String numeroSeguro){
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
	 * @param fecha
	 */
	public List obtenerCitas(Date fecha){
            return null;
	}
}//end Recepcionista