package LogicaNegocio;

import java.util.List;

/**
 *
 * @author Victor Javier
 */
public interface IReceta {
    public boolean registrar(Receta receta, int idConsulta);
    public List<Medicamento> obtenerMedicamentosRecetados();
}
