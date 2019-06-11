<?php
class Cita{
    
    private $idCita;
    private $horaReserva;
    private $estado;
    private $pacienteCita;
    private $medicoCita;
    private $iCita;
    

    public function __get($attr) {
		return CI_Controller::get_instance()->$attr;
    }
    
    public function __construct(){
        
    }

    public function getIdCita(){
        return $this->idCita;
    }

    public function setIdCita($idCita){
        $this->idCita = $idCita;
    }

    public function getHoraReserva(){
        return $this->horaReserva;
    }
    public function setHoraReserva($horaReserva){
        $this->horaReserva = $horaReserva;
    }

    public function getEstado() {
        return $this->estado;
    }

    public function setEstado($estado) {
        $this->estado = $estado;
    }

    public function getPacienteCita() {
        return $this->pacienteCita;
    }

    public function setPacienteCita($pacienteCita) {
        $this->pacienteCita = $pacienteCita;
    }

    public function getMedicoCita() {
        return $this->medicoCita;
    }

    public function setMedicoCita($medicoCita) {
        $this->medicoCita = $medicoCita;
    }

    public function getiCita() {
        return $this->iCita;
    }

    public function setiCita(ICita $iCita) {
        $this->iCita = $iCita;
    }

    public function registrar($numeroSeguro, $rfc) {
        return $this->iCita->registrar($numeroSeguro, $rfc, $this);
    }

}