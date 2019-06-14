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
        $registrado = FALSE;
        $cliente = new Client();
        $medicoJSON = $this->getJSON($medico);
        $respuesta = $cliente->post('http://localhost:8080/ConsultaExterna_WS/webresources/Personal/registrar/'.$idUsuario.'/'.$this->session->userdata('token'),[GuzzleHttp\RequestOptions::JSON => $medicoJSON]);
        $respuesta = json_decode($respuesta->getBody());
        if($respuesta->token){
            if($respuesta->registrado){
                $registrado = TRUE;
            }
        }
        return $registrado;
    }

    public function modificar($medico){
        $actualizado = FALSE;
        $cliente = new Client();
        $medicoJSON = $this->getJSON($medico);
        $respuesta = $cliente->put('http://localhost:8080/ConsultaExterna_WS/webresources/Personal/modificar/' . $this->session->userdata('token'),[GuzzleHttp\RequestOptions::JSON => $medicoJSON]);
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
        $respuesta = $cliente->post('http://localhost:8080/ConsultaExterna_WS/webresources/Personal/registrarentrada/' . $numeroConsultorio . '/' . $rfc . '/' . $this->session->userdata('token'),[]);
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
        $respuesta = $cliente->post('http://localhost:8080/ConsultaExterna_WS/webresources/Personal/registrarsalida/' . $rfc . '/' . $this->session->userdata('token'),[]);
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
        $respuesta = $cliente->delete('http://localhost:8080/ConsultaExterna_WS/webresources/Personal/estado/' . $rfc . '/' . $this->session->userdata('token'),[]);
        $respuesta = json_decode($respuesta->getBody());
        if ($respuesta->token) {
            if ($respuesta->cambiado) {
                $eliminado = TRUE;
            }
        }
        return $eliminado;
    }

    public function obtenerMedico($rfc){
        $cliente = new Client(['base_uri'=>'http://localhost:8080']);
        $token = $this->session->userdata("token");
        $peticion = new Request('GET','/ConsultaExterna_WS/webresources/Personal/obtenerrfc/'.$rfc.'/'.$token,[]);
        $respuesta = $cliente->send($peticion, []);
        $json = json_decode($respuesta->getBody());
        return $this->getJSONObject($json);
    }

    public function obtenerMedicoId($idUsuario){
        $cliente = new Client(['base_uri'=>'http://localhost:8080']);
        $token = $this->session->userdata('token');
        $peticion = new Request('GET','/ConsultaExterna_WS/webresources/Personal/obteneridusuario/'.$idUsuario.'/'.$token,[]);
        $respuesta = $cliente->send($peticion, []);
        $json = json_decode($respuesta->getBody());
        return $this->getJSONObject($json);
    }
    public function obtenerMedicos() {
        $cliente = new Client(['base_uri'=>'http://localhost:8080']);
        $token = $this->session->userdata('token');
        $peticion = new Request('GET','/ConsultaExterna_WS/webresources/Personal/obtenerrol/Medico/'.$token,[]);
        $respuesta = $cliente->send($peticion, []);
        $json = json_decode($respuesta->getBody());
        return $this->getMedicos($json);
    }

    private function getJSONObject($JSONObject){
        $medicoJSON = $JSONObject->personal;
        $medico = new Medico();
        $medico->setRfc('');
        if($medicoJSON->prRfc != ''){
            $this->session->set_userdata('rcf', $medicoJSON->prRfc);
            $this->session->set_userdata('nombre', $medicoJSON->perNombres." ".$medicoJSON->perApellidos);
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
    private function getMedicos($json) {
        $medicos = array();
        $arreglo = $json->personales;
        foreach ($arreglo as $elemento) {
            $medico = new Medico();
            $medico->setRfc ($elemento->prRfc);
            $medico->setNumeroTelefono($elemento->perNumTelefono);
            $medico->setNumeroPersonal($elemento->perNumPersonal);
            $medico->setNombre($elemento->perNombres);
            $medico->setApellido($elemento->perApellidos);
            $medico->setFechaNacimiento($elemento->perFechaNac);
            $medico->setSexo($elemento->perSexo);
            $medico->setTurno($elemento->perTurno);
            $medico->setEstado($elemento->perEstado);
            array_push($medicos, $medico);
        }
        return $medicos;
    }
}
