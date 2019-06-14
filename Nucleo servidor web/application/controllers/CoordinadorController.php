<?php

if ( ! defined('BASEPATH')) exit('No direct script access allowed');
interface_exists('ICoordinador', FALSE) OR require_once(APPPATH.'libraries/ICoordinador.php');
require_once(APPPATH.'libraries/Medico.php');
require_once(APPPATH.'libraries/recursos/DatosPersonal.php');
require_once(APPPATH.'libraries/Usuario.php');
require_once(APPPATH.'libraries/recursos/DatosUsuario.php');

class CoordinadorController extends CI_Controller{
  private function obtenerMensajeErrorMedico($datosPersonal) {
    $mensaje;
    if ($datosPersonal == DatosPersonal::NUMERO_TELEFONO_VACIO) {
      $mensaje = "El número de telefono es requerido";
    } else if ($datosPersonal == DatosPersonal::NUMERO_TELEFONO_LARGO) {
      $mensaje = "El número de teléfono es demasiado largo";
    } else if ($datosPersonal == DatosPersonal::NUMERO_TELEFONO_CORTO) {
      $mensaje = "El número de teléfono es demasiado corto";
    } else if ($datosPersonal == DatosPersonal::NOMBRE_VACIO) {
      $mensaje = "El nombre del médico es requerido";
    } else if ($datosPersonal == DatosPersonal::NOMBRE_LARGO) {
      $mensaje = "El nombre dem médico es demasiado largo";
    } else if ($datosPersonal == DatosPersonal::APELLIDO_VACIO) {
      $mensaje = "La apellido es requerido";
    } else if ($datosPersonal == DatosPersonal::APELLIDO_LARGO) {
      $mensaje = "El apellido es demasiado largo";
    } else if ($datosPersonal == DatosPersonal::FECHA_VACIA) {
      $mensaje = "La fecha de nacimiento es requerida";
    } else if ($datosPersonal == DatosPersonal::SEXO_VACIO) {
      $mensaje = "El sexo es requerido";
    } else if ($datosPersonal == DatosPersonal::RFC_VACIO) {
      $mensaje = "El rfc es requerido";
    } else if ($datosPersonal == DatosPersonal::RFC_LARGO) {
      $mensaje = "El rfc es demasiado largo";
    } else if ($datosPersonal == DatosPersonal::RFC_CORTO) {
      $mensaje = "El rfc es demasiado corto";
    } else if ($datosPersonal == DatosPersonal::NUMERO_PERSONAL_VACIO) {
      $mensaje = "El número de personal es requerido";
    } else if ($datosPersonal == DatosPersonal::TURNO_VACIO) {
      $mensaje = "El turno es requerido";
    } else if ($datosPersonal == DatosPersonal::ERROR_ALMACENAMIENTO) {
      $mensaje = "Ocurrió un error al almacenar el médico";
    }
    return $mensaje;
  }
  private function obtenerMensajeErrorUsuario($datosUsuario) {
    $mensaje;
    if ($datosUsuario == DatosUsuario::NOMBRE_VACIO) {
      $mensaje = "El nombre de usuario es requerido";
    } else if ($datosUsuario == DatosUsuario::NOMBRE_LARGO) {
      $mensaje = "El nombre de usuario es demasiado largo";
    } else if ($datosUsuario == DatosUsuario::CONTRASEÑA_VACIA) {
      $mensaje = "La contraseña es requerida";
    } else if ($datosUsuario == DatosUsuario::CONTRASEÑA_CORTA) {
      $mensaje = "La contraseña es demasiado corta";
    } else if ($datosUsuario == DatosUsuario::CONTRASEÑA_LARGA) {
      $mensaje = "La contraseña es demasiado larga";
    } else if ($datosUsuario == DatosUsuario::ERROR_ALMACENAMIENTO) {
      $mensaje = "Ocurrió un error al almacenar el usuario";
    }
    return $mensaje;
  }

  public function __construct(){
    parent::__construct();
    $this->load->helper('url');
    $this->load->helper('form');
    $this->load->library('Coordinador');
    $this->load->model('CoordinadorModelo','coordinadorModelo');
    $this->load->model('MedicoModelo','medicoModelo');
    $this->load->model('UsuarioModelo','usuarioModelo');
    $this->load->library('form_validation');
  }
  function index(){
    if ($this->session->userdata('token') && $this->session->userdata('rol') == 'Coordinador') {
      $data['nombre'] = $this->session->userdata('nombre');
      $this->load->view('registro_entradas_salidas_coordinador_view', $data);
    } else {
      $this->session->set_flashdata('no_session', 'Favor de iniciar sesión para ingresar al sistema');
      redirect('UsuarioController');
    }
  }
  public function consultarMedicamentos()
  {
    if ($this->session->userdata('token') && $this->session->userdata('rol') == 'Coordinador') {
      $data['nombre'] = $this->session->userdata('nombre');
      $this->load->view('consulta_medicamentos_coordinador_view', $data);
    } else {
      $this->session->set_flashdata('no_session', 'Favor de iniciar sesión para ingresar al sistema');
      redirect('UsuarioController');
    }
  }
  public function registrarMedico()
  {
    if ($this->session->userdata('token') && $this->session->userdata('rol') == 'Coordinador') {
      $data['nombre'] = $this->session->userdata('nombre');
      $medico = new Medico();
      $medico->setiMedico(new MedicoModelo());
      $data['medicos'] = $medico->obtenerPersonalRol('Medico');
      $this->load->view('registro_medico_coordinador_view', $data);
    } else {
      $this->session->set_flashdata('no_session', 'Favor de iniciar sesión para ingresar al sistema');
      redirect('UsuarioController');
    }
  }
  public function registrarRecepcionista()
  {
    if ($this->session->userdata('token') && $this->session->userdata('rol') == 'Coordinador') {
      $data['nombre'] = $this->session->userdata('nombre');
      $this->load->view('registro_recepcionista_coordinador_view', $data);
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

  public function guardarMedico() {
    $post = json_decode(file_get_contents('php://input'));
    $usuarioJson = $post->usuario;
    $medicoJson = $post->medico;
    $usuario = new Usuario();
    $usuario->setNombreUsuario($this->limpiar($usuarioJson->nombre));
    $usuario->setContraseña($this->limpiar($usuarioJson->contraseña));
    $usuario->setIdUsuario(100);
    $usuario->setRol('Medico');
    $usuario->setiUsuario(new UsuarioModelo());
    $medico = new Medico();
    $medico->setNombre($this->limpiar($medicoJson->nombres));
    $medico->setApellido($this->limpiar($medicoJson->apellidos));
    $medico->setNumeroTelefono($this->limpiar($medicoJson->telefono));
    $medico->setRfc($this->limpiar($medicoJson->rfc));
    $medico->setNumeroPersonal($this->limpiar($medicoJson->numeroPersonal));
    $medico->setTurno($this->limpiar($medicoJson->turno));
    $medico->setSexo($this->limpiar($medicoJson->sexo));
    $medico->setFechaNacimiento($this->limpiar($medicoJson->fechaNacimiento));
    $medico->setEstado(1);
    $medico->setiMedico(new MedicoModelo());
    $respuesta = array('resultado' => FALSE);
    $datosUsuario = $usuario->registrarUsuario();
    if ($datosUsuario == DatosUsuario::EXITO) {
      $datosPersonal = $medico->registrar($usuario->getIdUsuario());
      if ($datosPersonal == DatosPersonal::EXITO) {
        $respuesta['resultado'] = TRUE;
      } else {
        $respuesta['mensaje'] = $this->obtenerMensajeErrorMedico($datosPersonal);
      }
    } else {
      $respuesta['mensaje'] = $this->obtenerMensajeErrorUsuario($datosUsuario);
    }
    echo json_encode(array('respuesta' => $respuesta));
  }
  public function modificarMedico() {
    $put = json_decode(file_get_contents('php://input'));
    $medico = new Medico();
    $medico->setNombre($this->limpiar($put->nombres));
    $medico->setApellido($this->limpiar($put->apellidos));
    $medico->setNumeroTelefono($this->limpiar($put->telefono));
    $medico->setRfc($this->limpiar($put->rfc));
    $medico->setNumeroPersonal($this->limpiar($put->numeroPersonal));
    $medico->setTurno($this->limpiar($put->turno));
    $medico->setSexo($this->limpiar($put->sexo));
    $medico->setFechaNacimiento($this->limpiar($put->fechaNacimiento));
    $medico->setEstado(1);
    $medico->setiMedico(new MedicoModelo());
    $respuesta = array('resultado' => FALSE);
    $datosPersonal = $medico->modificar();
    if ($datosPersonal == DatosPersonal::EXITO) {
      $respuesta['resultado'] = TRUE;
    } else {
      $respuesta['mensaje'] = $this->obtenerMensajeErrorMedico($datosPersonal);
    }
    echo json_encode(array('respuesta' => $respuesta));
  }
  public function obtenerMedico($rfc) {
    $medico = new Medico();
    $medico->setiMedico(new MedicoModelo());
    $medico = $medico->obtenerPersonal($rfc);
    $respuesta = array('numeroPersonal' => $medico->getNumeroPersonal());
    if ($medico->getNumeroPersonal() != 0) {
      $respuesta['nombres'] = $medico->getNombre();
      $respuesta['apellidos'] = $medico->getApellido();
      $respuesta['telefono'] = $medico->getNumeroTelefono();
      $respuesta['rfc'] = $medico->getRfc();
      $respuesta['numeroPersonal'] = $medico->getNumeroPersonal();
      $respuesta['turno'] = $medico->getTurno();
      $respuesta['sexo'] = $medico->getSexo();
      $respuesta['fechaNacimiento'] = $medico->getFechaNacimiento();
    }
    echo json_encode($respuesta);
  }

  public function limpiar($value ){
    $search = array('<' ,'>', '/',"script");
    return str_replace($search, "", $value);
  }
}
