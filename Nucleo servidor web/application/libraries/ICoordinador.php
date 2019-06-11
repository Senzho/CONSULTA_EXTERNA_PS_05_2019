<?php
interface ICoordinador{
    public function registrar($coordinador, $idUsuario);
    public function modificar($coordinador);
    public function registrarEntrada($rfc, $numeroConsultorio);
    public function registrarSalida($rfc);
    public function eliminar($rfc);
    public function obtenerCoordinador($rfc);  
    public function obtenerCoordinadorId($idUsuario);  
} 