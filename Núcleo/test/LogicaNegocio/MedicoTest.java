/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio;

import Prueba.MedicoPersistenciaPrueba;
import Prueba.PacientePrueba;
import Recursos.DatosMedico;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author CrizUP
 */
public class MedicoTest {
    
    private MedicoPersistenciaPrueba prueba;
    private Medico medico;
    
    public MedicoTest() {
        this.prueba = new MedicoPersistenciaPrueba();
        this.medico = new Medico();
        this.medico.setNombre("gabriela".toUpperCase());
        this.medico.setApellido("Ubaldo sangabriel".toUpperCase());
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = "1997-04-22";
        Date fechaNacimiento = new Date();
        try {
            fechaNacimiento = formatoFecha.parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(PacientePrueba.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.medico.setFechaNacimiento(fechaNacimiento);
        this.medico.setNumeroPersonal("15011675");
        this.medico.setNumeroTelefono("522282813845");
        this.medico.setRfc("UASG970422");
        this.medico.setSexo('F');
        this.medico.setTurno("Vespertino".toUpperCase());
        this.medico.setiMedico(prueba);
    }
    @Test
    public void obtenerMedicoValidoTest(){
        assertNotNull(this.prueba.obtenerMedico("15011674"));
    }
     
    @Test
    public void obtenerMedicoNoExistenteoTest(){
        assertNull(this.prueba.obtenerMedico("1501167422"));
    }
    @Test
    public void registrarExitoTest() {
        assertEquals(this.medico.registrarMedico(), DatosMedico.EXITO);
    }

    @Test
    public void registrarNoExitoTest() {
        this.medico.setApellido("");
        assertNotEquals(this.medico.registrarMedico(), DatosMedico.EXITO);
        this.medico.setApellido("Ubaldo sangabriel");
    }
    
    @Test
    public void testModificar() {
        Medico medicoModificado = this.prueba.getMedico();
        medicoModificado.setNumeroTelefono("2283818245");
        assertEquals(medicoModificado.modificarMedico(), DatosMedico.EXITO);
    }

    @Test
    public void testModificarFallo() {
        Medico medicoModificado = this.prueba.getMedico();
        medicoModificado.setNumeroTelefono("");
        assertNotEquals(medicoModificado.modificarMedico(), DatosMedico.EXITO);
    }
    
}
