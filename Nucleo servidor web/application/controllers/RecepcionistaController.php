<?php

if ( ! defined('BASEPATH')) exit('No direct script access allowed');
interface_exists('IRecepcionista', FALSE) OR require_once(APPPATH.'libraries/IRecepcionista.php');

class RecepcionistaController extends CI_Controller{

  public function __construct(){
    parent::__construct();
    $this->load->helper('url');
    $this->load->helper('form');
    $this->load->library('Recepcionista');
    $this->load->model('RecepcionistaModelo','recepcionistaModelo');
    $this->load->library('form_validation');
  }
  public function index(){
    if ($this->session->userdata('token')) {
      $data['nombre'] = $this->session->userdata('nombre');
      $this->load->view('recepcionista_consulta_view', $data);
    } else {
      $this->session->set_flashdata('no_session', 'Favor de iniciar sesión para ingresar al sistema');
      redirect('welcome');
    }
  }
  public function registrarPaciente()
  {
    $nombre = $this->input->post('nombre');
    $apellidos = $this->input->post('apellidos');
    $seguro = $this->input->post('seguro');
    $telefono = $this->input->post('telefono');
    $alergias = $this->input->post('alergias');
  }

}
