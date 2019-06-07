<?php

if ( ! defined('BASEPATH')) exit('No direct script access allowed');
interface_exists('ICoordinador', FALSE) OR require_once(APPPATH.'libraries/ICoordinador.php');

class CoordinadorModelo implements ICoordinador{
    public function registrar($coordinador){
        echo $coordinador->getNombre();
    }
    public function modificar($coordinador){

    }
    public function registrarEntrada($numeroPersonal, $numeroConsultorio){

    }
    public function registrarSalida($numeroPersonal){

    }
    public function eliminar($numeroPersonal){

    }
    public function obtenerCoordinador($numeroPersonal){

    }
}