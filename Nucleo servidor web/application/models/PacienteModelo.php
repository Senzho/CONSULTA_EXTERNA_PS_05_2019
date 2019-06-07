<?php

if ( ! defined('BASEPATH')) exit('No direct script access allowed');
interface_exists('IPaciente', FALSE) OR require_once(APPPATH.'libraries/IPaciente.php');

class PacienteModelo implements IPaciente{
    public function obtenerPaciente($numeroSeguro){

    }
    public function registrarPaciente($paciente){

    }
    public function modificarPaciente($paciente){
        
    }
}