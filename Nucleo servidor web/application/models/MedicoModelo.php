<?php
if ( ! defined('BASEPATH')) exit('No direct script access allowed');
interface_exists('IMedico', FALSE) OR require_once(APPPATH.'libraries/IMedico.php');

require APPPATH . 'vendor/autoload.php';
use GuzzleHttp\Psr7\Request;
use GuzzleHttp\Client;

class MedicoModelo implements IMedico {


     public function __get($attr) {
        return CI_Controller::get_instance()->$attr;
    }
    
    public function __construct(){
             
    }

    public function registrar($medico,$idUsuario){
        $registrado = false;
        $cliente = new Client();
        $medicoJSON = $this->getJSON($medico);
        $respuesta = $cliente->post('http://192.168.43.126:8080/ConsultaExterna_WS/webresources/Personal/registrar/'.$idUsuario.'/'.$this->session->userdata('token'),[GuzzleHttp\RequestOptions::JSON => $usuarioJSON]);
         $respuesta = json_decode($respuesta->getBody());  
         if($respuesta->token){
            if($respuesta->registrado){
                $registrado = TRUE;
            }
        }
        return $registrado;
    }

    public function modificar($medico){

    }
    public function registrarEntrada($numeroPersonal, $numeroConsultorio){

    }
    public function registrarSalida($numeroPersonal){

    }
    public function eliminar(){

    }

    public function obtenerMedico($rfc){
        $cliente = new Client(['base_uri'=>'http://192.168.43.126:8080']);
        $token = $this->session->get_userdata("token");
        $peticion = new Request('GET','/ConsultaExterna_WS/webresources/Personal/obtenerrfc/'.$rfc.'/'.$token,[]);
        $respuesta = $cliente->send($peticion, []);
        $json = json_decode($respuesta->getBody());
        return $this->getJSONObject($json);
    }

    public function obtenerMedicoId($idUsuario){
        $cliente = new Client(['base_uri'=>'http://192.168.43.126:8080']);
        $token = $this->session->userdata('token');
        $peticion = new Request('GET','/ConsultaExterna_WS/webresources/Personal/obteneridusuario/'.$idUsuario.'/'.$token,[]);
        $respuesta = $cliente->send($peticion, []);
        $json = json_decode($respuesta->getBody());
        return $this->getJSONObject($json);
    }

    private function getJSONObject($JSONObject){
        $medicoJSON = $JSONObject->personal;
        $medico = new Medico();
        if(!$JSONObject->token){
            $medico->setNumeroPersonal(0);
        }else{
            $medico->setRfc($medicoJSON->prRfc);
            $medico->setNumeroTelefono($medicoJSON->perNumTelefono);
            $medico->setNombre($medicoJSON->perNombres);
            $medico->setApellido($medicoJSON->perApellidos);
            $medico->setFechaNacimiento($medicoJSON->perFechaNac);
            $medico->setSexo($medicoJSON->perSexo);
            $medico->setTurno($medicoJSON->perTurno);
            $medico->setNumeroPersonal($medicoJSON->perNumPersonal);
            $medico->setEstado($medicoJSON->perEstado);
        }
        return $medico;
    }

    private function getJSON($medico){
        return array('prRfc'=>$medico->getRfc(),'perApellidos'=>$medico->getApellido(), 'perEstado'=>$medico->getEstado(),'perNombres'=>$medico->getNombre(),'perNumPersonal'=>$medico->getNumeroPersonal(),'perNumTelefono'=>$medico->getNumeroTelefono(),'perSexo'=>$medico->getSexo(),'perTurno'=>$medico->getTurno(),'perFechaNac'=>$medico->getFechaNacimiento());
    }
}