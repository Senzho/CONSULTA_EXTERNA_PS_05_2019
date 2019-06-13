<?php
interface IPaciente{
    public function obtenerPaciente($numeroSeguro);
    public function obtenerPacientes();
    public function registrarPaciente($paciente);
    public function modificarPaciente($paciente);
}