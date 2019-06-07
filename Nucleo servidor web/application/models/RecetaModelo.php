<?php

if ( ! defined('BASEPATH')) exit('No direct script access allowed');
interface_exists('IReceta', FALSE) OR require_once(APPPATH.'libraries/IReceta.php');

class RecetaModelo implements IReceta{
    public function registrar($receta, $idConsulta){

    }
    
    public function obtenerMedicamentosRecetados(){

    }
}