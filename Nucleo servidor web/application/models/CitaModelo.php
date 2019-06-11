<?php
if ( ! defined('BASEPATH')) exit('No direct script access allowed');
interface_exists('ICita', FALSE) OR require_once(APPPATH.'libraries/ICita.php');

require APPPATH . 'vendor/autoload.php';
use GuzzleHttp\Psr7\Request;
use GuzzleHttp\Client;

class CitaModelo implements ICita {

	public function __get($attr) {
        return CI_Controller::get_instance()->$attr;
    }

    public function __construct(){

    }
    public function registrar($numeroSeguro, $rfc, $cita){
        $registrada = FALSE;
        $cliente = new Client();
        $citaJSON = $this->getJSON($cita);
        $respuesta = $cliente->post('http://192.168.43.126:8080/ConsultaExterna_WS/webresources/Cita/registrar/'.$numeroSeguro.'/'.$rfc.'/'.$this->session->userdata('token'),[GuzzleHttp\RequestOptions::JSON => $citaJSON]);
        $respuesta = json_decode($respuesta->getBody());  
        if($respuesta->token){
            if($respuesta->registrada){
                $registrada = TRUE;
            }
        }
        return $registrada;
    }
    //citEstado, citFechaHoraReserva
    private function getJSON($cita){
        return array('citEstado'=>$cita->getEstado(),'citFechaHoraReserva'=>$cita->getHoraReserva());
    }
}