/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prueba;

import LogicaNegocio.IMedicamento;
import LogicaNegocio.Medicamento;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CrizUP
 */
public class MedicamentoPersistenciaPrueba implements IMedicamento{

    private List<Medicamento> medicamentos;
    private Medicamento medicamentoDefault;
    
    public MedicamentoPersistenciaPrueba() {
        this.medicamentos = new ArrayList<>();
        this.medicamentoDefault = new Medicamento();
        this.medicamentoDefault.setCodigo("123567");
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
        String fecha = "11-04-2022";
        Date fechaCaducidad = new Date();
        try {
            fechaCaducidad = formatoFecha.parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(PacientePrueba.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.medicamentoDefault.setFechaCaducidad(fechaCaducidad);
        this.medicamentoDefault.setNombre("Paracetamol");
        this.medicamentoDefault.setGramaje((float)500);
        this.medicamentoDefault.setiMedicamento(this);
        this.medicamentos.add(medicamentoDefault);
    }

    public Medicamento getMedicamentoDefault() {
        return medicamentoDefault;
    }

    public void setMedicamentoDefault(Medicamento medicamentoDefault) {
        this.medicamentoDefault = medicamentoDefault;
    }

    
    
    @Override
    public List<Medicamento> obtenerMedicamentos() {
        return this.medicamentos;
    }
    
}
