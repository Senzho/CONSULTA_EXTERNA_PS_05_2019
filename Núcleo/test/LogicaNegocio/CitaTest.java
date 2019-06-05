/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio;

import Prueba.CitaPersistenciaPrueba;
import Prueba.PacientePrueba;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author CrizUP
 */
public class CitaTest {
    
    private CitaPersistenciaPrueba prueba;
    private Cita cita;
    public CitaTest() {
        prueba = new CitaPersistenciaPrueba();
        this.cita = new Cita();
        this.cita.setEstado(0);
        this.cita.setIdCita(3);
        this.cita.setiCita(prueba);
        
    }

    @Test
    public void testRegistrar() {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");        
        String fecha = "2019-05-29";
        Date fechaCita = new Date();
        try {
            fechaCita = formatoFecha.parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(PacientePrueba.class.getName()).log(Level.SEVERE, null, ex);
        }
        boolean registro  =cita.registrar("5673937452542", "15011674", fechaCita);
        assertTrue(registro);
        
    }
    
    @Test
    public void testRegistrarMedicoOPacienteNoExistentes() {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");        
        String fecha = "2019-05-29";
        Date fechaCita = new Date();
        try {
            fechaCita = formatoFecha.parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(PacientePrueba.class.getName()).log(Level.SEVERE, null, ex);
        }
        boolean registro  =cita.registrar("5673937452142", "15011774", fechaCita);
        assertFalse(registro);
        
    }
    
}
