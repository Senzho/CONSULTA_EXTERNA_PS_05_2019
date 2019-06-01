package LogicaNegocio;

import Recursos.DatosConsulta;
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
    private IConsulta iConsulta;

    public Consulta() {
        this.iConsulta = null;
    }
    private DatosConsulta validarDatosConsulta(){
        DatosConsulta consultaValida = DatosConsulta.VALIDO;
        if(this.fecha.toString().trim().equals("") || this.fecha == null){
            consultaValida = DatosConsulta.FECHA_VACIA;
        }else if(this.horaInicio.toString().trim().equals("") || this.horaInicio == null){
            consultaValida = DatosConsulta.HORA_INICIO_VACIA;
        }else if(this.horaFin.toString().trim().equals("") || this.horaFin == null){
            consultaValida = DatosConsulta.HORA_FIN_VACIA;
        }else if(this.diagnostico.length() > 70){
            consultaValida = DatosConsulta.DIAGNOSTICO_LARGO;
        }else if(this.diagnostico.length() == 0 || this.diagnostico == null){
            consultaValida = DatosConsulta.DIAGNOSTICO_VACIO;
        }else if(this.temperatura == 0.0){
            consultaValida = DatosConsulta.TEMPERATURA_VACIA;
        }else if(this.peso == 0.0){
            consultaValida = DatosConsulta.TEMPERATURA_VACIA;
        }else if(this.peso == 0.0){
            consultaValida = DatosConsulta.PESO_VACIO;
        }else if(this.presion == 0.0){
            consultaValida = DatosConsulta.PRESION_VACIA;
        }else if(this.estatura == 0.0){
            consultaValida = DatosConsulta.ESTATURA_VACIA;
        }else if(this.idConsulta == 0){
            consultaValida = DatosConsulta.IDCONSULTA_VACIO;
        }else if(this.pacienteConsulta == null){
            consultaValida = DatosConsulta.PACIENTECONSULTA_VACIO;
        }else if(this.recetaConsulta == null){
            consultaValida = DatosConsulta.RECETACONSULTA_VACIO;
        }
        return consultaValida;
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
    public void setIConsulta(IConsulta iConsulta){
        this.iConsulta = iConsulta;
    }
    
    /**
     *
     * @param numeroSeguro
     * @param numeroPersonal
     * #Podría no recibir nada, ambos parámetros puede sacarlos del médico y del paciente#
     * #En la interfaz (this.iConsulta.registrar()) falta enviar propiamente la consulta (this)#
     */
    public boolean registrar(String numeroSeguro, String numeroPersonal) {
        DatosConsulta datosConsulta = this.validarDatosConsulta();
        boolean consultaRegistrada = false;
        if(datosConsulta == DatosConsulta.VALIDO){
            consultaRegistrada = this.iConsulta.registrar(numeroSeguro, numeroPersonal);
        }
        return consultaRegistrada;
    }

    /**
     *
     * @param numeroSeguro
     * #Podría no recibir el numero, lo puede sacar del paciente#
     */
    public List obtenerHistorialClinico(String numeroSeguro) {
        return iConsulta.obtenerHistorialClinico(numeroSeguro);
    }

    public List obtenerHistorialBiometrico() {
        return iConsulta.obtenerHistorialBiometrico(this.pacienteConsulta.getNumeroSeguro());
    }
}//end Consulta
