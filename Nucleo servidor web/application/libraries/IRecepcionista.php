<?php
interface IRecepcionista{
    public function registrar($recepcionista);
    public function modificar($recepcionista);
    public function registrarEntrada($numeroConsultorio, $numeroPersonal);
    public function registrarSalida($numeroPersonal);
    public function eliminar($numeroPersonal);
    public function agregarConsulta($consulta);
    public function obtenerCitas($fecha);
    public function obtenerRecepcionista($numeroPersonal);
}
