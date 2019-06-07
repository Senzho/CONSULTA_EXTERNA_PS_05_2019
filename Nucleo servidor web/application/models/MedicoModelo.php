<?php
if ( ! defined('BASEPATH')) exit('No direct script access allowed');
interface_exists('IMedico', FALSE) OR require_once(APPPATH.'libraries/IMedico.php');

class MedicoModelo implements IMedico {
    public function registrar($coordinador){
        echo $coordinador->getNombre();
    }

    public function modificar($coordinador){

    }

    public function registrarEntrada($numeroPersonal, $numeroConsultorio){

    }
    
    public function registrarSalida($numeroPersonal){

    }

    public function eliminar(){

    }

    public function obtenerMedico($numeroPersonal){

    }
}