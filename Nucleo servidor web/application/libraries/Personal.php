<?php
abstract class Personal{
    private $rfc;
    private $numeroTelefono;
    private $numeroPersonal;
    private $nombre;
    private $apellido;
    private $fechaNacimiento;
    private $sexo;
    private $turno;
    private $estado;

    public function __get($attr) {
		return CI_Controller::get_instance()->$attr;
    }
    
    public function __construct(){
        
    }
    
    public function getRfc() {
        return $this->rfc;
    }

    public function setRfc($rfc) {
        $this->rfc = $rfc;
    }

    public function getNumeroTelefono() {
        return $this->numeroTelefono;
    }

    public function setNumeroTelefono($numeroTelefono) {
        $this->numeroTelefono = $numeroTelefono;
    }

    public function getNumeroPersonal() {
        return $this->numeroPersonal;
    }

    public function setNumeroPersonal($numeroPersonal) {
        $this->numeroPersonal = $numeroPersonal;
    }

    public function getNombre() {
        return $this->nombre;
    }

    public function setNombre($nombre) {
        $this->nombre = $nombre;
    }

    public function getApellido() {
        return $this->apellido;
    }

    public function setApellido($apellido) {
        $this->apellido = $apellido;
    }

    public function getFechaNacimiento() {
        return $this->fechaNacimiento;
    }

    public function setFechaNacimiento($fechaNacimiento) {
        $this->fechaNacimiento = $fechaNacimiento;
    }

    public function getSexo() {
        return $this->sexo;
    }

    public function setSexo($sexo) {
        $this->sexo = $sexo;
    }

    public function getTurno() {
        return $this->turno;
    }

    public function setTurno($turno) {
        $this->turno = $turno;
    }
    public function getEstado(){
        return $this->estado;
    }
    public function setEstado($estado){
        $this->estado = $estado;
    }

    public abstract function registrar($idUsuario);
    public abstract function modificar();
    public abstract function registrarEntrada($numeroConsultorio);
    public abstract function registrarSalida();
    public abstract function eliminar();
    public abstract function obtenerPersonal($rfc);
    public abstract function obtenerPersonalId($idUsuario);
    public abstract function obtenerPersonalRol($rol);
}