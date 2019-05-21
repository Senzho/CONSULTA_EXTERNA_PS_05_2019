package LogicaNegocio;

import Recursos.DatosPaciente;
import Prueba.Prueba;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Victor Javier
 */
public class PacienteTest {
    private Prueba prueba;
    
    public PacienteTest() {
        this.prueba = new Prueba();
    }
    
    @Test
    public void obtenerEdadValidaTest(){
        assertEquals(prueba.getPaciente().obtenerEdad(), 23);
    }
    @Test
    public void obtenerEdadNoValidaTest(){
        assertNotEquals(prueba.getPaciente().obtenerEdad(), 22);
    }
    @Test
    public void obtenerEdadNoValidaTest2(){
        assertNotEquals(prueba.getPaciente().obtenerEdad(), 24);
    }
    @Test
    public void obtenerPacienteValidoTest(){
        assertNotNull(this.prueba.obtenerPaciente("5673937452542"));
    }
    @Test
    public void obtenerPacienteNoValidoTest(){
        assertNull(this.prueba.obtenerPaciente("5673933452542"));
    }
    @Test
    public void registrarPacienteValidoTest(){
        Paciente pacienteRegistro = new Paciente();
        pacienteRegistro.setAlergias("Naproxeno");
        pacienteRegistro.setApellido("García Mascareñas");
        
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = "1996-04-22";
        Date fechaNacimiento = new Date();
        try {
            fechaNacimiento = formatoFecha.parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(Prueba.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        pacienteRegistro.setFechaNacimiento(fechaNacimiento);
        pacienteRegistro.setNombre("Víctor Javier");
        pacienteRegistro.setNumeroSeguro("987639587263");
        pacienteRegistro.setNumeroTelefono("3372847362");
        pacienteRegistro.setSexo('M');
        pacienteRegistro.setiPaciente(this.prueba);
        
        assertEquals(pacienteRegistro.registrarPaciente(), DatosPaciente.EXITO);
    }
}
