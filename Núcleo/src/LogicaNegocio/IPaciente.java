package LogicaNegocio;

/**
 *
 * @author Victor Javier
 */
public interface IPaciente {
    public Paciente obtenerPaciente(String numeroSeguro);
    public boolean registrarPaciente(Paciente paciente);
    public boolean modificarPaciente(Paciente paciente);
}
