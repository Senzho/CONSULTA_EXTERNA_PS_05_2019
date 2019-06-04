/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prueba;

import LogicaNegocio.Cita;
import LogicaNegocio.Consulta;
import LogicaNegocio.Recepcionista;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import LogicaNegocio.IRecepcionista;

/**
 *
 * @author mariolpz
 */
public class RecepcionistaPrueba implements IRecepcionista {
    private List<Recepcionista> recepcionistas;
    private List<Consulta> consultas;
    private List<Cita> citas;
    private Recepcionista recepcionista; 
    
    public RecepcionistaPrueba(){
        this.recepcionistas = new ArrayList<>();
        this.consultas = new ArrayList<>();
        this.citas = new ArrayList<>();
        this.recepcionista = new Recepcionista();
        this.recepcionista.setApellido("López Hernandez");
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = "1996-04-22";
        Date fechaNacimiento = new Date();
        try {
            fechaNacimiento = formatoFecha.parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(PacientePrueba.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.recepcionista.setFechaNacimiento(fechaNacimiento);
        this.recepcionista.setNombre("Mario");
        this.recepcionista.setNumeroPersonal("2015");
        this.recepcionista.setNumeroTelefono("2281065539");
        this.recepcionista.setRfc("AS1235FR65MCV");
        this.recepcionista.setSexo('M');
        this.recepcionista.setTurno("Nocturno");
        this.recepcionista.setiRecepcionista(this);
        this.recepcionistas.add(this.recepcionista);

        
    }

    @Override
    public boolean registrar(Recepcionista recepcionista) {
       return this.recepcionistas.add(recepcionista);
    }

    @Override
    public boolean modificar(Recepcionista recepcionista) {
        boolean modificado = false;
        for(Recepcionista recepcionistaLista: recepcionistas){
            if(recepcionistaLista.getNumeroPersonal().equals(this.recepcionista.getNumeroPersonal())){
                recepcionistaLista.setNombre("Jose antonio");
                recepcionistaLista.setSexo('F');
                recepcionistaLista.setTurno("Diurno");
                modificado = true;
                break;
            }
        }
        return modificado;
    }

    @Override
    public boolean registrarEntrada(String numeroConsultorio, String numeroPersonal) {
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
    public boolean agregarConsulta(Consulta consulta) {
        return this.consultas.add(consulta);
    }

    @Override
    public List obtenerCitas(Date fecha) {
        this.citas.add(new Cita());
        return this.citas;
    }

    @Override
    public Recepcionista obtenerRecepcionista(String numeroPersonal) {
        Recepcionista recepcionista = null;
        for(Recepcionista recepcionistaLista : recepcionistas){
            if(recepcionistaLista.getNumeroPersonal().equals(numeroPersonal)){
                recepcionista = recepcionistaLista;
                break;
            }
        }
        return recepcionista;
    }

    
    
}
