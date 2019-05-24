package LogicaNegocio;

import Prueba.UsuarioPrueba;
import Recursos.DatosUsuario;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Victor Javier
 */
public class UsuarioTest {
    private UsuarioPrueba prueba;
    private Usuario usuario;
    
    public UsuarioTest() {
        this.prueba = new UsuarioPrueba();
        this.usuario = new Usuario();
        this.usuario.setContraseña("JuHGtr545yweg3");
        this.usuario.setIdUsuario(1000);
        this.usuario.setNombreUsuario("MANJARO");
        this.usuario.setiUsuario(this.prueba);
    }
    
    @Test
    public void iniciarSesionExistenteTest(){
        assertTrue(this.usuario.iniciarSesion("senzho", "JNHgtgGVTBtBtgB") > 0);
    }
    @Test
    public void iniciarSesionNoExistenteTest(){
        assertFalse(this.usuario.iniciarSesion("ras_nok", "JuHGtr545yweg3") > 0);
    }
    @Test
    public void registrarUsuarioValidoTest(){
        assertEquals(this.usuario.registrarUsuario(), DatosUsuario.EXITO);
    }
    @Test
    public void registrarUsuarioNoValidoTest(){
        String contraseña = this.usuario.getContraseña();
        this.usuario.setContraseña("kjh");
        assertNotEquals(this.usuario.registrarUsuario(), DatosUsuario.EXITO);
        this.usuario.setContraseña(contraseña);
    }
    @Test
    public void modificarValidoTest(){
        Usuario usuario = this.prueba.getUsuario();
        String nombre = usuario.getNombreUsuario();
        usuario.setNombreUsuario("MANJARO");
        assertEquals(usuario.modificar(), DatosUsuario.EXITO);
        usuario.setNombreUsuario(nombre);
    }
    @Test
    public void modificarNoValidoTest(){
        Usuario usuario = this.prueba.getUsuario();
        String nombre = usuario.getNombreUsuario();
        usuario.setNombreUsuario("");
        assertNotEquals(usuario.modificar(), DatosUsuario.EXITO);
        usuario.setNombreUsuario(nombre);
    }
}
