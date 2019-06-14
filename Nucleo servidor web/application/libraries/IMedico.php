<?php
interface IMedico{
    public function registrar($medico,$idUsuario);
    public function modificar($medico);
    public function registrarEntrada($rfc, $numeroConsultorio);
    public function registrarSalida($rfc);
    public function eliminar($rfc);
    public function obtenerMedico($rfc);  
    public function obtenerMedicoId($idUsuario);
    public function obtenerMedicos();
}