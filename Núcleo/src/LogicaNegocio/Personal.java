package LogicaNegocio;

import Recursos.DatosPersonal;
import java.util.Date;

/**
 * @author Victor Javier
 * @version 1.0
 * @created 18-may.-2019 06:27:02 p. m.
 */
public abstract class Personal {

    protected String rfc;
    protected String numeroTelefono;
    protected String numeroPersonal;
    protected String nombre;
    protected String apellido;
    protected Date fechaNacimiento;
    protected char sexo;
    protected String turno;

    public Personal() {

    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public String getNumeroPersonal() {
        return numeroPersonal;
    }

    public void setNumeroPersonal(String numeroPersonal) {
        this.numeroPersonal = numeroPersonal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

   public abstract DatosPersonal registrar();

    public abstract DatosPersonal modificar();

    /**
     *
     * @param hora
     * @param numeroConsultorio
     */
    public abstract boolean registrarEntrada(String numeroConsultorio);

    /**
     *
     * @param hora
     */
    public abstract boolean registrarSalida();

    /**
     *
     * @param estado
     */
    public abstract boolean eliminar();

    /**
     *
     * @param idUsuario
     */
    public abstract Personal obtenerPersonal(String numeroPersonal);
}//end Personal
