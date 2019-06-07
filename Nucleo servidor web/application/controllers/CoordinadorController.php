<?php

if ( ! defined('BASEPATH')) exit('No direct script access allowed');
interface_exists('ICoordinador', FALSE) OR require_once(APPPATH.'libraries/ICoordinador.php');

class CoordinadorController extends CI_Controller{
    public function __construct(){
        parent::__construct();
        $this->load->helper('url');
        $this->load->helper('form');
        $this->load->library('Coordinador');
        $this->load->model('CoordinadorModelo','coordinadorModelo');
        $this->load->library('form_validation');
    }
    function index(){
        $coordinador = new Coordinador();
        $coordinador->setApellido("Gonzalez Hernandez");
        $coordinador->setFechaNacimiento("12-jun-2012");
        $coordinador->setNombre("Abril");
        $coordinador->setNumeroPersonal("2016");
        $coordinador->setNumeroTelefono("2281065539");
        $coordinador->setRfc("zsg125hgti786");
        $coordinador->setSexo('F');
        $coordinador->setTurno("Matutino");
        $coordinador->setNombre("mario");
        $coordinador->setICoordinador(new CoordinadorModelo());
        $coordinador->registrar();
    }
}