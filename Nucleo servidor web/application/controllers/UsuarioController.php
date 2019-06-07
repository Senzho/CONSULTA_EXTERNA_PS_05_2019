<?php

if ( ! defined('BASEPATH')) exit('No direct script access allowed');
interface_exists('IUsuario', FALSE) OR require_once(APPPATH.'libraries/IUsuario.php');

class UsuarioController extends CI_Controller{
    public function __construct(){
        parent::__construct();
        $this->load->helper('url');
        $this->load->helper('form');
        $this->load->library('Usuario');
        $this->load->model('UsuarioModelo','usuarioModelo');
        $this->load->library('form_validation');
    }
    public function index(){
        
    }

}