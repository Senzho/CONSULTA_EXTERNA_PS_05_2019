/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio;

import Prueba.ConsultaPueba;
import Recursos.DatosConsulta;
import java.util.Date;
import java.util.List;
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
public class ConsultaTest {
    private ConsultaPueba consultaPrueba;
    private Consulta consulta;
    
    public ConsultaTest() {
        this.consultaPrueba = new ConsultaPueba();
        this.consulta = new Consulta();
        this.consulta.setDiagnostico("un diagnostico de prueba 2");
        this.consulta.setEstatura((float) 1.89);
        this.consulta.setFecha(new Date());
        this.consulta.setHoraFin(new Date());
        this.consulta.setHoraInicio(new Date());
        this.consulta.setIConsulta(consultaPrueba);
        this.consulta.setIdConsulta(1);
        this.consulta.setMedicoConsulta(new Medico());
        Paciente paciente = new Paciente();
        paciente.setNumeroSeguro("1998");
        this.consulta.setPacienteConsulta(paciente);
        this.consulta.setRecetaConsulta(new Receta());
        this.consulta.setPeso((float) 80.0);
        this.consulta.setPresion((float) 12.2);
        this.consulta.setTemperatura((float) 32.5);
    }
    @Test
    public void registrarConsultaTest(){
        assertTrue(this.consulta.registrar(consulta.getPacienteConsulta().getNumeroSeguro(), "10"));
    }
    @Test
    public void noRegistroConsultaTest(){
        assertFalse(this.consulta.registrar(consulta.getPacienteConsulta().getNumeroSeguro(), ""));
    }
    @Test
    public void obtenerHistorialBiometricoTest(){
        assertEquals(this.consultaPrueba.obtenerHistorialBiometrico("1997").size(),1);
    }
     @Test
    public void obtenerHistorialBiometricoErrorTest(){
        assertEquals(this.consulta.obtenerHistorialBiometrico().size(),0);
    }
    @Test
    public void obtenerHistorialClinicoTest(){
        assertEquals(this.consultaPrueba.obtenerHistorialClinico("1997").size(),1);
    }
    @Test
    public void obtenerHistorialClinicoErrorTest(){
        assertEquals(this.consulta.obtenerHistorialClinico(consulta.getPacienteConsulta().getNumeroSeguro()).size(),0);
    }
}
