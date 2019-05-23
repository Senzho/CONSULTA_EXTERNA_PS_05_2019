package LogicaNegocio;

import Recursos.DatosPaciente;
import Prueba.PacientePrueba;
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
    private PacientePrueba prueba;
    private Paciente paciente;
    
    public PacienteTest() {
        this.prueba = new PacientePrueba();
        this.paciente = new Paciente();
        this.paciente.setAlergias("Naproxeno");
        this.paciente.setApellido("García Mascareñas");
        
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = "1996-04-22";
        Date fechaNacimiento = new Date();
        try {
            fechaNacimiento = formatoFecha.parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(PacientePrueba.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.paciente.setFechaNacimiento(fechaNacimiento);
        this.paciente.setNombre("Víctor Javier");
        this.paciente.setNumeroSeguro("987639587263");
        this.paciente.setNumeroTelefono("3372847362");
        this.paciente.setSexo('M');
        this.paciente.setiPaciente(this.prueba);
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
        assertEquals(this.paciente.registrarPaciente(), DatosPaciente.EXITO);
    }
    @Test
    public void registrarPacienteNoValidoTest(){
        Date fechaNacimiento = this.paciente.getFechaNacimiento();
        this.paciente.setFechaNacimiento(null);
        assertNotEquals(this.paciente.registrarPaciente(), DatosPaciente.EXITO);
        this.paciente.setFechaNacimiento(fechaNacimiento);
    }
    @Test
    public void modificarPacienteValidoTest(){
        Paciente paciente = this.prueba.getPaciente();
        String alergias = paciente.getAlergias();
        paciente.setAlergias("Subsalicilato de Bismito, Ranitidina");
        assertEquals(paciente.modificarPaciente(), DatosPaciente.EXITO);
        paciente.setAlergias(alergias);
    }
    @Test
    public void modificarPacienteNoValidoTest(){
        Paciente paciente = this.prueba.getPaciente();
        String alergias = paciente.getAlergias();
        paciente.setAlergias("");
        assertNotEquals(paciente.modificarPaciente(), DatosPaciente.EXITO);
        paciente.setAlergias(alergias);
    }
}
