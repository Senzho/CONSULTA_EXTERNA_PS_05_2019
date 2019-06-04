/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prueba;

import LogicaNegocio.Coordinador;
import LogicaNegocio.ICoordinador;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mariolpz
 */
public class CoordinadorPrueba implements ICoordinador{
    private List<Coordinador> coordinadores;
    private Coordinador coordinador;
    
    public CoordinadorPrueba(){
        this.coordinadores = new ArrayList<>();  
        this.coordinador = new Coordinador();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = "1996-04-22";
        Date fechaNacimiento = new Date();
        try {
            fechaNacimiento = formatoFecha.parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(PacientePrueba.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.coordinador.setFechaNacimiento(fechaNacimiento);
        this.coordinador.setNombre("Juan Manuel");
        this.coordinador.setApellido("Mendoza Granados");
        this.coordinador.setNumeroTelefono("2281927635");
        this.coordinador.setNumeroPersonal("2017");
        this.coordinador.setSexo('M');
        this.coordinador.setICoordinador(this);
        this.coordinadores.add(this.coordinador);
    }

    @Override
    public boolean registrar(Coordinador coordinador) {
        return this.coordinadores.add(coordinador);
    }

    @Override
    public boolean modificar(Coordinador coordinador) {
        boolean modificado = false;
        for(Coordinador coordinadorLista: coordinadores){
            if(coordinadorLista.getNumeroPersonal().equals(this.coordinador.getNumeroPersonal())){
                coordinadorLista.setNombre("Jose antonio");
                coordinadorLista.setSexo('F');
                coordinadorLista.setTurno("Diurno");
                modificado = true;
                break;
            }
        }
        return modificado;
    }

    @Override
    public boolean registrarEntrada(String numeroPersonal, String numeroConsultorio) {
        return true;
    }

    @Override
    public boolean registrarSalida(String numeroPersonal) {
        return true;
    }

    @Override
    public boolean eliminar(String numeroPersonal) {
        return true;
    }

    @Override
    public Coordinador obtenerCoordinador(String numeroPersonal) {
        Coordinador coordinadorRegistro = null;
        coordinadores.size();
        for(Coordinador coordinadorLista : coordinadores){
            if(coordinadorLista.getNumeroPersonal().equals(numeroPersonal)){
                coordinadorRegistro = coordinadorLista;
                break;
            }
        }
        return coordinadorRegistro;
    }
    
    
}
