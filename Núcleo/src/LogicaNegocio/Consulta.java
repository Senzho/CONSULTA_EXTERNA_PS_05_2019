package LogicaNegocio;

import java.util.Date;
import java.util.List;

/**
 * @author Victor Javier
 * @version 1.0
 * @created 18-may.-2019 06:27:02 p. m.
 */
public class Consulta {

	private Date fecha;
	private Time horaInicio;
	private Time horaFin;
	private String diagnostico;
	private float temperatura;
	private float peso;
	private float presion;
	private float estatura;
	private int idConsulta;
	public Paciente m_Paciente;
	public Medico m_Medico;
	public Receta m_Receta;

	public Consulta(){

	}

	/**
	 * 
	 * @param numeroSeguro
	 * @param numeroPersonal
	 */
	public boolean registrar(String numeroSeguro, String numeroPersonal){
		return false;
	}

	/**
	 * 
	 * @param numeroSeguro
	 */
	public List obtenerHistorialClinico(String numeroSeguro){
            return null;
	}

	public List obtenerHistorialBiometrico(){
            return null;
	}
}//end Consulta