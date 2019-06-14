<?php

if ( ! defined('BASEPATH')) exit('No direct script access allowed');
interface_exists('IConsulta', FALSE) OR require_once(APPPATH.'libraries/IConsulta.php');

require APPPATH . 'vendor/autoload.php';
use GuzzleHttp\Psr7\Request;
use GuzzleHttp\Client;

class ConsultaModelo implements IConsulta{
    public function __get($attr) {
        return CI_Controller::get_instance()->$attr;
  	}
    public function registrar($consulta, $numeroSeguro,$rfc, $folio){
        $registrado = false;
        $cliente = new Client();
        $consultaJSON = $this->getJSON($consulta);
        $respuesta = $cliente->post('http://localhost:8080/ConsultaExterna_WS/webresources/Consulta/registrar/'.$numeroSeguro.'/'.$rfc.'/'.$folio.'/'.$this->session->userdata('token'),[GuzzleHttp\RequestOptions::JSON => $consultaJSON]);
        $respuesta = json_decode($respuesta->getBody());
        if($respuesta->token){
            if($respuesta->registrado){
                $registrado = TRUE;
            }
        }
        return $registrado;
    }

    public function obtenerHistorialClinico($numeroSeguro){
        echo $numeroSeguro;
    }

    public function obtenerHistorialBiometrico($numeroSeguro){
        echo $numeroSeguro;
    }
    public function getJSON($consulta){
        return array('consulta'=>array('conDiagnostico'=>$consulta->getDiagnostico(),'conEstatura'=>$consulta->getEstatura(),'conFecha'=>$consulta->getFecha(),'conHoraInicio'=>$consulta->getHoraInicio(),'conHoraFin'=>$consulta->getHoraFin(),'conPeso'=>$consulta->getPeso(),'conPresion'=>$consulta->getPresion(),'conTemperaura'=>$consulta->getTemperatura()));
    }
}