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
        
    }

}