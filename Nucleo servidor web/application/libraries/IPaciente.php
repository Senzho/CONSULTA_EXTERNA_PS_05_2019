<?php
interface IPaciente{
    public function obtenerPaciente($numeroSeguro);
    public function registrarPaciente($paciente);
    public function modificarPaciente($paciente);
}