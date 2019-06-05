/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prueba;

import LogicaNegocio.Cita;
import LogicaNegocio.ICita;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CrizUP
 */
public class CitaPersistenciaPrueba implements ICita{

    private MedicoPersistenciaPrueba medicos;
    private PacientePrueba pacientes;
    private List<Cita> citas;
    private Cita citaDefault;
    
    public CitaPersistenciaPrueba() {
        this.medicos = new MedicoPersistenciaPrueba();
        this.pacientes = new PacientePrueba();
        this.citas = new ArrayList<>();
        this.citaDefault = new Cita();
        this.citaDefault.setEstado(0);
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        formatoFecha.setTimeZone(TimeZone.getTimeZone("GMT"));
        String fecha = "2019-05-29";
        Date fechaCita = new Date();
        try {
            fechaCita = formatoFecha.parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(PacientePrueba.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.citaDefault.setHoraReserva(fechaCita);
        this.citaDefault.setMedicoCita(medicos.getMedico());
        this.citaDefault.setPacienteCita(pacientes.getPaciente());
        this.citaDefault.setiCita(this);
    }

    public MedicoPersistenciaPrueba getMedicos() {
        return medicos;
    }

    public void setMedicos(MedicoPersistenciaPrueba medicos) {
        this.medicos = medicos;
    }

    public PacientePrueba getPacientes() {
        return pacientes;
    }

    public void setPacientes(PacientePrueba pacientes) {
        this.pacientes = pacientes;
    }
    
    
    public boolean registrar(String numeroSeguro, String numeroPersonal, Date horaReserva) {
        boolean registrado = false;
        if(this.medicos.getMedico().obtenerMedico(numeroPersonal) != null && this.pacientes.getPaciente().obtenerPaciente(numeroSeguro) != null){
            boolean existe = false;
            for (Cita pacienteLista : this.citas){
                if (pacienteLista.getHoraReserva().equals(horaReserva)){
                    existe = true;
                    break;
                }
            }
            if (!existe) {
                Cita citaNueva = new Cita();
                citaNueva.setEstado(0);
                citaNueva.setHoraReserva(horaReserva);
                citaNueva.setIdCita(2);
                citaNueva.setMedicoCita(this.medicos.getMedico().obtenerMedico(numeroSeguro));
                citaNueva.setPacienteCita(this.pacientes.getPaciente().obtenerPaciente(numeroPersonal));
                this.citas.add(citaNueva);
                registrado = true;
            }
        } 
        return registrado;
    }
    
}
