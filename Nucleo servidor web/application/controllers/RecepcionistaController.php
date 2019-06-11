<?php

if ( ! defined('BASEPATH')) exit('No direct script access allowed');
interface_exists('IRecepcionista', FALSE) OR require_once(APPPATH.'libraries/IRecepcionista.php');
require_once(APPPATH.'libraries/Paciente.php');

class RecepcionistaController extends CI_Controller{

  public function __construct(){
    parent::__construct();
    //$this->load->helper('url');
    //$this->load->helper('form');
    $this->load->library('Recepcionista');
    $this->load->model('RecepcionistaModelo','recepcionistaModelo');
    $this->load->model('PacienteModelo','pacienteModelo');
    //$this->load->library('form_validation');
  }
  public function index(){
    if ($this->session->userdata('token') && $this->session->userdata('rol') == 'Recepcionista') {
      $data['nombre'] = $this->session->userdata('nombre');
      $data['t'] = $this->session->userdata('token');
      $this->load->view('recepcionista_consultas_view', $data);
    } else {
      $this->session->set_flashdata('no_session', 'Favor de iniciar sesión para ingresar al sistema');
      redirect('UsuarioController');
    }
  }

  public function registrarPaciente()
  {
    $paciente = new Paciente();
    $paciente->setNombre($this->input->post('nombre'));
    $paciente->setNumeroSeguro($this->input->post('seguro'));
    $paciente->setNumeroTelefono($this->input->post('telefono'));
    $paciente->setFechaNacimiento($this->input->post('fechaNacimiento'));
    $paciente->setSexo($this->input->post('sexo'));
    $paciente->setAlergias($this->input->post('alergias'));
    $paciente->setApellido($this->input->post('apellidos'));
    $paciente->setiPaciente(new PacienteModelo());
    echo json_encode(array('resultado' => $paciente->registrarPaciente()));
  }
  public function actualizarPacientes()
  {
    if ($this->session->userdata('token') && $this->session->userdata('rol') == 'Recepcionista') {
      $data['nombre'] = $this->session->userdata('nombre');
      $this->load->view('actualizar_pacientes_view', $data);
    } else {
      $this->session->set_flashdata('no_session', 'Favor de iniciar sesión para ingresar al sistema');
      redirect('UsuarioController');
    }
  }
  public function agendarCita()
  {
    if ($this->session->userdata('token') && $this->session->userdata('rol') == 'Recepcionista') {
      $data['nombre'] = $this->session->userdata('nombre');
      $this->load->view('agendar_cita_view', $data);
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
