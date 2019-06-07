<?php

if ( ! defined('BASEPATH')) exit('No direct script access allowed');
require_once(APPPATH.'libraries/recursos/DatosReceta.php');

class Receta{
    private $folio;
    private $instrucciones;
    private $fechaVencimiento;
    private $medicamentosReceta;
    private $iReceta;    

    public function __get($attr) {
		return CI_Controller::get_instance()->$attr;
    }
    
    public function __construct(){
        
    }

    private function validarDatos(){
        $datosReceta = DatosReceta::VALIDA;
        if ($this->folio == "" || $this->folio == null){
            $datosReceta = DatosReceta::FOLIO_VACIO;
        } else if ($this->instrucciones == "" || $this->instrucciones == null){
            $datosReceta = DatosReceta::INSTRUCCIONES_VACIAS;
        } else if ($this->fechaVencimiento == null){
            $datosReceta = DatosReceta::FECHA_VENCIMIENTO_VACIA;
        }
        return $datosReceta;
    }

    public function getFolio() {
        return $this->folio;
    }

    public function setFolio($folio) {
        $this->folio = $folio;
    }

    public function getInstrucciones() {
        return $this->instrucciones;
    }

    public function setInstrucciones($instrucciones) {
        $this->instrucciones = $instrucciones;
    }

    public function getFechaVencimiento() {
        return $this->fechaVencimiento;
    }

    public function setFechaVencimiento($fechaVencimiento) {
        $this->fechaVencimiento = $fechaVencimiento;
    }

    public function getMedicamentosReceta() {
        return $this->medicamentosReceta;
    }

    public function setMedicamentoReceta($medicamentosReceta) {
        $this->medicamentosReceta = $medicamentosReceta;
    }

    public function getiReceta() {
        return $this->iReceta;
    }

    public function setiReceta($iReceta) {
        $this->iReceta = $iReceta;
    }
     /**
     *
     * @param idConsulta
     * @return 
     */
    public function registrar($idConsulta) {
        $validacion = $this->validarDatos();
        if ($validacion == DatosReceta::VALIDA){
            $validacion = $this->iReceta->registrar($this, $idConsulta) ? DatosReceta::EXITO : DatosReceta::ERROR_ALMACENAMIENTO;
        }
        return $validacion;
    }

    /**
     * #Recuerdo que esto es para el reporte, se obtendrían de algun periodo en especial?#
     * @param fechaMenor: recibe la fecha desde la cual se desea el histórico.
     * @param fechaMayor: recibe la fecha hasta la cual se desea el histórico.
     * @return 
     */
    //Agregadas fechas, no recibía nada.
    public function obtenerMedicamentosRecetados($fechaMenor, $fechaMayor) {
        return $this->iReceta->obtenerMedicamentosRecetados();
    }
}