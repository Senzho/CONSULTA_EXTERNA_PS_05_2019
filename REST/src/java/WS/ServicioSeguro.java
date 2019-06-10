package WS;

import DataAccess.controladores.SesionesJpaController;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

/**
 *
 * @author Victor Javier
 */
public class ServicioSeguro {
    protected boolean tokenValido(String token) {
        boolean valido;
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ConsultaExterna_WSPU");
        SesionesJpaController sesionesJpaController = new SesionesJpaController(entityManagerFactory);
        try {
            sesionesJpaController.obtenerPorToken(token);
            valido = true;
        } catch(NoResultException excepcion) {
            valido = false;
        }
        return valido;
    }
    
    public ServicioSeguro() {
        
    }
}
