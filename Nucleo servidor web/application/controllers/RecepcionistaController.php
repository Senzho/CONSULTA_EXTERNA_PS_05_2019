<?php

if ( ! defined('BASEPATH')) exit('No direct script access allowed');
interface_exists('IRecepcionista', FALSE) OR require_once(APPPATH.'libraries/IRecepcionista.php');
require_once(APPPATH.'libraries/Paciente.php');
require_once(APPPATH.'libraries/recursos/DatosPaciente.php');

class RecepcionistaController extends CI_Controller{
  private function obtenerMensajeError($datosPaciente) {
    $mensaje;
    if ($datosPaciente == DatosPaciente::NOMBRE_VACIO) {
      $mensaje = "El nombre es requerido";
    } else if ($datosPaciente == DatosPaciente::NOMBRE_LARGO) {
      $mensaje = "El nombre es demasiado largo";
    } else if ($datosPaciente == DatosPaciente::APELLIDO_VACIO) {
      $mensaje = "El apellido es requerido";
    } else if ($datosPaciente == DatosPaciente::APELLIDO_LARGO) {
      $mensaje = "El apellido es demasiado largo";
    } else if ($datosPaciente == DatosPaciente::NUMERO_SEGURO_VACIO) {
      $mensaje = "El numero de seguro es requerido";
    } else if ($datosPaciente == DatosPaciente::FECHA_NACIMIENTO_VACIA) {
      $mensaje = "La fecha de nacimiento es requerida";
    } else if ($datosPaciente == DatosPaciente::SEXO_VACIO) {
      $mensaje = "El sexo es requerido";
    } else if ($datosPaciente == DatosPaciente::ALERGIAS_VACIAS) {
      $mensaje = "Las alergias son requeridas";
    } else if ($datosPaciente == DatosPaciente::ALERGIAS_LARGAS) {
      $mensaje = "Las alergias son demasiado largas";
    } else if ($datosPaciente == DatosPaciente::ERROR_ALMACENAMIENTO) {
      $mensaje = "Ocurrió un error al almacenar el paciente";
    }
    return $mensaje;
  }

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
      $this->load->view('recepcionista_consultas_view', $data);
    } else {
      $this->session->set_flashdata('no_session', 'Favor de iniciar sesión para ingresar al sistema');
      redirect('UsuarioController');
    }
  }

  public function registrarPaciente()
  {
    $post = json_decode(file_get_contents('php://input'));
    $paciente = new Paciente();
    $paciente->setNombre($post->nombres);
    $paciente->setNumeroSeguro($post->numeroSeguro);
    $paciente->setNumeroTelefono($post->numeroTelefono);
    $paciente->setFechaNacimiento($post->fechaNacimiento);
    $paciente->setSexo($post->sexo);
    $paciente->setAlergias($post->alergias);
    $paciente->setApellido($post->apellidos);
    $paciente->setiPaciente(new PacienteModelo());
    $datosPaciente = $paciente->registrarPaciente();
    $respuesta = array('resultado' => FALSE);
    if ($datosPaciente == DatosPaciente::EXITO) {
      $respuesta['resultado'] = TRUE;
    } else {
      $respuesta['mensaje'] = $this->obtenerMensajeError($datosPaciente);
    }
    echo json_encode(array('respuesta' => $respuesta));
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
