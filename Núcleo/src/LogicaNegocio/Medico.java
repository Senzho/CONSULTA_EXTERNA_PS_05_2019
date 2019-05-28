package LogicaNegocio;

import Recursos.DatosMedico;
import Recursos.DatosPaciente;
import java.util.Date;

/**
 * @author Victor Javier
 * @version 1.0
 * @created 18-may.-2019 06:27:02 p. m.
 */
public class Medico extends Personal {

    IMedico iMedico;
    
    public Medico() {

    }

    public Medico(IMedico iMedico) {
        this.iMedico = iMedico;
    }

    public IMedico getiMedico() {
        return iMedico;
    }

    public void setiMedico(IMedico iMedico) {
        this.iMedico = iMedico;
    }
     private DatosMedico validarDatos(){
        DatosMedico datosMedico = DatosMedico.VALIDO;
        if (this.nombre == null || this.nombre.trim().equals("")){
            datosMedico = DatosMedico.NOMBRE_VACIO;
        } else if (this.nombre.trim().length() > 70){
            datosMedico = DatosMedico.NOMBRE_LARGO;
        } else if (this.apellido.trim().equals("") || this.apellido == null){
            datosMedico = DatosMedico.APELLIDO_VACIO;
        } else if (this.apellido.trim().length() > 70){
            datosMedico = DatosMedico.APELLIDO_LARGO;
        } else if (this.rfc.trim().equals("")){
            datosMedico = DatosMedico.RFC_VACIO;
        } else if (this.fechaNacimiento == null){
            datosMedico = DatosMedico.FECHA_NACIMIENTO_VACIA;
        } else if (this.sexo == ' '){
            datosMedico = DatosMedico.SEXO_VACIO;
        } else if (this.numeroTelefono.trim().equals("") || this.numeroTelefono == null){
            datosMedico = DatosMedico.NUMERO_TELEFONO_VACIO;
        } else if (this.numeroTelefono.trim().length() > 12){
            datosMedico = DatosMedico.NUMERO_TELEFONO_LARGO;
        } else if(this.numeroTelefono.trim().length() < 10){
            datosMedico = DatosMedico.NUMERO_TELEFONO_CORTO;
        } else if (this.numeroPersonal.trim().equals("") || this.numeroPersonal == null){
            datosMedico = DatosMedico.NUMERO_PERSONAL_VACIO;
        } else if(this.turno.trim().equals("") || this.turno == null){
            datosMedico = DatosMedico.TURNO_VACIO;
        }
        return datosMedico;
    }
    
    public DatosMedico registrarMedico() {
        DatosMedico registro = this.validarDatos();
        if (registro == DatosMedico.VALIDO){
            if (this.obtenerMedico(this.numeroPersonal) == null){
                registro = this.iMedico.registrarMedico(this) ? DatosMedico.EXITO : DatosMedico.ERROR_ALMACENAMIENTO;
            }
        }
        return registro;
    }

    public DatosMedico modificarMedico() {
        DatosMedico registro = this.validarDatos();
        if (registro == DatosMedico.VALIDO){
                registro = this.iMedico.modificarMedico(this) ? DatosMedico.EXITO : DatosMedico.ERROR_ALMACENAMIENTO;
            
        }
        return registro;
    }

    public Medico obtenerMedico(String numeroPersonal) {
        return iMedico.obtenerMedico(numeroPersonal);
    }

    /**
     *
     * @param numeroConsultorio
     */
    public boolean registrarEntrada(String numeroConsultorio) {
        boolean respuesta = false;
        if (!numeroConsultorio.trim().equals("") || numeroConsultorio != null ) {
            respuesta = iMedico.registrarEntrada(numeroConsultorio, this.numeroPersonal );
        }
        return respuesta;
    }

    /**
     *
     */
    public boolean registrarSalida() {
        return iMedico.registrarSalida();
    }

    /**
     *
     * @param estado
     */
    public boolean cambiarEstado(boolean estado) {
        return false;
    }
}//end Medico
