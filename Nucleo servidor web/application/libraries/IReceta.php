<?php
interface IReceta{
    public function registrar($receta, $idConsulta);
    public function obtenerMedicamentosRecetados();
}