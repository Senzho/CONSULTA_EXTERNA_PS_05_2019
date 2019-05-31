/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prueba;

import LogicaNegocio.Consulta;
import LogicaNegocio.IConsulta;
import LogicaNegocio.Medico;
import LogicaNegocio.Paciente;
import LogicaNegocio.Receta;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author mariolpz
 */
public class ConsultaPueba implements IConsulta{

    private List<Consulta> consultas;
    private Consulta consulta;
    private HashMap<String, String> registrosConsulta;
    
    public ConsultaPueba(){
        this.consultas = new ArrayList<>();
        this.registrosConsulta = new HashMap<String,String>();
        consulta = new Consulta();
        this.consulta.setDiagnostico("un diagnostico de prueba");
        this.consulta.setEstatura((float) 1.89);
        this.consulta.setFecha(new Date());
        this.consulta.setHoraFin(new Date());
        this.consulta.setHoraInicio(new Date());
        this.consulta.setIConsulta(this);
        this.consulta.setIdConsulta(1);
        this.consulta.setMedicoConsulta(new Medico());
        Paciente paciente = new Paciente();
        paciente.setNumeroSeguro("1997");
        this.consulta.setPacienteConsulta(paciente);
        this.consulta.setRecetaConsulta(new Receta());
        this.consulta.setPeso((float) 80.0);
        this.consulta.setPresion((float) 12.2);
        this.consulta.setTemperatura((float) 32.5);
        this.consultas.add(consulta);
    }
    @Override
    public boolean registrar(String numeroSeguro, String numeroPersonal) {
        boolean registrado = false;
        if(numeroSeguro.length() > 0 && numeroPersonal.length() > 0){
            this.registrosConsulta.put(numeroSeguro, numeroPersonal);
            registrado = true;
        }
        return registrado;
    }

    @Override
    public List obtenerHistorialClinico(String numeroSeguro) {
        List<Consulta> consultasHistorial = new ArrayList<>();
        for(Consulta consultaLista: consultas){
            if(consultaLista.getPacienteConsulta().getNumeroSeguro().equals(numeroSeguro)){
                consultasHistorial.add(consultaLista);
            }
        }
        return consultasHistorial;
    }

    @Override
    public List obtenerHistorialBiometrico(String numeroSeguro) {
        List<String> biometricoHistorial = new ArrayList<>();
        for(Consulta consultaLista: consultas){
            if(consultaLista.getPacienteConsulta().getNumeroSeguro().equals(numeroSeguro)){
                biometricoHistorial.add(consultaLista.getFecha().toString()+"Peso:"+consultaLista.getPeso()+" Presión:"+consultaLista.getPresion()+
                        " Temperatura:"+consultaLista.getTemperatura());
            }
        }
        return biometricoHistorial;
    }    
}
