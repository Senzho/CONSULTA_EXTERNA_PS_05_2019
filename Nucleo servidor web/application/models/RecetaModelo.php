<?php

if ( ! defined('BASEPATH')) exit('No direct script access allowed');
interface_exists('IReceta', FALSE) OR require_once(APPPATH.'libraries/IReceta.php');

require APPPATH . 'vendor/autoload.php';
use GuzzleHttp\Psr7\Request;
use GuzzleHttp\Client;

class RecetaModelo implements IReceta{
	public function __get($attr) {
    	return CI_Controller::get_instance()->$attr;
  	}
    public function registrar($receta){
    	$registrado = false;
    	$cliente = new Client();
        $recetaJSON = $this->getJSON($receta);
        $respuesta = $cliente->post('http://192.168.43.126:8080/ConsultaExterna_WS/webresources/Receta/registrar/'.$this->session->userdata('token'),[GuzzleHttp\RequestOptions::JSON => $recetaJSON]);
        $respuesta = json_decode($respuesta->getBody());  
        if($respuesta->token){
            if($respuesta->registrada){
                $registrado = TRUE;
                $receta->setFolio($respuesta->receta->recFolio);
            }
        }
        return $registrado;
    }
    
    public function obtenerMedicamentosRecetados(){

    }
    //recFolio, recFechaCreacion, recFechaVencimiento, recInstrucciones
   private function getJSON($receta){
   	$medicamentos = $receta->getMedicamentosReceta();
   	$medicamentosArray = array();
	foreach ($medicamentos as $elemento) {
		$medicamento = array('medCodigo'=>$elemento->getCodigo());
		array_push($medicamentosArray, $medicamento);
	}
    return array('receta'=>array('recFolio'=>$receta->getFolio(),'recFechaCreacion'=>$receta->getFechaCreacion(),'recFechaVencimiento'=>$receta->getFechaVencimiento(),'recInstrucciones'=>$receta->getInstrucciones()), 'medicamentos'=>$medicamentosArray);
    }
}