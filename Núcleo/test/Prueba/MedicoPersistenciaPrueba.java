/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prueba;

import LogicaNegocio.IMedico;
import LogicaNegocio.Medico;
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
public class MedicoPersistenciaPrueba implements IMedico{

    List<Medico> medicos;
    Medico medicoDefault;

    public MedicoPersistenciaPrueba() {
        this.medicos = new ArrayList<>();
        this.medicoDefault = new Medico();
        this.medicoDefault.setNombre("Cristhian".toUpperCase());
        this.medicoDefault.setApellido("Ubaldo Promotor".toUpperCase());
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = "1996-04-22";
        Date fechaNacimiento = new Date();
        try {
            fechaNacimiento = formatoFecha.parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(PacientePrueba.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.medicoDefault.setFechaNacimiento(fechaNacimiento);
        this.medicoDefault.setNumeroPersonal("15011674");
        this.medicoDefault.setNumeroTelefono("522282813871");
        this.medicoDefault.setRfc("UAPC970416");
        this.medicoDefault.setSexo('M');
        this.medicoDefault.setTurno("matutino".toUpperCase());
        this.medicos.add(medicoDefault);
        this.medicoDefault.setiMedico(this);
        
    }
    
     public Medico getMedico() {
        return medicoDefault;
    }

    public void setMedico(Medico medico) {
        this.medicoDefault = medico;
    }

    
    
    @Override
    public Medico obtenerMedico(String numeroPersonal) {
        Medico medico = null;
        for (Medico mediciLista : this.medicos){
            if (mediciLista.getNumeroPersonal().equals(numeroPersonal)){
                medico = mediciLista;
                break;
            }
        }
        return medico;
    }

    @Override
    public boolean registrarMedico(Medico medico) {
        boolean registrado = false;
        if(obtenerMedico(medico.getNumeroPersonal()) == null){
            registrado = this.medicos.add(medico);
        } 
        return registrado;
    }

    @Override
    public boolean modificarMedico(Medico medico) {
         boolean modificado = false;
        for (Medico pacienteLista : this.medicos){
            if (pacienteLista.getNumeroPersonal().equals(medico.getNumeroPersonal())){
                pacienteLista.setRfc(medico.getRfc());
                pacienteLista.setApellido(medico.getApellido());
                pacienteLista.setFechaNacimiento(medico.getFechaNacimiento());
                pacienteLista.setNombre(medico.getNombre());
                pacienteLista.setNumeroTelefono(medico.getNumeroTelefono());
                pacienteLista.setSexo(medico.getSexo());
                pacienteLista.setNumeroTelefono(medico.getNumeroTelefono());
                pacienteLista.setTurno(medico.getTurno());
                modificado = true;
                break;
            }
        }
        return modificado;
    }

    /**
     * lo siguientes metodos actuarián en una tabla  de registros en la bd 
     * por lo que no importa 
     */
    @Override
    public boolean registrarEntrada(String numeroConsultorio, String numPersonal) {
        return true;
    }

    @Override
    public boolean registrarSalida() {
        return true;
    }

    @Override
    public boolean cambiarEstado(boolean estado) {
        return true;
    }
    
}
