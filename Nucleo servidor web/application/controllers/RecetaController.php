<?php

if ( ! defined('BASEPATH')) exit('No direct script access allowed');
interface_exists('IReceta', FALSE) OR require_once(APPPATH.'libraries/IReceta.php');

class RecetaController extends CI_Controller{
    public function __construct(){
        parent::__construct();
        $this->load->helper('url');
        $this->load->helper('form');
        $this->load->library('Receta');
        $this->load->model('RecetaModelo','RecetaModelo');
        $this->load->library('form_validation');
    }
    public function index(){
        
    }

}