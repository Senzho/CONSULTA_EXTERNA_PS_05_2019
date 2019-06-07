<?php
if ( ! defined('BASEPATH')) exit('No direct script access allowed');
interface_exists('IRecepcionista', FALSE) OR require_once(APPPATH.'libraries/IRecepcionista.php');

class RecepcionistaModelo implements IRecepcionista {

    public function modificar($recepcionista){

    }
    public function registrarEntrada($numeroConsultorio, $numeroPersonal){

    }
    public function registrarSalida($numeroPersonal){
        
    }
    public function eliminar($numeroPersonal){

    }
    public function agregarConsulta($consulta){

    }
    public function obtenerCitas($fecha){

    }
    public function obtenerRecepcionista($numeroPersonal){

    }
}