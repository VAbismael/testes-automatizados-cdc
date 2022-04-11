package entity;

import helper.MockHelper;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class LanceTest extends MockHelper {

    @Test
    public void lanceTest(){
        Lance lance = getLance();

        assertNotNull(lance.getUsuario());
        assertNotNull(lance.getValor());
        assertNotNull(lance.toString());


    }
}
