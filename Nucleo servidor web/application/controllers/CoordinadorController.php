<?php

if ( ! defined('BASEPATH')) exit('No direct script access allowed');
interface_exists('ICoordinador', FALSE) OR require_once(APPPATH.'libraries/ICoordinador.php');

class CoordinadorController extends CI_Controller{
    public function __construct(){
        parent::__construct();
        $this->load->helper('url');
        $this->load->helper('form');
        $this->load->library('Coordinador');
        $this->load->model('CoordinadorModelo','coordinadorModelo');
        $this->load->library('form_validation');
    }
    function index(){
      if ($this->session->userdata('token')) {
        $data['nombre'] = $this->session->userdata('nombre');
        $this->load->view('registro_entradas_salidas_coordinador_view', $data);
      } else {
        $this->session->set_flashdata('no_session', 'Favor de iniciar sesión para ingresar al sistema');
        redirect('UsuarioController');
      }
    }
    public function cerarSesion()
    {
      $this->session->unset_userdata('rol');
      $this->session->unset_userdata('rfc');
      $this->session->unset_userdata('token');
      redirect('UsuarioController');
    }
}
