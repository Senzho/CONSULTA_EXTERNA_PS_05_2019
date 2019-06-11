<?php

if ( ! defined('BASEPATH')) exit('No direct script access allowed');
interface_exists('IUsuario', FALSE) OR require_once(APPPATH.'libraries/IUsuario.php');

class UsuarioController extends CI_Controller{
  public function __construct(){
    parent::__construct();
    $this->load->helper('url');
    $this->load->helper('form');
    $this->load->library('Usuario');
    $this->load->library('Medico');
    $this->load->library('Coordinador');
    $this->load->library('Recepcionista');
    $this->load->model('UsuarioModelo','usuarioModelo');
    $this->load->model('MedicoModelo','medicoModelo');
    $this->load->model('CoordinadorModelo','coordinadorModelo');
    $this->load->model('RecepcionistaModelo','recepcionistaModelo');
    $this->load->library('form_validation');
  }
  public function index(){
    if ($this->session->userdata('token')) {
      if ($this->session->userdata('rol') == 'Recepcionista' ) {
        redirect('RecepcionistaController');
      }elseif ($this->session->userdata('rol') == 'Medico' ) {
        redirect('MedicoController');
      }elseif ($this->session->userdata('rol') == 'Coordinador' ) {
        redirect('CoordinadorController');
      }
    } else {
      $data['info'] = $this->session->flashdata('no_session');
      $this->load->view("login_view");
    }
  }
  public function iniciarSesion(){
    $nombre = $this->input->post("usuario");
    $contrasena =$this->input->post("contrasena");
    $usuario = new Usuario();
    $usuario->setIUsuario(new UsuarioModelo());
    $usuario->setNombreUsuario($nombre);
    $usuario->setContraseña($contrasena);
    $usuario = $usuario->iniciarSesion();
    if($usuario->getIdUsuario() === 0){
      $data['info'] = 'Los datos son incorrectos';
      $this->load->view('login_view', $data);
    }else{
      if($usuario->getRol()=== 'Medico'){
        $medico = new Medico();
        $medico->setiMedico(new MedicoModelo());
        $medico = $medico->obtenerPersonalId($usuario->getIdUsuario());
        if($medico->getNumeroPersonal() === 0){
          $data['info'] = 'No hay personal asignado a este usuario';
          $this->load->view('login_view', $data);
        }else{
          redirect('MedicoController');
        }
      }else if($usuario->getRol() == 'Coordinador'){
        $coordinador = new Coordinador();
        $coordinador->setICoordinador(new CoordinadorModelo());
        $coordinador = $coordinador->obtenerPersonalId($usuario->getIdUsuario());
        if($coordinador->getNumeroPersonal() === 0){
          $data['info'] = 'No hay personal asignado a este usuario';
          $this->load->view('login_view', $data);
        }else{
          redirect('CoordinadorController');
        }
      }else if($usuario->getRol() == 'Recepcionista'){
        $recepcionista = new Recepcionista();
        $recepcionista->setIRecepcionista(new RecepcionistaModelo());
        $recepcionista = $recepcionista->obtenerPersonalId($usuario->getIdUsuario());
        if($recepcionista->getNumeroPersonal() === 0){
          $data['info'] = 'No hay personal asignado a este usuario';
          $this->load->view('login_view', $data);
        }else{
          redirect('RecepcionistaController');
        }
      }
    }
  }

}
