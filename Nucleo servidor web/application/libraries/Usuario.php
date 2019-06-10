<?php
class Usuario{
    private $idUsuario;
    private $nombreUsuario;
    private $contraseña;
    private $personalUsuario;
    private $iUsuario;
    private $rol;

    public function __get($attr) {
		return CI_Controller::get_instance()->$attr;
    }
    
    public function __construct(){
        
    }

    private function validarDatos(){
        $datosUsuario = DatosUsuario::VALIDO;
        if ($this->idUsuario < 1){
            $datosUsuario = DatosUsuario::ID_NEGATIVO;
        } else if ($this->nombreUsuario == "" || $this->nombreUsuario == null){
            $datosUsuario = DatosUsuario::NOMBRE_VACIO;
        } else if (strlen($this->nombreUsuario) > 15){
            $datosUsuario = DatosUsuario::NOMBRE_LARGO;
        } else if ($this->contraseña == "" || $this->contraseña == null){
            $datosUsuario = DatosUsuario::CONTRASEÑA_VACIA;
        } else if (strlen($this->contraseña) < 6){
            $datosUsuario = DatosUsuario::CONTRASEÑA_CORTA;
        } else if (strlen($this->contraseña) > 15){
            $datosUsuario = DatosUsuario::CONTRASEÑA_LARGA;
        }
        return $datosUsuario;
    }
    public function getIdUsuario() {
        return $this->idUsuario;
    }

    public function setIdUsuario($idUsuario) {
        $this->idUsuario = $idUsuario;
    }

    public function getNombreUsuario() {
        return $this->nombreUsuario;
    }

    public function setNombreUsuario($nombreUsuario) {
        $this->nombreUsuario = $nombreUsuario;
    }

    public function getContraseña() {
        return $this->contraseña;
    }

    public function setContraseña($contraseña) {
        $this->contraseña = $contraseña;
    }

    public function getPersonalUsuario() {
        return $this->personalUsuario;
    }

    public function setPersonalUsuario($personalUsuario) {
        $this->personalUsuario = $personalUsuario;
    }

    public function getiUsuario() {
        return $this->iUsuario;
    }

    public function setiUsuario($iUsuario) {
        $this->iUsuario = $iUsuario;
    }
    public function getRol(){
        return $this->rol;
    }
    public function setRol($rol){
        $this->rol = $rol;
    }
    
    public function registrarUsuario() {
        $validacion = $this->validarDatos();
        if ($validacion == DatosUsuario::VALIDO){
            $validacion = $this->iUsuario->registrarUsuario($this) ? DatosUsuario::EXITO : DatosUsuario::ERROR_ALMACENAMIENTO;
        }
        return $validacion;
    }

    public function modificar() {
        $modificacion = $this->validarDatos();
        if ($modificacion == DatosUsuario::VALIDO){
            $modificacion = $this->iUsuario->modificar($this) ? DatosUsuario::EXITO : DatosUsuario::ERROR_ALMACENAMIENTO;
        }
        return $modificacion;
    }

    /**
     *
     * @param nombre
     * @param contraseña
     * @return 
     */
    public function iniciarSesion() {
        return $this->iUsuario->iniciarSesion($this->getNombreUsuario(), $this->getContraseña());
    }

}