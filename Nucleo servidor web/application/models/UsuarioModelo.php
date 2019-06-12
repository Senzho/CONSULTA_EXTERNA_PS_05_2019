<?php

if ( ! defined('BASEPATH')) exit('No direct script access allowed');
interface_exists('IUsuario', FALSE) OR require_once(APPPATH.'libraries/IUsuario.php');

require APPPATH . 'vendor/autoload.php';
use GuzzleHttp\Psr7\Request;
use GuzzleHttp\Client;


class UsuarioModelo implements IUsuario{

  public function __get($attr) {
    return CI_Controller::get_instance()->$attr;
  }

    public function registrarUsuario($usuario){
        $registrado = FALSE;
        $cliente = new Client();
        $usuarioJSON = $this->getJSON($usuario);
        $respuesta = $cliente->post('http://192.168.43.126:8080/ConsultaExterna_WS/webresources/Usuario/registrar/'.$this->session->userdata('token'),[GuzzleHttp\RequestOptions::JSON => $usuarioJSON]);
        $respuesta = json_decode($respuesta->getBody());  
        if($respuesta->token){
            if($respuesta->registrado){
                $registrado = TRUE;
                $usuario->setIdUsuario($respuesta->usuario->usuId);
            }
        }
        return $registrado;
    }

    public function modificar($usuario){
        $modificado = FALSE;
        $cliente = new Client();
        $usuarioJSON = $this->getJSON($usuario);
        $respuesta = $cliente->put('http://192.168.43.126:8080/ConsultaExterna_WS/webresources/Usuario/modificar/'.$this->session->userdata('token'),[GuzzleHttp\RequestOptions::JSON => $usuarioJSON]);
        $respuesta = json_decode($respuesta->getBody());  
        if($respuesta->token){
            if($respuesta->actualizado){
                $modificado = TRUE;
            }
        }
        return $modificado;
    }
    
    public function iniciarSesion($nombre, $contraseña){
        //$cliente = new Client(['base_uri'=>'http://192.168.43.126:8080']);
        $cliente = new Client(['base_uri'=>'http://localhost:8080']);
    	$peticion = new Request('GET','/ConsultaExterna_WS/webresources/Usuario/obtener/'.$nombre.'/'.$contraseña,[]);
    	$respuesta = $cliente->send($peticion, []);
    	$json = json_decode($respuesta->getBody());
    	return $this->getJSONObject($json);
    }

    private function getJSON($usuario){
        return array('usuId'=>$usuario->getIdUsuario(),'usuNombre'=>$usuario->getNombreUsuario(), 'usuContrasena'=>$usuario->getContraseña(),'usuRol'=>$usuario->getRol());
    }

    private function getJSONObject($JSONObject){
    	$usuarioJSON = $JSONObject->usuario;
    	$usuario = new Usuario();
        $usuario->setIdUsuario(0);
        if($usuarioJSON->usuId != 0){
            $this->session->set_userdata('token',$JSONObject->tokenGenerado);
            $this->session->set_userdata('rol',$usuarioJSON->usuRol);
        	$usuario->setNombreUsuario ($usuarioJSON->usuNombre);
        	$usuario->setContraseña ($usuarioJSON->usuContrasena);
        	$usuario->setIdUsuario ($usuarioJSON->usuId);
        	$usuario->setRol($usuarioJSON->usuRol);
        }
    	return $usuario;
    }
  
}
