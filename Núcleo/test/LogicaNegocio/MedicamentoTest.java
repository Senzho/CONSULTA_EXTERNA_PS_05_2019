/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio;

import Prueba.MedicamentoPersistenciaPrueba;
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
 * @author CrizUP
 */
public class MedicamentoTest {
    
    private MedicamentoPersistenciaPrueba prueba;
    public MedicamentoTest() {
        this.prueba = new MedicamentoPersistenciaPrueba();
    }

    @Test
    public void testObtenerMedicamentos() {
        Medicamento medicamento = this.prueba.getMedicamentoDefault();
        assertEquals(medicamento.obtenerMedicamentos().size(),1);
    }
    
}
