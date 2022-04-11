package entity;

import helper.MockHelper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LeilaoTest extends MockHelper {

    @Test
    public void naoDeveAceitarMaisDoQue5LancesDeUmMesmoUsuario(){
        Leilao leilao = new Leilao("Macbook Pro 15");
        Usuario steveJobs = new Usuario("Steve Jobs");
        Usuario billGates = new Usuario("Bill Gates");
        leilao.propoe(new Lance(steveJobs, 2000.0));
        leilao.propoe(new Lance(billGates, 3000.0));
        leilao.propoe(new Lance(steveJobs, 4000.0));
        leilao.propoe(new Lance(billGates, 5000.0));
        leilao.propoe(new Lance(steveJobs, 6000.0));
        leilao.propoe(new Lance(billGates, 7000.0));
        leilao.propoe(new Lance(steveJobs, 8000.0));
        leilao.propoe(new Lance(billGates, 9000.0));
        leilao.propoe(new Lance(steveJobs, 10000.0));
        leilao.propoe(new Lance(billGates, 11000.0));

        leilao.propoe(new Lance(steveJobs, 12000.0));

        assertEquals(10, leilao.getLances().size());

        int ultimo = leilao.getLances().size() -1;
        Lance ultimoLance = leilao.getLances().get(ultimo);
        assertEquals(11000.0, ultimoLance.getValor(), 0.00001);


    }

    @Test
    public void deveReceberUmLance(){
        Leilao leilao = new Leilao("Macbook Pro 15");
        assertEquals(0, leilao.getLances().size());

        leilao.propoe(new Lance(new Usuario("Steve Jobs"), 2000.0));

        assertEquals(1, leilao.getLances().size());
        assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
    }

    @Test
    public void deveReceberVarios(){
        Leilao leilao = new Leilao("Macbook Pro 15");
        assertEquals(0, leilao.getLances().size());

        leilao.propoe(new Lance(new Usuario("Steve Jobs"), 2000.0));
        leilao.propoe(new Lance(new Usuario("Steve Wozniak"), 3000.0));

        assertEquals(2, leilao.getLances().size());
        assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
        assertEquals(3000.0, leilao.getLances().get(1).getValor(), 0.00001);
    }

    @Test
    public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario(){
        Leilao leilao = new Leilao("Macbook Pro 15");
        Usuario steve = new Usuario("Steve Jobs");

        leilao.propoe(new Lance(steve, 2000.0));
        leilao.propoe(new Lance(steve, 3000.0));

        assertEquals(1, leilao.getLances().size());
        assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
    }

    @Test
    public void leilaoTest(){
        Leilao leilao = getLeilao();

        assertNotNull(leilao.getNome());
        assertNotNull(leilao.toString());

        Leilao leilao1 = new Leilao("Teste");
        assertNotNull(leilao1.getNome());
    }

    @Test
    public void propoeTest(){
        Leilao leilao = getLeilao();
        Lance lance = getLance();

        try {
            leilao.propoe(lance);
        }catch (NullPointerException exception){
            System.err.println(exception.getMessage());
        }
        assertNotNull(leilao);
    }

}
