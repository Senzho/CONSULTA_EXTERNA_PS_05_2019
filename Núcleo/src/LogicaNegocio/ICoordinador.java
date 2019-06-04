/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio;

import java.util.Date;

/**
 *
 * @author mariolpz
 */
public interface ICoordinador {
    public boolean registrar(Coordinador coordinador);
    public boolean modificar(Coordinador coordinador);
    public boolean registrarEntrada(String numeroPersonal, String numeroConsultorio);
    public boolean registrarSalida(String numeroPersonal);
    public boolean eliminar(String numeroPersonal);
    public Coordinador obtenerCoordinador(String numeroPersonal);    
}
