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

    @Override
    public DatosPersonal registrar() {
        DatosPersonal datosPersonal = this.validarDatosRecepcionista();
        if(datosPersonal == DatosPersonal.VALIDO){
            if(this.obtenerPersonal(this.getNumeroPersonal())== null){
                datosPersonal = this.iRecepcionista.registrar(this)? 
                        DatosPersonal.EXITO : DatosPersonal.ERROR_ALMACENAMIENTO;
            }
        }
        return datosPersonal;
    }

    @Override
    public DatosPersonal modificar() {
        DatosPersonal modificar = this.validarDatosRecepcionista();
        if(modificar == DatosPersonal.VALIDO){
            modificar = this.iRecepcionista.modificar(this)?
                   DatosPersonal.EXITO : DatosPersonal.ERROR_ALMACENAMIENTO;
        }
        return modificar;
    }

    @Override
    public boolean registrarEntrada(String numeroConsultorio) {
        boolean entradaRegistrada = false;
        if(!numeroConsultorio.trim().equals("")){
            entradaRegistrada = this.iRecepcionista.registrarEntrada(numeroConsultorio, this.getNumeroPersonal());
        }
        
        return entradaRegistrada; 
    }

    @Override
    public boolean registrarSalida() {
        return this.iRecepcionista.registrarSalida(this.getNumeroPersonal());
    }

    @Override
    public boolean eliminar() {
       return this.iRecepcionista.eliminar(this.getNumeroPersonal());
    }

    @Override
    public Personal obtenerPersonal(String numeroPersonal) {
        return iRecepcionista.obtenerRecepcionista(numeroPersonal);
    }

    public boolean agregarConsulta(Consulta consulta) {
        return this.iRecepcionista.agregarConsulta(consulta);
    }

    public List obtenerCitas(Date fecha) {
        return this.iRecepcionista.obtenerCitas(fecha);
    }
    
}//end Recepcionista