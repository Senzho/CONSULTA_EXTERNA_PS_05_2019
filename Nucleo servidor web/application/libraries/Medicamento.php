<?php
class Medicamento{
    private $codigo;
    private $nombre;
    private $gramaje;
    private $fechaCaducidad;
    private $iMedicamento;

    public function __get($attr) {
		return CI_Controller::get_instance()->$attr;
    }
    
    public function __construct(){
        
    }

    public function getCodigo() {
        return $this->codigo;
    }

    public function setCodigo($codigo) {
        $this->codigo = $codigo;
    }

    public function getNombre() {
        return $this->nombre;
    }

    public function setNombre($nombre) {
        $this->nombre = $nombre;
    }

    public function getGramaje() {
        return $this->gramaje;
    }

    public function setGramaje($gramaje) {
        $this->gramaje = $gramaje;
    }

    public function getFechaCaducidad() {
        return $this->fechaCaducidad;
    }

    public function setFechaCaducidad($fechaCaducidad) {
        $this->fechaCaducidad = $fechaCaducidad;
    }

    public function getiMedicamento() {
        return $this->iMedicamento;
    }

    public function setiMedicamento($iMedicamento) {
        $this->iMedicamento = $iMedicamento;
    }

    public function obtenerMedicamentos() {
        return $this->iMedicamento->obtenerMedicamentos();
    }
}