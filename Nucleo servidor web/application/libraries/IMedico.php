<?php
interface IMedico{
    public function registrar($medico,$idUsuario);
    public function modificar($medico);
    public function registrarEntrada($numeroPersonal, $numeroConsultorio);
    public function registrarSalida($numeroPersonal);
    public function eliminar();
    public function obtenerMedico($rfc);  
    public function obtenerMedicoId($idUsuario);
}