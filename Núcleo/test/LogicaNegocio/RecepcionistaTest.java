/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio;

import Prueba.PacientePrueba;
import Prueba.RecepcionistaPrueba;
import Recursos.DatosPersonal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mariolpz
 */
public class RecepcionistaTest {
    private RecepcionistaPrueba recepcionistaPrueba;
    private Recepcionista recepcionista;
    
    public RecepcionistaTest() {
        this.recepcionistaPrueba = new RecepcionistaPrueba();
        this.recepcionista = new Recepcionista();
        this.recepcionista.setApellido("Gonzalez Hernandez");
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = "1992-04-22";
        Date fechaNacimiento = new Date();
        try {
            fechaNacimiento = formatoFecha.parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(PacientePrueba.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.recepcionista.setFechaNacimiento(fechaNacimiento);
        this.recepcionista.setNombre("Abril");
        this.recepcionista.setNumeroPersonal("2016");
        this.recepcionista.setNumeroTelefono("2281065539");
        this.recepcionista.setRfc("zsg125hgti786");
        this.recepcionista.setSexo('F');
        this.recepcionista.setTurno("Matutino");
        this.recepcionista.setiRecepcionista(recepcionistaPrueba);
    }
    @Test
    public void registrarRecepcionistaTest(){
        assertEquals(this.recepcionista.registrar(), DatosPersonal.EXITO);
    }
    @Test
    public void registrarRecepcionistaNoExitoTest(){
        this.recepcionista.setNombre("");
        assertNotEquals(recepcionista.registrar(), DatosPersonal.EXITO);
        this.recepcionista.setNombre("Abril");
    }
    @Test
    public void modificarRecepcionistaTest(){
        this.recepcionista.setNombre("Karen");
        assertEquals(this.recepcionista.modificar(),DatosPersonal.EXITO);
    }
    @Test
    public void editarRecepcionistaNoExitoTest(){
        this.recepcionista.setNombre("");
        assertNotEquals(recepcionista.modificar(), DatosPersonal.EXITO);
        this.recepcionista.setNombre("Abril");
    }
    @Test
    public void registrarEntradaTest(){
        assertTrue(recepcionista.registrarEntrada("12"));
    }
    @Test
    public void registrarSalidaTest(){
        assertTrue(recepcionista.registrarSalida());
    }
    public void registrarConsultaTest(){
        Consulta consulta = new Consulta();
        assertTrue(recepcionista.agregarConsulta(consulta));
    }
    public void cambiarEstadoTest(){
        assertTrue(this.recepcionista.eliminar());
    }
    @Test
    public void obtenerCitasTest(){
        System.out.println(this.recepcionista.obtenerCitas(new Date()).size());
        assertEquals(this.recepcionista.obtenerCitas(new Date()).size(), 2);
    }    
    @Test
    public void obtenerRecepcionistaValidoTest(){
        assertEquals(this.recepcionista.registrar(), DatosPersonal.EXITO);
    }
    @Test
    public void obtenerRecepcionistaNoExistenteTest(){
        assertNull(this.recepcionistaPrueba.obtenerRecepcionista("2017"));
    }
    
}
