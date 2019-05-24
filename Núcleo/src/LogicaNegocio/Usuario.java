package LogicaNegocio;

import Recursos.DatosUsuario;

/**
 * @author Victor Javier
 * @version 1.0
 * @created 18-may.-2019 06:27:03 p. m.
 */
public class Usuario {

    private int idUsuario;
    private String nombreUsuario;
    private String contraseña;
    private Personal personalUsuario;
    private IUsuario iUsuario;
    
    private DatosUsuario validarDatos(){
        DatosUsuario datosUsuario = DatosUsuario.VALIDO;
        if (this.idUsuario < 1){
            datosUsuario = DatosUsuario.ID_NEGATIVO;
        } else if (this.nombreUsuario.trim().equals("") || this.nombreUsuario == null){
            datosUsuario = DatosUsuario.NOMBRE_VACIO;
        } else if (this.nombreUsuario.trim().length() > 15){
            datosUsuario = DatosUsuario.NOMBRE_LARGO;
        } else if (this.contraseña.trim().equals("") || this.contraseña == null){
            datosUsuario = DatosUsuario.CONTRASEÑA_VACIA;
        } else if (this.contraseña.trim().length() < 6){
            datosUsuario = DatosUsuario.CONTRASEÑA_CORTA;
        } else if (this.contraseña.trim().length() > 15){
            datosUsuario = DatosUsuario.CONTRASEÑA_LARGA;
        }
        return datosUsuario;
    }

    public Usuario() {
        this.idUsuario = 0;
        this.nombreUsuario = "";
        this.contraseña = "";
        this.iUsuario = null;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Personal getPersonalUsuario() {
        return personalUsuario;
    }

    public void setPersonalUsuario(Personal personalUsuario) {
        this.personalUsuario = personalUsuario;
    }

    public IUsuario getiUsuario() {
        return iUsuario;
    }

    public void setiUsuario(IUsuario iUsuario) {
        this.iUsuario = iUsuario;
    }
    
    public DatosUsuario registrarUsuario() {
        DatosUsuario validacion = this.validarDatos();
        if (validacion == DatosUsuario.VALIDO){
            validacion = this.iUsuario.registrarUsuario(this) ? DatosUsuario.EXITO : DatosUsuario.ERROR_ALMACENAMIENTO;
        }
        return validacion;
    }

    public DatosUsuario modificar() {
        DatosUsuario modificacion = this.validarDatos();
        if (modificacion == DatosUsuario.VALIDO){
            modificacion = this.iUsuario.modificar(this) ? DatosUsuario.EXITO : DatosUsuario.ERROR_ALMACENAMIENTO;
        }
        return modificacion;
    }

    /**
     *
     * @param nombre
     * @param contraseña
     * @return 
     */
    public int iniciarSesion(String nombre, String contraseña) {
        return this.iUsuario.iniciarSesion(nombre, contraseña);
    }
}//end Usuario
