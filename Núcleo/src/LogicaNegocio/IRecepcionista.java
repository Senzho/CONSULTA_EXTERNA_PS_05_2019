/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio;

import java.util.Date;
import java.util.List;

/**
 *
 * @author mariolpz
 */
public interface IRecepcionista {
    
    public boolean registrar(Recepcionista recepcionista);
    public boolean modificar(Recepcionista recepcionista);
    public boolean registrarEntrada(String numeroPersonal, String numeroConsultorio);
    public boolean registrarSalida(String numeroPersonal);
    public boolean eliminar();
    public boolean agregarConsulta(Consulta consulta);
    public List obtenerCitas(Date fecha);
    public Recepcionista obtenerRecepcionista(String numeroPersonal);
}
