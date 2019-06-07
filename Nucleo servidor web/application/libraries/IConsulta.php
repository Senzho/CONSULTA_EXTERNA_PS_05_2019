<?php
interface IConsulta{
    public function registrar($numeroSeguro,$numeroPersonal);
    public function obtenerHistorialClinico($numeroSeguro);
    public function obtenerHistorialBiometrico($numeroSeguro);
}