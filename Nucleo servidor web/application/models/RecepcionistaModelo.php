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

    public function registrar($recepcionista, $idUsuario){
        $registrado = FALSE;
        $cliente = new Client();
        $recepcionistaJSON = $this->getJSON($recepcionista);
        $respuesta = $cliente->post('http://192.168.43.126:8080/ConsultaExterna_WS/webresources/Personal/registrar/'.$idUsuario.'/'.$this->session->userdata('token'),[GuzzleHttp\RequestOptions::JSON => $recepcionistaJSON]);
        $respuesta = json_decode($respuesta->getBody());  
        if($respuesta->token){
            if($respuesta->registrado){
                $registrado = TRUE;
            }
        }
        return $registrado;
    }

    public function modificar($recepcionista){
        $actualizado = FALSE;
        $cliente = new Client();
        $recepcionistaJSON = $this->getJSON($recepcionista);
        $respuesta = $cliente->put('http://192.168.43.126:8080/ConsultaExterna_WS/webresources/Personal/modificar/' . $this->session->userdata('token'),[GuzzleHttp\RequestOptions::JSON => $recepcionistaJSON]);
        $respuesta = json_decode($respuesta->getBody());  
        if($respuesta->token){
            if($respuesta->actualizado){
                $actualizado = TRUE;
            }
        }
        return $actualizado;
    }
    public function registrarEntrada($numeroConsultorio, $rfc){
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
    public function agregarConsulta($consulta){

    }
    public function obtenerCitas($fecha){
        $cliente = new Client(['base_uri'=>'http://192.168.43.126:8080']);
        $peticion = new Request('GET','/ConsultaExterna_WS/webresources/Cita/obtener/'.$fecha.'/'.$this->session->userdata('token'),[]);
        $respuesta = $cliente->send($peticion, []);
        $json = json_decode($respuesta->getBody());
        return $this->getCitas($json);
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

    private function getJSON($recepcionista){
        return array('prRfc'=>$recepcionista->getRfc(),'perApellidos'=>$recepcionista->getApellido(), 'perEstado'=>$recepcionista->getEstado(),'perNombres'=>$recepcionista->getNombre(),'perNumPersonal'=>$recepcionista->getNumeroPersonal(),'perNumTelefono'=>$recepcionista->getNumeroTelefono(),'perSexo'=>$recepcionista->getSexo(),'perTurno'=>$recepcionista->getTurno(),'perFechaNac'=>$recepcionista->getFechaNacimiento());
    }

    private function getCitas($json) {
        $citas = array();
        $arreglo = $json->citas;
        foreach ($arreglo as $elemento) {
            $cita = new Cita();
            $cita.setIdCita($elemento->citId);
            $cita.setEstado($elemento->citEstado);
            $cita.setHoraReserva($elemento->citFechaHoraReserva);
            array_push($citas, $cita);
        }
        return $citas;
    }
}
