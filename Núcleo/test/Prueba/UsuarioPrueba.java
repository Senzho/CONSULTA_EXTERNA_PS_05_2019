package Prueba;

import LogicaNegocio.IUsuario;
import LogicaNegocio.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Victor Javier
 */
public class UsuarioPrueba implements IUsuario {
    private List<Usuario> usuarios;
    private Usuario usuario;

    public UsuarioPrueba() {
        this.usuarios = new ArrayList<>();
        this.usuario = new Usuario();
        this.usuario.setContraseña("JNHgtgGVTBtBtgB");
        this.usuario.setIdUsuario(1);
        this.usuario.setNombreUsuario("senzho");
        this.usuario.setiUsuario(this);
        this.usuarios.add(this.usuario);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean registrarUsuario(Usuario usuario) {
        return this.usuarios.add(usuario);
    }

    @Override
    public boolean modificar(Usuario usuario) {
        boolean modificado = false;
        for (Usuario usuarioLista : this.usuarios){
            if (usuarioLista.getIdUsuario() == usuario.getIdUsuario()){
                usuarioLista.setContraseña(usuario.getContraseña());
                usuarioLista.setNombreUsuario(usuario.getNombreUsuario());
                modificado = true;
                break;
            }
        }
        return modificado;
    }

    @Override
    public int iniciarSesion(String nombre, String contraseña) {
        int idUsuario = 0;
        for (Usuario usuario : this.usuarios){
            if (usuario.getNombreUsuario().equals(nombre) && usuario.getContraseña().equals(contraseña)){
                idUsuario = usuario.getIdUsuario();
                break;
            }
        }
        return idUsuario;
    }
}
