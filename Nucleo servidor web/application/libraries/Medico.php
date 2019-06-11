<?php

if ( ! defined('BASEPATH')) exit('No direct script access allowed');
require_once(APPPATH.'libraries/recursos/DatosPersonal.php');
require_once(APPPATH.'libraries/Personal.php');

class Medico extends Personal{
    private $iMedico;

    public function __get($attr) {
		return CI_Controller::get_instance()->$attr;
    }
    
    public function __construct(){
        
    }

    private function validarDatosPersonalesMedico(){
        $datosMedico = DatosPersonal::VALIDO;
        if($this->getNumeroTelefono() === "" || $this->getNumeroTelefono() === null){
            $datosMedico = DatosPersonal::NUMERO_TELEFONO_VACIO;
        }else if(strlen($this->getNumeroTelefono()) > 10){
            $datosMedico = DatosPersonal::NUMERO_TELEFONO_LARGO;
        }else if(strlen($this->getNumeroTelefono()) < 10){
            $datosMedico = DatosPersonal::UMERO_TELEFONO_CORTO;
        }else if($this->getNombre() === "" || $this->getNombre() === null){
            $datosMedico = DatosPersonal::NOMBRE_VACIO;
        }else if(strlen($this->getNombre()) > 70){
            $datosMedico = DatosPersonal::NOMBRE_LARGO;
        }else if($this->getApellido()==="" || $this->getApellido() === null){
            $datosMedico = DatosPersonal::APELLIDO_VACIO;
        }else if(strlen($this->getApellido()) > 70){
            $datosMedico = DatosPersonal::APELLIDO_LARGO;
        }else if($this->getFechaNacimiento() === "" || $this->getFechaNacimiento() === null){
            $datosMedico = DatosPersonal::FECHA_VACIA;
        }else if($this->getSexo()==""){
            $datosMedico = DatosPersonal::SEXO_VACIO;
        }else  if($this->getRfc()==="" || $this->getRfc() === null){
            $datosMedico = DatosPersonal::RFC_VACIO;
        }else if(strlen($this->getRfc()) > 13){
            $datosMedico = DatosPersonal::RFC_LARGO;
        }else if(strlen($this->getRfc())< 13){
            $datosMedico = DatosPersonal::RFC_CORTO;
        }else if($this->getNumeroPersonal()==="" || $this->getNumeroPersonal() === null){
            $datosMedico = DatosPersonal::NUMERO_PERSONAL_VACIO;
        }else if($this->getTurno()==="" || $this->getTurno() == null){
            $datosMedico = DatosPersonal::TURNO_VACIO;
        }
        return $datosMedico;
    }

    public function setIMedico($iMedico){
        $this->iMedico = $iMedico;
    }

    public function registrar($idUsuario){
        $registro = $this->validarDatosPersonalesMedico();
        if($registro === DatosPersonal::VALIDO){
            if($this->obtenerPersonal($this->getNumeroPersonal())== null){
                if($this->iMedico->registrar($this, $idUsuario)){
                    $registro = DatosPersonal::EXITO;
                }else{
                    $registro = DatosPersonal::ERROR_ALMACENAMIENTO;
                }
            }
            return $registro;
        }
    }

    public function modificar(){
        $registro = $this->validarDatos();
        if($registro === DatosPersonal::VALIDO){
            if($this->iMedico->modificar($this)){
                $registro = DatosPersonal::EXITO;
            }else{
                $registro = DatosPersonal::ERROR_ALMACENAMIENTO;
            }
        }
        return $registro;
    }

    public function registrarEntrada($numeroConsultorio){
        $respuesta = false;
        if($numeroConsultorio!="" | $numeroConsultorio!= null){
            $respuesta = $this->iMedico->registrarEntrada($this->getRfc(), $numeroConsultorio);
        }
        return $respuesta;
    }

    public function registrarSalida(){
        return $this->iMedico->registrarSalida($this->getRfc());
    }

    public function eliminar(){
        return $this->iMedico->eliminar($this->getRfc());
    }

    public function obtenerPersonal($rfc){
        return $this->iMedico->obtenerMedico($rfc);
    }
    public function obtenerPersonalId($idUsuario){
        return $this->iMedico->obtenerMedicoId($idUsuario);
    }

}