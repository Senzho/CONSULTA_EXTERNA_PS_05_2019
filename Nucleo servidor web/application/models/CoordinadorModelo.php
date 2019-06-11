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

    public function registrar($coordinador, $idUsuario){
        $registrado = FALSE;
        $cliente = new Client();
        $coordinadorJSON = $this->getJSON($coordinador);
        $respuesta = $cliente->post('http://192.168.43.126:8080/ConsultaExterna_WS/webresources/Personal/registrar/'.$idUsuario.'/'.$this->session->userdata('token'),[GuzzleHttp\RequestOptions::JSON => $coordinadorJSON]);
        $respuesta = json_decode($respuesta->getBody());  
        if($respuesta->token){
            if($respuesta->registrado){
                $registrado = TRUE;
            }
        }
        return $registrado;
    }
    public function modificar($coordinador){
        $actualizado = FALSE;
        $cliente = new Client();
        $coordinadorJSON = $this->getJSON($coordinador);
        $respuesta = $cliente->put('http://192.168.43.126:8080/ConsultaExterna_WS/webresources/Personal/modificar/' . $this->session->userdata('token'),[GuzzleHttp\RequestOptions::JSON => $coordinadorJSON]);
        $respuesta = json_decode($respuesta->getBody());  
        if($respuesta->token){
            if($respuesta->actualizado){
                $actualizado = TRUE;
            }
        }
        return $actualizado;
    }
    public function registrarEntrada($rfc, $numeroConsultorio){
        $registrada = FALSE;
        $cliente = new Client();
        $respuesta = $cliente->post('http://192.168.43.126:8080/ConsultaExterna_WS/webresources/Personal/registrarentrada/' . $numeroConsultorio . '/' . $rfc . '/' . $this->session->userdata('token'),[]);
        $respuesta = json_decode($respuesta->getBody());
        if ($respuesta->token) {
            if ($respuesta->registrada) {
                $registrada = TRUE;
            }
        }
        return $registrada;
    }
    public function registrarSalida($rfc){
        $registrada = FALSE;
        $cliente = new Client();
        $respuesta = $cliente->post('http://192.168.43.126:8080/ConsultaExterna_WS/webresources/Personal/registrarsalida/' . $rfc . '/' . $this->session->userdata('token'),[]);
        $respuesta = json_decode($respuesta->getBody());
        if ($respuesta->token) {
            if ($respuesta->registrada) {
                $registrada = TRUE;
            }
        }
        return $registrada;
    }
    public function eliminar($rfc){
        $eliminado = FALSE;
        $cliente = new Client();
        $respuesta = $cliente->delete('http://192.168.43.126:8080/ConsultaExterna_WS/webresources/Personal/estado/' . $rfc . '/' . $this->session->userdata('token'),[]);
        $respuesta = json_decode($respuesta->getBody());
        if ($respuesta->token) {
            if ($respuesta->cambiado) {
                $eliminado = TRUE;
            }
        }
        return $eliminado;
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
          $this->session->set_userdata('rcf', $coordinadorJSON->prRfc);
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

    private function getJSON($coordinador){
        return array('prRfc'=>$coordinador->getRfc(),'perApellidos'=>$coordinador->getApellido(), 'perEstado'=>$coordinador->getEstado(),'perNombres'=>$coordinador->getNombre(),'perNumPersonal'=>$coordinador->getNumeroPersonal(),'perNumTelefono'=>$coordinador->getNumeroTelefono(),'perSexo'=>$coordinador->getSexo(),'perTurno'=>$coordinador->getTurno(),'perFechaNac'=>$coordinador->getFechaNacimiento());
    }
}
