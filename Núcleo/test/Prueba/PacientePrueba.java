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
public class PacientePrueba implements IPaciente{
    private List<Paciente> pacientes;
    private Paciente paciente;

    public PacientePrueba() {
        this.pacientes = new ArrayList<>();
        this.paciente = new Paciente();
        this.paciente.setAlergias("Paracetamol");
        
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = "1996-04-22";
        Date fechaNacimiento = new Date();
        try {
            fechaNacimiento = formatoFecha.parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(PacientePrueba.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.paciente.setFechaNacimiento(fechaNacimiento);
        this.paciente.setNombre("Juan Manuel");
        this.paciente.setApellido("Mendoza Granados");
        this.paciente.setNumeroSeguro("5673937452542");
        this.paciente.setNumeroTelefono("2281927635");
        this.paciente.setSexo('M');
        this.paciente.setiPaciente(this);
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
        return this.pacientes.add(paciente);
    }

    @Override
    public boolean modificarPaciente(Paciente paciente) {
        boolean modificado = false;
        for (Paciente pacienteLista : this.pacientes){
            if (pacienteLista.getNumeroSeguro().equals(paciente.getNumeroSeguro())){
                pacienteLista.setAlergias(paciente.getAlergias());
                pacienteLista.setApellido(paciente.getApellido());
                pacienteLista.setFechaNacimiento(paciente.getFechaNacimiento());
                pacienteLista.setNombre(paciente.getNombre());
                pacienteLista.setNumeroTelefono(paciente.getNumeroTelefono());
                pacienteLista.setSexo(paciente.getSexo());
                modificado = true;
                break;
            }
        }
        return modificado;
    }
}
