<?php

if ( ! defined('BASEPATH')) exit('No direct script access allowed');
interface_exists('IUsuario', FALSE) OR require_once(APPPATH.'libraries/IUsuario.php');

class UsuarioModelo implements IUsuario{
    public function registrarUsuario($usuario){

    }
    public function modificar($usuario){

    }
    
    public function iniciarSesion($nombre, $contraseña){

    }
}