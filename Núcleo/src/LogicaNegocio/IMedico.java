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
    public Medico obtenerMedico(String numeroPersonal);
    public boolean registrarMedico(Medico medico);
    public boolean modificarMedico(Medico medico);
    public boolean registrarEntrada( String numeroConsultorio, String numPersonal);
    public boolean registrarSalida();
    public boolean cambiarEstado(boolean estado);
}
