<?php

if ( ! defined('BASEPATH')) exit('No direct script access allowed');
require_once(APPPATH.'libraries/recursos/DatosConsulta.php');

class Consulta{

    private $fecha;
    private $horaInicio;
    private $horaFin;
    private $diagnostico;
    private $temperatura;
    private $peso;
    private $presion;
    private $estatura;
    private $idConsulta;
    private $pacienteConsulta;
    private $medicoConsulta;
    private $recetaConsulta;
    private $iConsulta;


    public function __get($attr) {
		return CI_Controller::get_instance()->$attr;
    }
    
    public function __construct(){
        $this->iConsulta = null;
    }

    private function validarDatosConsulta(){
        $consultavalida = DatosConsulta::VALIDO;
        if($this->fecha === '' || fecha === null){
            $consultaValida = DatosConsulta::FECHA_VACIA;
        }else if($this->horaInicio === "" || $this->horaInicio === null){
            $consultaValida = DatosConsulta::HORA_INICIO_VACIA;            
        }else if($this->horaFin === "" || $this->horaFin === null){
            $consultaValida = DatosConsulta::HORA_FIN_VACIA;
        }else if(strlen($this->diagnostico) > 70){
            $consultaValida = DatosConsulta::DIAGNOSTICO_LARGO;
        }else if (strlen($this->diagnostico) === 0 || $this->diagnostico === null){
            $consultaValida = DatosConsulta::DIAGNOSTICO_VACIO;
        }else if($this->temperatura === 0){
            $consultaValida = DatosConsulta::TEMPERATURA_VACIA;
        }else if($this->peso === 0){
            $consultaValida = DatosConsulta::PESO_VACIO;
        }else if($this->presion === 0){
            $consultaValida = DatosConsulta::PRESION_VACIA;
        }else if($this->estatura === 0.0){
            $consultaValida = DatosConsulta::ESTATURA_VACIA;
        }else if($this->idConsulta === 0){
            $consultaValida = DatosConsulta::IDCONSULTA_VACIO;
        }else if($this->pacienteConsulta === null){
            $consultaValida = DatosConsulta::PACIENTECONSULTA_VACIO;
        }else if($this->recetaConsulta === null){
            $consultaValida = DatosConsulta::RECETACONSULTA_VACIO;
        }
        return $consultaValida;
    }


    public function getFecha() {
        return $this->fecha;
    }

    public function setFecha($fecha) {
        $this->fecha = $fecha;
    }

    public function getHoraInicio() {
        return $this->horaInicio;
    }

    public function setHoraInicio($horaInicio) {
        $this->horaInicio = $horaInicio;
    }

    public function getHoraFin() {
        return $this->horaFin;
    }

    public function setHoraFin($horaFin) {
        $this->horaFin = $horaFin;
    }

    public function getDiagnostico() {
        return $this->diagnostico;
    }

    public function setDiagnostico($diagnostico) {
        $this->diagnostico = $diagnostico;
    }

    public function getTemperatura() {
        return $this->temperatura;
    }

    public function setTemperatura($temperatura) {
        $this->temperatura = $temperatura;
    }

    public function getPeso() {
        return $this->peso;
    }

    public function setPeso($peso) {
        $this->peso = $peso;
    }

    public function getPresion() {
        return $this->presion;
    }

    public function setPresion($presion) {
        $this->presion = $presion;
    }

    public function getEstatura() {
        return $this->estatura;
    }

    public function setEstatura($estatura) {
        $this->estatura = $estatura;
    }

    public function getIdConsulta() {
        return $this->idConsulta;
    }

    public function setIdConsulta($idConsulta) {
        $this->idConsulta = $idConsulta;
    }

    public function getPacienteConsulta() {
        return $this->pacienteConsulta;
    }

    public function setPacienteConsulta($pacienteConsulta) {
        $this->pacienteConsulta = $pacienteConsulta;
    }

    public function getMedicoConsulta() {
        return $this->medicoConsulta;
    }

    public function setMedicoConsulta($medicoConsulta) {
        $this->medicoConsulta = $medicoConsulta;
    }

    public function getRecetaConsulta() {
        return $this->recetaConsulta;
    }

    public function setRecetaConsulta($recetaConsulta) {
        $this->recetaConsulta = $recetaConsulta;
    }

    public function setIConsulta($iConsulta){
        $this->iConsulta = $iConsulta;
    }

    public function registrar($numeroSeguro, $numeroPersonal) {
        $datosConsulta = $this->validarDatosConsulta();
        $consultaRegistrada = false;
        if($datosConsulta === DatosConsulta::VALIDO){
            $consultaRegistrada = $this->iConsulta->registrar(numeroSeguro, numeroPersonal);
        }
        return $consultaRegistrada;
    }

    public function obtenerHistorialClinico($numeroSeguro) {
        return $this->iConsulta->obtenerHistorialClinico($numeroSeguro);
    }
    
    public function obtenerHistorialBiometrico() {
        return $this->iConsulta->obtenerHistorialBiometrico($this->pacienteConsulta->getNumeroSeguro());
    }
}