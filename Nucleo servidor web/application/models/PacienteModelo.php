<?php

if ( ! defined('BASEPATH')) exit('No direct script access allowed');
interface_exists('IPaciente', FALSE) OR require_once(APPPATH.'libraries/IPaciente.php');

require APPPATH . 'vendor/autoload.php';
use GuzzleHttp\Psr7\Request;
use GuzzleHttp\Client;

class PacienteModelo implements IPaciente{
    public function obtenerPaciente($numeroSeguro){

    }
    public function registrarPaciente($paciente){
        $registrado = FALSE;
        $cliente = new Client();
        $pacienteJSON = $this->getJSON($paciente);
        //$respuesta = $cliente->post('http://192.168.43.126:8080/ConsultaExterna_WS/webresources/Paciente/registrar/'.$this->session->userdata('token'),[GuzzleHttp\RequestOptions::JSON => $pacienteJSON]);
        $respuesta = $cliente->post('http://localhost:8080/ConsultaExterna_WS/webresources/Paciente/registrar/'.$this->session->userdata('token'),[GuzzleHttp\RequestOptions::JSON => $pacienteJSON]);
        $respuesta = json_decode($respuesta->getBody());  
        if($respuesta->token){
            if($respuesta->registrado){
                $registrado = TRUE;
            }
        }
        return $registrado;
    }
    public function modificarPaciente($paciente){
        
    }

    private function getJSON($paciente){
        return array('pacNumSeguro' => $paciente->getNumeroSeguro(), 'pacAlergias' => $paciente->getAlergias(), 'pacApellidos' => $paciente->getApellido, 'pacNombres' => $paciente->getNombre(), 'pacNumTelefono' => $paciente->getNumTelefono(), 'pacSexo' => $paciente->getSexo());
    }
}