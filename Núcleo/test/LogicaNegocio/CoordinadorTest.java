/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio;

import Prueba.CoordinadorPrueba;
import Prueba.PacientePrueba;
import Recursos.DatosPersonal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mariolpz
 */
public class CoordinadorTest {
    private CoordinadorPrueba coordinadorPrueba;
    private Coordinador coordinador;
    
    public CoordinadorTest() {
        this.coordinadorPrueba = new CoordinadorPrueba();
        this.coordinador = new Coordinador();
        this.coordinador.setApellido("Gonzalez Hernandez");
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = "1992-04-22";
        Date fechaNacimiento = new Date();
        try {
            fechaNacimiento = formatoFecha.parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(PacientePrueba.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.coordinador.setFechaNacimiento(fechaNacimiento);
        this.coordinador.setNombre("Abril");
        this.coordinador.setNumeroPersonal("2016");
        this.coordinador.setNumeroTelefono("2281065539");
        this.coordinador.setRfc("zsg125hgti786");
        this.coordinador.setSexo('F');
        this.coordinador.setTurno("Matutino");
        this.coordinador.setICoordinador(coordinadorPrueba);
    }
    @Test
    public void registrarCordinadorTest(){
        assertEquals(this.coordinador.registrar(),DatosPersonal.EXITO);
    }
    @Test
    public void registrarCoordinadorInvalidoTest(){
        this.coordinador.setNumeroPersonal("2017");
        assertNotEquals(this.coordinador.registrar(), DatosPersonal.EXITO);
        this.coordinador.setNumeroPersonal("2016");
    }
    @Test
    public void editarCoordinadorTest(){
        this.coordinador.setNombre("Karen");
        assertEquals(this.coordinador.modificar(), DatosPersonal.EXITO);
        this.coordinador.setNombre("Abril");
    }
    @Test
    public void editarCoordinadorInvalidoTest(){
        this.coordinador.setNombre("");
        assertNotEquals(this.coordinador.modificar(), DatosPersonal.EXITO);
        this.coordinador.setNombre("Abril");   
    }
    @Test
    public void registrarEntradaTest(){
        assertTrue(this.coordinador.registrarEntrada(new Date()));
    }
     @Test
    public void registrarSalidaTest(){
        assertTrue(this.coordinador.registrarSalida(new Date()));
    }
    @Test
    public void cambiarEstadoTest(){
        assertTrue(this.coordinador.cambiarEstado(true));
    }
    @Test
    public void obtenerCoordinadorTest(){
        assertNotNull(this.coordinador.obtenerCoordinador("2017"));
    }
     @Test
    public void obtenerCoordinadorInvalidoTest(){
        assertNull(this.coordinador.obtenerCoordinador("2015"));
    }
}
