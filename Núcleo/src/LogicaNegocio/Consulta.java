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
    private Date horaInicio;
    private Date horaFin;
    private String diagnostico;
    private float temperatura;
    private float peso;
    private float presion;
    private float estatura;
    private int idConsulta;
    private Paciente pacienteConsulta;
    private Medico medicoConsulta;
    private Receta recetaConsulta;

    public Consulta() {

    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getPresion() {
        return presion;
    }

    public void setPresion(float presion) {
        this.presion = presion;
    }

    public float getEstatura() {
        return estatura;
    }

    public void setEstatura(float estatura) {
        this.estatura = estatura;
    }

    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public Paciente getPacienteConsulta() {
        return pacienteConsulta;
    }

    public void setPacienteConsulta(Paciente pacienteConsulta) {
        this.pacienteConsulta = pacienteConsulta;
    }

    public Medico getMedicoConsulta() {
        return medicoConsulta;
    }

    public void setMedicoConsulta(Medico medicoConsulta) {
        this.medicoConsulta = medicoConsulta;
    }

    public Receta getRecetaConsulta() {
        return recetaConsulta;
    }

    public void setRecetaConsulta(Receta recetaConsulta) {
        this.recetaConsulta = recetaConsulta;
    }

    /**
     *
     * @param numeroSeguro
     * @param numeroPersonal
     */
    public boolean registrar(String numeroSeguro, String numeroPersonal) {
        return false;
    }

    /**
     *
     * @param numeroSeguro
     */
    public List obtenerHistorialClinico(String numeroSeguro) {
        return null;
    }

    public List obtenerHistorialBiometrico() {
        return null;
    }
}//end Consulta
