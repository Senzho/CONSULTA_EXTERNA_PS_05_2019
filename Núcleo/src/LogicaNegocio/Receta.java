package LogicaNegocio;

import Recursos.DatosReceta;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Victor Javier
 * @version 1.0
 * @created 18-may.-2019 06:27:03 p. m.
 */
public class Receta {

    private String folio;
    private String instrucciones;
    private Date fechaVencimiento;
    private List<Medicamento> medicamentosReceta;
    private IReceta iReceta;
    
    private DatosReceta validarDatos(){
        DatosReceta datosReceta = DatosReceta.VALIDA;
        if (this.folio.trim().equals("") || this.folio == null){
            datosReceta = DatosReceta.FOLIO_VACIO;
        } else if (this.instrucciones.trim().equals("") || this.instrucciones == null){
            datosReceta = DatosReceta.INSTRUCCIONES_VACIAS;
        } else if (this.fechaVencimiento == null){
            datosReceta = DatosReceta.FECHA_VENCIMIENTO_VACIA;
        } else if (this.fechaVencimiento.equals(new Date())){
            datosReceta = DatosReceta.FECHA_VENCIMIENTO_ACTUAL;
        }
        return datosReceta;
    }

    public Receta() {
        this.folio = "";
        this.instrucciones = "";
        this.fechaVencimiento = new Date();
        this.medicamentosReceta = new ArrayList<>();
        this.iReceta = null;
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

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public List<Medicamento> getMedicamentosReceta() {
        return medicamentosReceta;
    }

    public void setMedicamentoReceta(List<Medicamento> medicamentosReceta) {
        this.medicamentosReceta = medicamentosReceta;
    }

    public IReceta getiReceta() {
        return iReceta;
    }

    public void setiReceta(IReceta iReceta) {
        this.iReceta = iReceta;
    }
    
    /**
     *
     * @param idConsulta
     * @return 
     */
    public DatosReceta registrar(int idConsulta) {
        DatosReceta validacion = this.validarDatos();
        if (validacion == DatosReceta.VALIDA){
            validacion = this.iReceta.registrar(this, idConsulta) ? DatosReceta.EXITO : DatosReceta.ERROR_ALMACENAMIENTO;
        }
        return validacion;
    }

    /**
     * #Recuerdo que esto es para el reporte, se obtendrían de algun periodo en especial?#
     * @param fechaMenor: recibe la fecha desde la cual se desea el histórico.
     * @param fechaMayor: recibe la fecha hasta la cual se desea el histórico.
     * @return 
     */
    //Agregadas fechas, no recibía nada.
    public List<Medicamento> obtenerMedicamentosRecetados(Date fechaMenor, Date fechaMayor) {
        return this.iReceta.obtenerMedicamentosRecetados();
    }
}//end Receta
