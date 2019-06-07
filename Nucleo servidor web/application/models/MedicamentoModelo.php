<?php
if ( ! defined('BASEPATH')) exit('No direct script access allowed');
interface_exists('IMedicamento', FALSE) OR require_once(APPPATH.'libraries/IMedicamento.php');

class MedicamentoModelo implements IMedicamento{
    
    public function obtenerMedicamentos(){

    }
}