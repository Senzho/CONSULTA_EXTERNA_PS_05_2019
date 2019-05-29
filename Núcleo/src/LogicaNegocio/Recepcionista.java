package LogicaNegocio;

import Recursos.DatosPersonal;
import java.util.Date;
import java.util.List;

/**
 * @author Victor Javier
 * @version 1.0
 * @created 18-may.-2019 06:27:03 p. m.
 */
public class Recepcionista extends Personal {
    private IRecepcionista iRecepcionista;
    
    public Recepcionista() {
        this.iRecepcionista = null;
    }
    private DatosPersonal validarDatosPersonalesRecepcionista(){
        DatosPersonal datosRecepcionista = DatosPersonal.VALIDO;
        if(this.getNumeroTelefono().trim().equals("") || this.getNumeroTelefono() == null){
            datosRecepcionista = DatosPersonal.NUMERO_TELEFONO_VACIO;
        }else if(this.getNumeroTelefono().trim().length() > 10){
            datosRecepcionista = DatosPersonal.NUMERO_TELEFONO_LARGO;
        }else if(this.getNumeroTelefono().trim().length() < 10){
            datosRecepcionista = DatosPersonal.NUMERO_TELEFONO_CORTO;
        }else if(this.getNombre().trim().equals("") || this.getNombre() == null){
            datosRecepcionista = DatosPersonal.NOMBRE_VACIO;
        }else if(this.getNombre().trim().length() > 70){
            datosRecepcionista = DatosPersonal.NOMBRE_LARGO;
        }else if(this.getApellido().trim().equals("") || this.getApellido() == null){
            datosRecepcionista = DatosPersonal.APELLIDO_VACIO;
        }else if(this.getApellido().trim().length() > 70){
            datosRecepcionista = DatosPersonal.APELLIDO_LARGO;
        }else if(this.getFechaNacimiento().toString().trim().equals("") || this.getFechaNacimiento() == null){
            datosRecepcionista = DatosPersonal.FECHA_VACIA;
        }else if(this.getFechaNacimiento() == new Date()){
            datosRecepcionista = DatosPersonal.FECHA_ACTUAL;
        }if(this.getSexo()==' '){
            datosRecepcionista = DatosPersonal.SEXO_VACIO;
        }else  if(this.getRfc().trim().equals("") || this.getRfc() == null){
            datosRecepcionista = DatosPersonal.RFC_VACIO;
        }else if(this.getRfc().trim().length() > 13){
            datosRecepcionista = DatosPersonal.RFC_LARGO;
        }else if(this.getRfc().trim().length()< 13){
            datosRecepcionista = DatosPersonal.RFC_CORTO;
        }else if(this.getNumeroPersonal().trim().equals("") || this.getNumeroPersonal() == null){
            datosRecepcionista = DatosPersonal.NUMERO_PERSONAL_VACIO;
        }else if(this.getTurno().trim().equals("") || this.getTurno() == null){
            datosRecepcionista = DatosPersonal.TURNO_VACIO;
        }
        return datosRecepcionista;
    }
    
    private DatosPersonal validarDatosRecepcionista(){
        DatosPersonal datosRecepcionista = DatosPersonal.VALIDO;
        if(this.validarDatosPersonalesRecepcionista() != DatosPersonal.VALIDO){
            datosRecepcionista = this.validarDatosPersonalesRecepcionista();
        }
        return datosRecepcionista;
    }
    public void setiRecepcionista(IRecepcionista iRecepcionista){
        this.iRecepcionista = iRecepcionista;
    }
    public DatosPersonal registrar() {
        DatosPersonal datosRecepcionista = this.validarDatosRecepcionista();
        if(datosRecepcionista == DatosPersonal.VALIDO){
           if(this.obtenerRecepcionista(this.getNumeroPersonal()) == null){
               datosRecepcionista = this.iRecepcionista.registrar(this)? DatosPersonal.EXITO : DatosPersonal.ERROR_ALMACENAMIENTO;
           }
        }
        return datosRecepcionista;
    }

    public DatosPersonal modificar() {
        DatosPersonal modificar = this.validarDatosRecepcionista();
        if(modificar == DatosPersonal.VALIDO){
            modificar = this.iRecepcionista.modificar(this)? DatosPersonal.EXITO : DatosPersonal.ERROR_ALMACENAMIENTO;
        }
        return modificar;
    }

    /**
     *
     * @param hora
     * @param numeroConsultorio
     */
    public boolean registrarEntrada(Date hora, String numeroConsultorio) {
        boolean entradaRegistrada = false;
        if(hora != null){
            if(numeroConsultorio != null || !numeroConsultorio.trim().equals("")){
                entradaRegistrada = this.iRecepcionista.registrarEntrada(hora, numeroConsultorio);
            }
        }
        return entradaRegistrada;        
    }

    /**
     *
     * @param hora
     */
    public boolean registrarSalida(Date hora) {
        boolean salidaRegistrada = false;
        if(hora != null){
            salidaRegistrada = this.iRecepcionista.registrarSalida(hora);
        }
        return salidaRegistrada;
    }

    /**agregarConsulta
     *
     * @param numeroSeguro
     *///modificado, recibia numeroSeguro
    public boolean agregarConsulta(Consulta consulta) {
        return this.iRecepcionista.agregarConsulta(consulta);
    }

    /**
     *
     * @param estado
     */
    public boolean cambiarEstado(boolean estado) {
        return this.iRecepcionista.cambiarEstado(estado);
    }

    /**
     *
     * @param fecha
     */
    public List obtenerCitas(Date fecha) {
        return this.iRecepcionista.obtenerCitas(fecha);
    }
    public Recepcionista obtenerRecepcionista(String numeroPersonal){
        return iRecepcionista.obtenerRecepcionista(numeroPersonal);
    }
}//end Recepcionista