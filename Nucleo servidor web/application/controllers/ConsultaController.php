<?php
if ( ! defined('BASEPATH')) exit('No direct script access allowed');
interface_exists('IConsulta', FALSE) OR require_once(APPPATH.'libraries/IConsulta.php');

class ConsultaController extends CI_Controller {
    public function __construct(){
        parent::__construct();
        $this->load->helper('url');
        $this->load->helper('form');
        $this->load->library('Consulta');
        $this->load->model('ConsultaModelo','consultaModelo');
        $this->load->library('form_validation');
    }
    public function index(){
        $ConsultaModelo = new ConsultaModelo();
        $consulta = new Consulta();
        $consulta->setDiagnostico("un diagnostico de prueba 2");
        $consulta->setEstatura(1.89);
        $consulta->setFecha("2012-18-18");
        $consulta->setHoraFin("2012-18-18");
        $consulta->setHoraInicio("2012-18-18");
        $consulta->setIConsulta($ConsultaModelo);
        $consulta->setIdConsulta(1);
        $consulta->setMedicoConsulta(new Medico());
        $paciente = new Paciente();
        $paciente->setNumeroSeguro("1998");
        $consulta->setPacienteConsulta($paciente);
        $consulta->setRecetaConsulta(new Receta());
        $consulta->setPeso(80.0);
        $consulta->setPresion(12.2);
        $consulta>setTemperatura(32.5);
        $consulta->registrar($consulta->getPacienteConsulta()->getNumeroSeguro(),"10");
    }
}