package service;

import builder.CriadorDeLeilao;
import entity.Lance;
import entity.Leilao;
import entity.Usuario;
import helper.MockHelper;

import org.junit.Before;
import org.junit.Test;
import service.Avaliador;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.*;

public class AvaliadorTest extends MockHelper {

    private Avaliador leiloeiro;

    @Before
    public void criaAvaliador(){
        this.leiloeiro = new Avaliador();
    }


    @Test
    public void deveEntenderLancesEmOrdemCrescente(){
        Leilao leilao = new CriadorDeLeilao()
                .para("Xbox One S")
                .lance(new Usuario("Kratos"), 2000.0)
                .lance(new Usuario("Cecilia"), 1000.0)
                .lance(new Usuario("Vinicius"), 3000.0)
                .build();

        leiloeiro.avalia(leilao);

        assertThat(leiloeiro.getMenorLance(), equalTo(1000.0));
        assertThat(leiloeiro.getMaiorLance(), equalTo(3000.0));
    }

    @Test
    public void deveEntenderLeilaoComApenasUmLance(){
        Usuario usuario = getUsuario();
        Leilao leilao = getLeilao();

        leilao.propoe(getLance());

        leiloeiro.avalia(leilao);

        assertEquals(1000, leiloeiro.getMaiorLance(), 0.0001);
        assertEquals(1000, leiloeiro.getMenorLance(), 0.0001);
    }

    @Test
    public void deveEncontrarOsTresMaioresLances(){
        Usuario joao = new Usuario("Joao");
        Usuario maria = new Usuario("Maria");
        Leilao leilao = new CriadorDeLeilao()
                .para("Playstation 4")
                .lance(joao, 100.0)
                .lance(maria, 200.0)
                .lance(joao, 300.0)
                .lance(maria, 400.0)
                .build();

        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();

        assertEquals(3, maiores.size());
        assertEquals(400, maiores.get(0).getValor(), 0.0001);
        assertEquals(300, maiores.get(1).getValor(), 0.0001);
        assertEquals(200, maiores.get(2).getValor(), 0.0001);

        assertThat(maiores, hasItems(new Lance(maria, 400.0),
                                     new Lance(joao, 300.0),
                                     new Lance(maria, 200.0 )
        ));
    }

    @Test(expected = RuntimeException.class)
    public void naoDeveAvaliarLeiloesSemNenhumLaceDado(){
        Leilao leilao = new CriadorDeLeilao()
                .para("Casa Grande")
                .build();
        leiloeiro.avalia(leilao);
    }

}
