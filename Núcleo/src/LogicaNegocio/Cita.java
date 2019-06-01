package LogicaNegocio;

import java.util.Date;

/**
 * @author Victor Javier
 * @version 1.0
 * @created 18-may.-2019 06:27:02 p. m.
 */
public class Cita {

    private int idCita;
    private Date horaReserva;
    private int estado;
    private Paciente pacienteCita;
    private Medico medicoCita;
    private ICita iCita;

    public Cita() {

    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public Date getHoraReserva() {
        return horaReserva;
    }

    public void setHoraReserva(Date horaReserva) {
        this.horaReserva = horaReserva;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Paciente getPacienteCita() {
        return pacienteCita;
    }

    public void setPacienteCita(Paciente pacienteCita) {
        this.pacienteCita = pacienteCita;
    }

    public Medico getMedicoCita() {
        return medicoCita;
    }

    public void setMedicoCita(Medico medicoCita) {
        this.medicoCita = medicoCita;
    }

    public ICita getiCita() {
        return iCita;
    }

    public void setiCita(ICita iCita) {
        this.iCita = iCita;
    }
 
    /**
     *
     * @param numeroSeguro
     * @param numeroPersonal
     * #Podría no recibir ningún parámetro, los números los puede sacar del médico y el paciente, y la fecha la tiene como atributo#
     * #La interfaz podría enviar la propia cita (this)#
     */
    public boolean registrar(String numeroSeguro, String numeroPersonal, Date horaReserva) {
        return this.iCita.registrar(numeroSeguro, numeroPersonal, horaReserva);
    }
}//end Cita
