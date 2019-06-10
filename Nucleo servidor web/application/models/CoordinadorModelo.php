<?php

if ( ! defined('BASEPATH')) exit('No direct script access allowed');
interface_exists('ICoordinador', FALSE) OR require_once(APPPATH.'libraries/ICoordinador.php');

require APPPATH . 'vendor/autoload.php';
use GuzzleHttp\Psr7\Request;
use GuzzleHttp\Client;

class CoordinadorModelo implements ICoordinador{

     public function __get($attr) {
        return CI_Controller::get_instance()->$attr;
    }
    
    public function __construct(){
             
    }

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
    public function obtenerCoordinador($rfc){
        $cliente = new Client(['base_uri'=>'http://192.168.43.126:8080']);
        $token = $this->session->get_userdata("token");
        $peticion = new Request('GET','/ConsultaExterna_WS/webresources/Personal/obtenerrfc/'.$rfc.'/'.$token,[]);
        $respuesta = $cliente->send($peticion, []);
        $json = json_decode($respuesta->getBody());
        return $this->getJSONObject($json);
    }
    
    public function obtenerCoordinadorId($numeroPersonal){
        $cliente = new Client(['base_uri'=>'http://192.168.43.126:8080']);
        $token = $this->session->userdata('token');
        $peticion = new Request('GET','/ConsultaExterna_WS/webresources/Personal/obteneridusuario/'.$numeroPersonal.'/'.$token,[]);
        $respuesta = $cliente->send($peticion, []);
        $json = json_decode($respuesta->getBody());
        return $this->getJSONObject($json);
    }

    private function getJSONObject($JSONObject){
        $coordinadorJSON = $JSONObject->personal;
        $coordinador = new Coordinador();
        if(!$JSONObject->token){
            $coordinador->setNumeroPersonal(0);
        }else{
            $coordinador->setRfc($coordinadorJSON->prRfc);
            $coordinador->setNumeroTelefono($coordinadorJSON->perNumTelefono);
            $coordinador->setNombre($coordinadorJSON->perNombres);
            $coordinador->setApellido($coordinadorJSON->perApellidos);
            $coordinador->setFechaNacimiento($coordinadorJSON->perFechaNac);
            $coordinador->setSexo($coordinadorJSON->perSexo);
            $coordinador->setTurno($coordinadorJSON->perTurno);
            $coordinador->setNumeroPersonal($coordinadorJSON->perNumPersonal);
            $coordinador->setEstado($coordinadorJSON->perEstado);
        }
        return $coordinador;
    }
}