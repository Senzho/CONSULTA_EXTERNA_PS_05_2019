package LogicaNegocio;

/**
 * @author Victor Javier
 * @version 1.0
 * @created 18-may.-2019 06:27:02 p. m.
 */
public class Medico extends Personal {

	public Medico(){

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
}//end Medico