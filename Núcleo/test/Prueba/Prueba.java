package Prueba;

import LogicaNegocio.IPaciente;
import LogicaNegocio.Paciente;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Victor Javier
 */
public class Prueba implements IPaciente{
    private List<Paciente> pacientes;
    private Paciente paciente;

    public Prueba() {
        this.pacientes = new ArrayList<Paciente>();
        this.paciente = new Paciente();
        this.paciente.setAlergias("Paracetamol");
        
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = "1996-04-22";
        Date fechaNacimiento = new Date();
        try {
            fechaNacimiento = formatoFecha.parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(Prueba.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.paciente.setFechaNacimiento(fechaNacimiento);
        this.paciente.setNombre("Juan Manuel");
        this.paciente.setApellido("Mendoza Granados");
        this.paciente.setNumeroSeguro("5673937452542");
        this.paciente.setNumeroTelefono("2281927635");
        this.paciente.setSexo('M');
        this.pacientes.add(this.paciente);
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    
    @Override
    public Paciente obtenerPaciente(String numeroSeguro){
        Paciente paciente = null;
        for (Paciente pacienteLista : this.pacientes){
            if (pacienteLista.getNumeroSeguro().equals(numeroSeguro)){
                paciente = pacienteLista;
                break;
            }
        }
        return paciente;
    }

    @Override
    public boolean registrarPaciente(Paciente paciente) {
        this.pacientes.add(paciente);
        return true;
    }

    @Override
    public boolean modificarPaciente(Paciente paciente) {
        return false;
    }
}
