<?php

if ( ! defined('BASEPATH')) exit('No direct script access allowed');
require_once(APPPATH.'libraries/recursos/DatosPersonal.php');
require_once(APPPATH.'libraries/Personal.php');

class Coordinador extends Personal{
    private $iCoordinador;

    public function __get($attr) {
		return CI_Controller::get_instance()->$attr;
    }
    
    public function __construct(){
        
    }

    private function validarDatosPersonalesCoordinador(){
        $datosCoordinador = DatosPersonal::VALIDO;
        if($this->getNumeroTelefono() === "" || $this->getNumeroTelefono() === null){
            $datosCoordinador = DatosPersonal::NUMERO_TELEFONO_VACIO;
        }else if(strlen($this->getNumeroTelefono()) > 10){
            $datosCoordinador = DatosPersonal::NUMERO_TELEFONO_LARGO;
        }else if(strlen($this->getNumeroTelefono()) < 10){
            $datosCoordinador = DatosPersonal::UMERO_TELEFONO_CORTO;
        }else if($this->getNombre() === "" || $this->getNombre() === null){
            $datosCoordinador = DatosPersonal::NOMBRE_VACIO;
        }else if(strlen($this->getNombre()) > 70){
            $datosCoordinador = DatosPersonal::NOMBRE_LARGO;
        }else if($this->getApellido()==="" || $this->getApellido() === null){
            $datosCoordinador = DatosPersonal::APELLIDO_VACIO;
        }else if(strlen($this->getApellido()) > 70){
            $datosCoordinador = DatosPersonal::APELLIDO_LARGO;
        }else if($this->getFechaNacimiento() === "" || $this->getFechaNacimiento() === null){
            $datosCoordinador = DatosPersonal::FECHA_VACIA;
        }else if($this->getSexo()==""){
            $datosCoordinador = DatosPersonal::SEXO_VACIO;
        }else  if($this->getRfc()==="" || $this->getRfc() === null){
            $datosCoordinador = DatosPersonal::RFC_VACIO;
        }else if(strlen($this->getRfc()) > 13){
            $datosCoordinador = DatosPersonal::RFC_LARGO;
        }else if(strlen($this->getRfc())< 13){
            $datosCoordinador = DatosPersonal::RFC_CORTO;
        }else if($this->getNumeroPersonal()==="" || $this->getNumeroPersonal() === null){
            $datosCoordinador = DatosPersonal::NUMERO_PERSONAL_VACIO;
        }else if($this->getTurno()==="" || $this->getTurno() == null){
            $datosCoordinador = DatosPersonal::TURNO_VACIO;
        }
        return $datosCoordinador;
    }
    public function setICoordinador($iCoordinador){
        $this->iCoordinador = $iCoordinador;
    }


    public function registrar($idUsuario){
        $coordinadorRegistrado = $this->validarDatosPersonalesCoordinador();
        if($coordinadorRegistrado == DatosPersonal::VALIDO){
            if($this->obtenerPersonal($this->getNumeroPersonal()) === null){
                if($this->iCoordinador->registrar($this, $idUsuario)){
                    $coordinadorRegistrado = DatosPersonal::EXITO;
                }else{
                    $coordinadorRegistrado =  DatosPersonal::ERROR_ALMACENAMIENTO;
                }
            }
        }
        return $coordinadorRegistrado;
    }
    public function modificar(){
        $coordinadorModificado = $this->validarDatosPersonalesCoordinador();
        if($coordinadorModificado === DatosPersonal::VALIDO){
            if($this->iCoordinador->modificar($this)){
                $coordinadorModificado = DatosPersonal::EXITO;
            }else{
                $coordinadorModificado = DatosPersonal::ERROR_ALMACENAMIENTO;
            }
        }
        return $coordinadorModificado;
    }
    public function registrarEntrada($numeroConsultorio){
        return $this->iCoordinador->registrarEntrada($this->getRfc(),$numeroConsultorio);
    }
    public function registrarSalida(){
        return $this->iCoordinador->registrarSalida($this->getRfc());
    }
    public function eliminar(){
        return $this->iCoordinador->eliminar($this->getRfc());
    }
    public function obtenerPersonal($rfc){
        return $this->iCoordinador->obtenerCoordinador($rfc);
    }
        public function obtenerPersonalId($idUsuario){
        return $this->iCoordinador->obtenerCoordinadorId($idUsuario);
    }
}