package LogicaNegocio;

import Recursos.DatosPaciente;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author Victor Javier
 * @version 1.0
 * @created 18-may.-2019 06:27:02 p. m.
 */
public class Paciente {

    private String nombre;
    private String apellido;
    private String numeroSeguro;
    private String numeroTelefono;
    private Date fechaNacimiento;
    private char sexo;
    private String alergias;
    private IPaciente iPaciente;
    
    private DatosPaciente validarDatos(){
        DatosPaciente datosPaciente = DatosPaciente.VALIDO;
        if (this.nombre.trim().equals("") || this.nombre == null){
            datosPaciente = DatosPaciente.NOMBRE_VACIO;
        } else if (this.nombre.trim().length() > 70){
            datosPaciente = DatosPaciente.NOMBRE_LARGO;
        } else if (this.apellido.trim().equals("") || this.apellido == null){
            datosPaciente = DatosPaciente.APELLIDO_VACIO;
        } else if (this.apellido.trim().length() > 70){
            datosPaciente = DatosPaciente.APELLIDO_LARGO;
        } else if (this.numeroSeguro.trim().equals("")){
            datosPaciente = DatosPaciente.NUMERO_SEGURO_VACIO;
        } else if (this.fechaNacimiento == null){
            datosPaciente = DatosPaciente.FECHA_NACIMIENTO_VACIA;
        } else if (this.fechaNacimiento == new Date()){
            datosPaciente = DatosPaciente.FECHA_NACIMIENTO_ACTUAL;
        } else if (this.sexo == ' '){
            datosPaciente = DatosPaciente.FECHA_NACIMIENTO_VACIA;
        } else if (this.alergias.trim().equals("") || this.alergias == null){
            datosPaciente = DatosPaciente.ALERGIAS_VACIAS;
        } else if (this.alergias.trim().length() > 200){
            datosPaciente = DatosPaciente.ALERGIAS_LARGAS;
        }
        return datosPaciente;
    }

    public Paciente() {
        this.fechaNacimiento = new Date();
        this.iPaciente = null;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumeroSeguro() {
        return numeroSeguro;
    }

    public void setNumeroSeguro(String numeroSeguro) {
        this.numeroSeguro = numeroSeguro;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public IPaciente getiPaciente() {
        return iPaciente;
    }

    public void setiPaciente(IPaciente iPaciente) {
        this.iPaciente = iPaciente;
    }
    
    public int obtenerEdad() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fecha = this.fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formato);
        LocalDate fechaActual = LocalDate.now();
        Period periodo = Period.between(LocalDate.parse(fecha), fechaActual);
        return periodo.getYears();
    }

    public DatosPaciente registrarPaciente() {
        DatosPaciente registro = this.validarDatos();
        if (registro == DatosPaciente.VALIDO){
            if (this.obtenerPaciente(this.numeroSeguro) == null){
                registro = this.iPaciente.registrarPaciente(this) ? DatosPaciente.EXITO : DatosPaciente.ERROR_ALMACENAMIENTO;
            }
        }
        return registro;
    }
    
    public boolean modificarPaciente() {
        return false;
    }

    /**
     *
     * @param numeroSeguro
     */
    public Paciente obtenerPaciente(String numeroSeguro) {
        return iPaciente.obtenerPaciente(numeroSeguro);
    }
}//end Paciente
