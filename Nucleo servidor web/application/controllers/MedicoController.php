<?php

if ( ! defined('BASEPATH')) exit('No direct script access allowed');
interface_exists('IMedico', FALSE) OR require_once(APPPATH.'libraries/IMedico.php');

class MedicoController extends CI_Controller{
  public function __construct(){
    parent::__construct();
    //$this->load->helper('url');
    //$this->load->helper('form');
    $this->load->library('Medico');
    $this->load->model('MedicoModelo','medicoModelo');
    //$this->load->library('form_validation');
  }
  function index(){
    if ($this->session->userdata('token') && $this->session->userdata('rol') == 'Medico') {
      $data['nombre'] = $this->session->userdata('nombre');
      $this->load->view('consultas_medico_view', $data);
    } else {
      $this->session->set_flashdata('no_session', 'Favor de iniciar sesión para ingresar al sistema');
      redirect('UsuarioController');
    }
  }
  public function realizarCita()
  {
    if ($this->session->userdata('token') && $this->session->userdata('rol') == 'Medico') {
      $data['nombre'] = $this->session->userdata('nombre');
      $this->load->view('realizar_cita_view', $data);
    } else {
      $this->session->set_flashdata('no_session', 'Favor de iniciar sesión para ingresar al sistema');
      redirect('UsuarioController');
    }
  }
  public function cerrarSesion()
  {
    $this->session->unset_userdata('rol');
    $this->session->unset_userdata('rfc');
    $this->session->unset_userdata('token');
    redirect('UsuarioController');
  }
}
