<?php

if ( ! defined('BASEPATH')) exit('No direct script access allowed');
interface_exists('IMedicamento', FALSE) OR require_once(APPPATH.'libraries/IMedicamento.php');

class MedicamentoController Extends CI_Controller{

    public function __construct(){
        parent::__construct();
        $this->load->helper('url');
        $this->load->helper('form');
        $this->load->library('Medicamento');
        $this->load->model('MedicamentoModelo','medicamentoModelo');
        $this->load->library('form_validation');
    }
    function index(){
        
    }
}