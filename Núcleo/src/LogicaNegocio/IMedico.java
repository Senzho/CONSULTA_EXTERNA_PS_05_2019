/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio;

import java.util.Date;

/**
 *
 * @author CrizUP
 */
public interface IMedico {
    public boolean registrar(Medico coordinador);
    public boolean modificar(Medico coordinador);
    public boolean registrarEntrada(String numeroPersonal, String numeroConsultorio);
    public boolean registrarSalida(String numeroPersonal);
    public boolean eliminar();
    public Medico obtenerMedico(String numeroPersonal);    
}
