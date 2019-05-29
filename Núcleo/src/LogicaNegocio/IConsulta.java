/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio;

import java.util.List;

/**
 *
 * @author mariolpz
 */
public interface IConsulta {
     public boolean registrar(String numeroSeguro, String numeroPersonal);
     public List obtenerHistorialClinico(String numeroSeguro);
      public List obtenerHistorialBiometrico(String numeroSeguro);
}
