<?php
interface ICoordinador{
    public function registrar($coordinador);
    public function modificar($coordinador);
    public function registrarEntrada($numeroPersonal, $numeroConsultorio);
    public function registrarSalida($numeroPersonal);
    public function eliminar($numeroPersonal);
    public function obtenerCoordinador($numeroPersonal);  
} 