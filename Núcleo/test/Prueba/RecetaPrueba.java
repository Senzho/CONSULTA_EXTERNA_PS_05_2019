package Prueba;

import LogicaNegocio.IReceta;
import LogicaNegocio.Medicamento;
import LogicaNegocio.Receta;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Victor Javier
 */
public class RecetaPrueba implements IReceta {
    private Map<Integer, Receta> recetas;
    
    public RecetaPrueba(){
        this.recetas = new HashMap<>();
    }

    @Override
    public boolean registrar(Receta receta, int idConsulta) {
        return this.recetas.put(idConsulta, receta) == null;
    }

    @Override
    public List<Medicamento> obtenerMedicamentosRecetados() {
        List<Medicamento> medicamentos = new ArrayList<>();
        for (int idConsulta : this.recetas.keySet()){
            Receta receta = this.recetas.get(idConsulta);
            receta.getMedicamentosReceta().forEach((medicamento) -> {
                medicamentos.add(medicamento);
            });
        }
        return medicamentos;
    }
}
