package LogicaNegocio;

import Prueba.PacientePrueba;
import Prueba.RecetaPrueba;
import Recursos.DatosReceta;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Victor Javier
 */
public class RecetaTest {
    private RecetaPrueba prueba;
    private Receta receta;
    
    public RecetaTest() {
        this.prueba = new RecetaPrueba();
        this.receta = new Receta();
        
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = "2021-04-22";
        Date fechaVencimiento = new Date();
        try {
            fechaVencimiento = formatoFecha.parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(PacientePrueba.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.receta.setFechaVencimiento(fechaVencimiento);
        this.receta.setFolio("HY6KJAO98MH12");
        this.receta.setInstrucciones("Tomar una capsula de Amancencera por la mañana y una por la noche, por 7 días");
        
        List<Medicamento> medicamentos = new ArrayList<>();
        Medicamento medicamento = new Medicamento();
        medicamento.setCodigo("84636272229");
        medicamento.setFechaCaducidad(fechaVencimiento);
        medicamento.setGramaje(10);
        medicamento.setNombre("Amancencera");
        medicamentos.add(medicamento);
        
        this.receta.setMedicamentoReceta(medicamentos);
        this.receta.setiReceta(this.prueba);
    }
    
    @Test
    public void registrarValidaTest(){
        assertEquals(this.receta.registrar(1020), DatosReceta.EXITO);
    }
    @Test
    public void registrarNoValidaTest(){
        Date fechaVencimiento = this.receta.getFechaVencimiento();
        this.receta.setFechaVencimiento(new Date());
        assertNotEquals(this.receta.registrar(1020), DatosReceta.EXITO);
        this.receta.setFechaVencimiento(fechaVencimiento);
    }
}
