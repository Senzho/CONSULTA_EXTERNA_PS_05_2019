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
public interface ICita {
    public boolean registrar(String numeroSeguro, String numeroPersonal, Date horaReserva);
}
