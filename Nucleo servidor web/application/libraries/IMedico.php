<?php
interface IMedico{
    public function registrar($coordinador);
    public function modificar($coordinador);
    public function registrarEntrada($numeroPersonal, $numeroConsultorio);
    public function registrarSalida($numeroPersonal);
    public function eliminar();
    public function obtenerMedico($numeroPersonal);  
}