<?php

if ( ! defined('BASEPATH')) exit('No direct script access allowed');
require_once(APPPATH.'libraries/recursos/DatosPaciente.php');

class Paciente{
    
    private $nombre;
    private $apellido;
    private $numeroSeguro;
    private $numeroTelefono;
    private $fechaNacimiento;
    private $sexo;
    private $alergias;
    private $iPaciente;

    public function __get($attr) {
		return CI_Controller::get_instance()->$attr;
    }
    
    public function __construct(){
        
    }

    private function validarDatos(){
        $datosPaciente = DatosPaciente::VALIDO;
        if ($this->nombre == "" || $this->nombre == null){
            $datosPaciente = DatosPaciente::NOMBRE_VACIO;
        } else if (strlen($this->nombre) > 70){
            $datosPaciente = DatosPaciente::NOMBRE_LARGO;
        } else if ($this->apellido == "" || $this->apellido == null){
            $datosPaciente = DatosPaciente::APELLIDO_VACIO;
        } else if (strlen($this->apellido) > 70){
            $datosPaciente = DatosPaciente::APELLIDO_LARGO;
        } else if ($this->numeroSeguro == ""){
            $datosPaciente = DatosPaciente::NUMERO_SEGURO_VACIO;
        } else if ($this->fechaNacimiento == null){
            $datosPaciente = DatosPaciente::FECHA_NACIMIENTO_VACIA;
        } else if ($this->sexo == ""){
            $datosPaciente = DatosPaciente::SEXO_VACIO;
        } else if ($this->alergias == "" || $this->alergias == null){
            $datosPaciente = DatosPaciente::ALERGIAS_VACIAS;
        } else if (strlen($this->alergias) > 200){
            $datosPaciente = DatosPaciente::ALERGIAS_LARGAS;
        }
        return $datosPaciente;
    }

    public function getNombre() {
        return $this->nombre;
    }

    public function setNombre($nombre) {
        $this->nombre = $nombre;
    }

    public function getNumeroSeguro() {
        return $this->numeroSeguro;
    }

    public function setNumeroSeguro($numeroSeguro) {
        $this->numeroSeguro = $numeroSeguro;
    }

    public function getNumeroTelefono() {
        return $this->numeroTelefono;
    }

    public function setNumeroTelefono($numeroTelefono) {
        $this->numeroTelefono = $numeroTelefono;
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

    public function getAlergias() {
        return $this->alergias;
    }

    public function setAlergias($alergias) {
        $this->alergias = $alergias;
    }

    public function getApellido() {
        return $this->apellido;
    }

    public function setApellido($apellido) {
        $this->apellido = $apellido;
    }

    public function getiPaciente() {
        return $this->iPaciente;
    }

    public function setiPaciente($iPaciente) {
        $this->iPaciente = $iPaciente;
    }



    public function obtenerEdad() {

    }

    public function registrarPaciente() {
        $registro = $this->validarDatos();
        if ($registro == DatosPaciente::VALIDO){
            if ($this->obtenerPaciente($this->numeroSeguro) == null){
                $registro = $this->iPaciente->registrarPaciente($this)? DatosPaciente::EXITO : DatosPaciente::ERROR_ALMACENAMIENTO;
            }
        }
        return $registro;
    }
    
    public function modificarPaciente() {
        $modificacion = $this->validarDatos();
        if ($modificacion == DatosPaciente::VALIDO){
            $modificacion = $this->iPaciente->modificarPaciente($this) ? DatosPaciente::EXITO : DatosPaciente::ERROR_ALMACENAMIENTO;
        }
        return $modificacion;
    }

    /**
     *
     * @param numeroSeguro
     * @return 
     */
    public function obtenerPaciente($numeroSeguro) {
        return $this->iPaciente->obtenerPaciente($numeroSeguro);
    }

}