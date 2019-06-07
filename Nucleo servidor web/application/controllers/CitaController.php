<?php

if ( ! defined('BASEPATH')) exit('No direct script access allowed');
interface_exists('ICita', FALSE) OR require_once(APPPATH.'libraries/ICita.php');

class CitaController extends CI_Controller{
    
    public function __construct(){
        parent::__construct();
        $this->load->helper('url');
        $this->load->helper('form');
        $this->load->library('Cita');
        $this->load->model('CitaModelo','citaModelo');
        $this->load->library('form_validation');
    }
    function index(){
        $cita = new Cita();
        $cita->setEstado(0);
        $cita->setIdCita(1);
        $cita->setiCita(new CitaModelo());
        $cita->registrar("mario","1","2");
    }

    
}