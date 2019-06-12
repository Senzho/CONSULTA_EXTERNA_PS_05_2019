<?php

if ( ! defined('BASEPATH')) exit('No direct script access allowed');
interface_exists('IPaciente', FALSE) OR require_once(APPPATH.'libraries/IPaciente.php');

require APPPATH . 'vendor/autoload.php';
use GuzzleHttp\Psr7\Request;
use GuzzleHttp\Client;

class PacienteModelo implements IPaciente{
    public function __get($attr) {
        return CI_Controller::get_instance()->$attr;
    }

    public function obtenerPaciente($numeroSeguro){
        $cliente = new Client(['base_uri'=>'http://192.168.43.126:8080']);
        $peticion = new Request('GET',"/ConsultaExterna_WS/webresources/Paciente/obtener/".$numeroSeguro.'/'.$this->session->userdata('token'),[]);
        $respuesta = $cliente->send($peticion, []);
        $json = json_decode($respuesta->getBody());
        return $this->getJSONObject($json);
    }

    public function registrarPaciente($paciente){
        $registrado = FALSE;
        $cliente = new Client();
        $pacienteJSON = $this->getJSON($paciente);
        $respuesta = $cliente->post('http://192.168.43.126:8080/ConsultaExterna_WS/webresources/Paciente/registrar/'.$this->session->userdata('token'),[GuzzleHttp\RequestOptions::JSON => $pacienteJSON]);
        //$respuesta = $cliente->post('http://localhost:8080/ConsultaExterna_WS/webresources/Paciente/registrar/'.$this->session->userdata('token'),[GuzzleHttp\RequestOptions::JSON => $pacienteJSON]);
        $respuesta = json_decode($respuesta->getBody());  
        if($respuesta->token){
            if($respuesta->registrado){
                $registrado = TRUE;
            }
        }
        return $registrado;
    }
    public function modificarPaciente($paciente){
        $modificado = FALSE;
        $cliente = new Client();
        $pacienteJSON = $this->getJSON($paciente);
        $respuesta = $cliente->put('http://192.168.43.126:8080/ConsultaExterna_WS/webresources/Paciente/modificar/'.$this->session->userdata('token'),[GuzzleHttp\RequestOptions::JSON => $pacienteJSON]);
        $respuesta = json_decode($respuesta->getBody());  
        if($respuesta->token){
            if($respuesta->actualizado){
                $modificado = TRUE;
            }
        }
        return $modificado;
    }

    private function getJSON($paciente){
        return array('pacNumSeguro' => $paciente->getNumeroSeguro(), 'pacAlergias' => $paciente->getAlergias(), 'pacApellidos' => $paciente->getApellido(), 'pacNombres' => $paciente->getNombre(), 'pacNumTelefono' => $paciente->getNumeroTelefono(), 'pacSexo' => $paciente->getSexo(), 'pacFechaNac' => $paciente->getFechaNacimiento());
    }

     private function getJSONObject($JSONObject){
        $pacienteJSON = $JSONObject->paciente;
        $paciente = new Paciente();
        $paciente->setNumeroSeguro(0);

        if($pacienteJSON->pacNumSeguro != 0){
            $paciente->setNumeroSeguro ($pacienteJSON->pacNumSeguro);
            $paciente->setAlergias($pacienteJSON->pacAlergias);
            $paciente->setApellido($pacienteJSON->pacApellidos);
            $paciente->setNombre($pacienteJSON->pacNombres);
            $paciente->setFechaNacimiento($pacienteJSON->pacFechaNac);
            $paciente->setNumeroTelefono($pacienteJSON->pacNumTelefono);
            $paciente->setSexo($pacienteJSON->pacSexo);
        }
        return $pacienteJSON;
    }
}