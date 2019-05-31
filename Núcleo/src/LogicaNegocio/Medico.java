package LogicaNegocio;

import Recursos.DatosMedico;
import Recursos.DatosPaciente;
import Recursos.DatosPersonal;
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
    private DatosPersonal validarDatos(){
        DatosPersonal datosMedico = DatosPersonal.VALIDO;
        if (this.nombre == null || this.nombre.trim().equals("")){
            datosMedico = DatosPersonal.NOMBRE_VACIO;
        } else if (this.nombre.trim().length() > 70){
            datosMedico = DatosPersonal.NOMBRE_LARGO;
        } else if (this.apellido.trim().equals("") || this.apellido == null){
            datosMedico = DatosPersonal.APELLIDO_VACIO;
        } else if (this.apellido.trim().length() > 70){
            datosMedico = DatosPersonal.APELLIDO_LARGO;
        } else if (this.rfc.trim().equals("")){
            datosMedico = DatosPersonal.RFC_VACIO;
        } else if (this.fechaNacimiento == null){
            datosMedico = DatosPersonal.FECHA_NACIMIENTO_VACIA;
        } else if (this.sexo == ' '){
            datosMedico = DatosPersonal.SEXO_VACIO;
        } else if (this.numeroTelefono.trim().equals("") || this.numeroTelefono == null){
            datosMedico = DatosPersonal.NUMERO_TELEFONO_VACIO;
        } else if (this.numeroTelefono.trim().length() > 12){
            datosMedico = DatosPersonal.NUMERO_TELEFONO_LARGO;
        } else if(this.numeroTelefono.trim().length() < 10){
            datosMedico = DatosPersonal.NUMERO_TELEFONO_CORTO;
        } else if (this.numeroPersonal.trim().equals("") || this.numeroPersonal == null){
            datosMedico = DatosPersonal.NUMERO_PERSONAL_VACIO;
        } else if(this.turno.trim().equals("") || this.turno == null){
            datosMedico = DatosPersonal.TURNO_VACIO;
        }
        return datosMedico;
    }
    
    /*public DatosPaciente registrarMedico() {
        DatosPaciente registro = this.validarDatos();
        if (registro == DatosPaciente.VALIDO){
            if (this.obtenerMedico(this.numeroPersonal) == null){
                registro = this.iMedico.registrarMedico(this) ? DatosPaciente.EXITO : DatosPaciente.ERROR_ALMACENAMIENTO;
            }
        }
        return registro;
    }*/

    /*public DatosPaciente modificarMedico() {
        DatosPaciente registro = this.validarDatos();
        if (registro == DatosPaciente.VALIDO){
                registro = this.iMedico.modificarMedico(this) ? DatosPaciente.EXITO : DatosPaciente.ERROR_ALMACENAMIENTO;
            
        }
        return registro;
    }*/

    public Medico obtenerMedico(String numeroPersonal) {
        return iMedico.obtenerMedico(numeroPersonal);
    }

    /**
     *
     * @param numeroConsultorio
     * @return 
     */
    @Override
    public boolean registrarEntrada(String numeroConsultorio) {
        boolean respuesta = false;
        if (!numeroConsultorio.trim().equals("") || numeroConsultorio != null ) {
            respuesta = iMedico.registrarEntrada(numeroConsultorio, this.numeroPersonal );
        }
        return respuesta;
    }

    /**
     *
     * @return 
     */
    @Override
    public boolean registrarSalida() {
        return iMedico.registrarSalida(numeroPersonal);
    }

    @Override
    public DatosPersonal registrar() {
        DatosPersonal registro = this.validarDatos();
        if (registro == DatosPersonal.VALIDO){
            if (this.obtenerMedico(this.numeroPersonal) == null){
                registro = this.iMedico.registrar(this) ? DatosPersonal.EXITO : DatosPersonal.ERROR_ALMACENAMIENTO;
            }
        }
        return registro;
    }

    @Override
    public DatosPersonal modificar() {
        DatosPersonal registro = this.validarDatos();
        if (registro == DatosPersonal.VALIDO){
                registro = this.iMedico.modificar(this) ? DatosPersonal.EXITO : DatosPersonal.ERROR_ALMACENAMIENTO;
            
        }
        return registro;
    }

    @Override
    public boolean eliminar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Personal obtenerPersonal(String numeroPersonal) {
        return iMedico.obtenerMedico(numeroPersonal);
    }
}//end Medico
