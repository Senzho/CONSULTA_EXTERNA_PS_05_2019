<?php
interface IRecepcionista{
    public function registrar($recepcionista, $idUsuario);
    public function modificar($recepcionista);
    public function registrarEntrada($numeroConsultorio, $rfc);
    public function registrarSalida($rfc);
    public function eliminar($rfc);
    public function agregarConsulta($consulta);
    public function obtenerCitas($fecha);
    public function obtenerRecepcionista($rfc);
    public function obtenerRecepcionistaId($idUsuario);
}
