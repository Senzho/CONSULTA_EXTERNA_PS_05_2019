<?php
if ( ! defined('BASEPATH')) exit('No direct script access allowed');
interface_exists('IRecepcionista', FALSE) OR require_once(APPPATH.'libraries/IRecepcionista.php');

require APPPATH . 'vendor/autoload.php';
use GuzzleHttp\Psr7\Request;
use GuzzleHttp\Client;

class RecepcionistaModelo implements IRecepcionista {

    public function __get($attr) {
        return CI_Controller::get_instance()->$attr;
    }

    public function __construct(){

    }

    public function registrar($recepcionista){

    }

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
    public function obtenerRecepcionista($rfc){
        $cliente = new Client(['base_uri'=>'http://192.168.43.126:8080']);
        $token = $this->session->get_userdata("token");
        $peticion = new Request('GET','/ConsultaExterna_WS/webresources/Personal/obtenerrfc/'.$rfc.'/'.$token,[]);
        $respuesta = $cliente->send($peticion, []);
        $json = json_decode($respuesta->getBody());
        return $this->getJSONObject($json);
    }
    public function obtenerRecepcionistaId($numeroPersonal){
         $cliente = new Client(['base_uri'=>'http://192.168.43.126:8080']);
        $token = $this->session->userdata('token');
        $peticion = new Request('GET','/ConsultaExterna_WS/webresources/Personal/obteneridusuario/'.$numeroPersonal.'/'.$token,[]);
        $respuesta = $cliente->send($peticion, []);
        $json = json_decode($respuesta->getBody());
        return $this->getJSONObject($json);
    }

    private function getJSONObject($JSONObject){
        $recepcionistaJSON = $JSONObject->personal;
        $recepcionista = new Recepcionista();
        if(!$JSONObject->token){
            $recepcionista->setNumeroPersonal(0);
        }else{
          $this->session->set_userdata('rcf', $recepcionistaJSON->prRfc);
            $recepcionista->setRfc($recepcionistaJSON->prRfc);
            $recepcionista->setNumeroTelefono($recepcionistaJSON->perNumTelefono);
            $recepcionista->setNombre($recepcionistaJSON->perNombres);
            $recepcionista->setApellido($recepcionistaJSON->perApellidos);
            $recepcionista->setFechaNacimiento($recepcionistaJSON->perFechaNac);
            $recepcionista->setSexo($recepcionistaJSON->perSexo);
            $recepcionista->setTurno($recepcionistaJSON->perTurno);
            $recepcionista->setNumeroPersonal($recepcionistaJSON->perNumPersonal);
            $recepcionista->setEstado($recepcionistaJSON->perEstado);
        }
        return $recepcionista;
    }
}
