package LogicaNegocio;

import java.util.List;

/**
 * @author Victor Javier
 * @version 1.0
 * @created 18-may.-2019 06:27:03 p. m.
 */
public class Receta {

    private String folio;
    private String instrucciones;
    private String fechaVencimiento;
    private Medicamento medicamentoReceta;

    public Receta() {

    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(String instrucciones) {
        this.instrucciones = instrucciones;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Medicamento getMedicamentoReceta() {
        return medicamentoReceta;
    }

    public void setMedicamentoReceta(Medicamento medicamentoReceta) {
        this.medicamentoReceta = medicamentoReceta;
    }

    /**
     *
     * @param idConsulta
     */
    public boolean registrar(int idConsulta) {
        return false;
    }

    public List obtenerMedicamentosRecetados() {
        return null;
    }
}//end Receta
