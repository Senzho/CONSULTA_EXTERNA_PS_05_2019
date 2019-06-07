<?php
if ( ! defined('BASEPATH')) exit('No direct script access allowed');
interface_exists('ICita', FALSE) OR require_once(APPPATH.'libraries/ICita.php');

class CitaModelo implements ICita {
    public function registrar($numeroSeguro, $numeroPersonal, $horaReserva){
        echo $numeroPersonal;//aqui debería ir la implementación de guardar en rest
    }
}