<?php

if ( ! defined('BASEPATH')) exit('No direct script access allowed');
interface_exists('IConsulta', FALSE) OR require_once(APPPATH.'libraries/IConsulta.php');

class ConsultaModelo implements IConsulta{
    
    public function registrar($numeroSeguro,$numeroPersonal){
        echo $numeroSeguro;
    }

    public function obtenerHistorialClinico($numeroSeguro){
        echo $numeroSeguro;
    }

    public function obtenerHistorialBiometrico($numeroSeguro){
        echo $numeroSeguro;
    }
}