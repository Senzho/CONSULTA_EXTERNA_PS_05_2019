package LogicaNegocio;

import Recursos.DatosPersonal;
import java.util.Date;

/**
 * @author Victor Javier
 * @version 1.0
 * @created 18-may.-2019 06:27:02 p. m.
 */
public class Coordinador extends Personal {
    private ICoordinador iCoordinador;
    
        
    public Coordinador() {

    }
    
    private DatosPersonal validarDatosPersonalesCoordinador(){
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
    public void setICoordinador(ICoordinador iCoordinador){
        this.iCoordinador = iCoordinador;
    }
    
    private DatosPersonal validarDatosCoordinador(){
        DatosPersonal datosCoordinador = DatosPersonal.VALIDO;
        if(this.validarDatosPersonalesCoordinador() != DatosPersonal.VALIDO){
            datosCoordinador = this.validarDatosPersonalesCoordinador();
        }
        return datosCoordinador;
    }
  
    @Override
    public DatosPersonal registrar() {
       DatosPersonal registrar = this.validarDatosCoordinador();
       if(registrar == DatosPersonal.VALIDO){
           if(this.obtenerCoordinador(this.getNumeroPersonal()) == null){
               registrar = this.iCoordinador.registrar(this)?DatosPersonal.EXITO:DatosPersonal.ERROR_ALMACENAMIENTO;
           }
       }
       return registrar;
    }

    @Override
    public DatosPersonal modificar() {
        DatosPersonal modificar = this.validarDatosCoordinador();
        if(modificar == DatosPersonal.VALIDO){
            modificar = this.iCoordinador.modificar(this)?DatosPersonal.EXITO:DatosPersonal.ERROR_ALMACENAMIENTO;
        }
        return modificar;
    }

    /**
     *
     * @param hora
     * @param numeroConsultorio
     *///borre numero de consultorio, registra la entrada de el o la de alguien más?
    public boolean registrarEntrada(Date hora) {
        boolean entradaRegistrada = false;
        if(hora != null){
            entradaRegistrada = this.iCoordinador.registrarEntrada(hora);
        }
        return entradaRegistrada;       
    }

    /**
     *
     * @param hora
     * @return 
     */
    @Override
    public boolean registrarSalida(Date hora) {
        boolean salidaRegistrada = false;
        if(hora != null){
            salidaRegistrada = this.iCoordinador.registrarSalida(hora);
        }
        return salidaRegistrada;
    }

    /**
     *
     * @param estado
     * @return 
     */
    @Override
    public boolean cambiarEstado(boolean estado) {
        return iCoordinador.cambiarEstado(estado);
    }
    
    public Coordinador obtenerCoordinador(String numeroPersonal){
        return iCoordinador.obtenerCoordinador(numeroPersonal);
    }
}//end Coordinador
