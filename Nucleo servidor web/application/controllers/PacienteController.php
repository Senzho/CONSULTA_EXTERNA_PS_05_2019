<?php

if ( ! defined('BASEPATH')) exit('No direct script access allowed');
interface_exists('IPaciente', FALSE) OR require_once(APPPATH.'libraries/IPaciente.php');

class PacienteController extends CI_Controller{
    public function __construct(){
        parent::__construct();
        $this->load->helper('url');
        $this->load->helper('form');
        $this->load->library('Paciente');
        $this->load->model('PacienteModelo','pacienteModelo');
        $this->load->library('form_validation');
    }
    function index(){
        
    }
}