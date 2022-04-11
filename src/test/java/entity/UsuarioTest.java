package entity;

import helper.MockHelper;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class UsuarioTest extends MockHelper {

    @Test
    public void usuarioTest(){
        Usuario usuario = getUsuario();
        assertNotNull(usuario.getNome());
        assertNotNull(usuario.toString());

        Usuario usuario1 = new Usuario("Usuarios");

        assertNotNull(usuario1.getNome());
        assertNotNull(usuario1.toString());
    }


}
