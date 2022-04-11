package service;

import builder.CriadorDeLeilao;
import dao.LeilaoDao;
import entity.Leilao;
import org.junit.Test;
import repository.RepositorioCarteiro;
import repository.RepositorioDeLeiloes;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class EncerradorDeLeilaoTest {

    @Test
    public void deveEncerrarLeiloesQueComecaramUmaSemanaAtras() {
        Calendar antiga = Calendar.getInstance();
        antiga.set(1999, Calendar.FEBRUARY, 20);

        Leilao leilao1 = new CriadorDeLeilao().para("TV de plasma")
                .naData(antiga).build();
        Leilao leilao2 = new CriadorDeLeilao().para("Geladeira")
                .naData(antiga).build();

        List<Leilao> leiloesAntigos = Arrays.asList(leilao1, leilao2);

        LeilaoDao daoFalso = mock(LeilaoDao.class);
        when(daoFalso.correntes()).thenReturn(leiloesAntigos);

        RepositorioCarteiro repositorioCarteiro = mock(RepositorioCarteiro.class);
        EncerradorDeLeilao encerrador = new EncerradorDeLeilao(daoFalso, repositorioCarteiro);
        encerrador.encerra();

        assertEquals(2, encerrador.getTotalEncerrados());
        assertTrue(leilao1.isEncerrado());
        assertTrue(leilao2.isEncerrado());
    }

    @Test
    public void naoDeveEncerrarLeiloesQueComecaramMenosDeUmaSemana(){

    }

    @Test
    public void naoDeveEncerrarLeiloesCasoNaoHajaNenhum(){

    }


    @Test
    public void deveAtualizarLeiloesEncerrados(){
        Calendar antiga = Calendar.getInstance();
        antiga.set(1999, Calendar.FEBRUARY, 20);

        Leilao leilao1 = new CriadorDeLeilao().para("TV de plasma")
                .naData(antiga).build();

        RepositorioCarteiro carteiro = mock(RepositorioCarteiro.class);
        RepositorioDeLeiloes daoFalso = mock(RepositorioDeLeiloes.class);
        when(daoFalso.correntes()).thenReturn(Collections.singletonList(leilao1));

        EncerradorDeLeilao encerradorDeLeilao = new EncerradorDeLeilao(daoFalso, carteiro);
        encerradorDeLeilao.encerra();

        verify(daoFalso, times(1)).atualiza(leilao1);
    }

    @Test
    public void deveContinuarAExecucaoMesmoQuandoDaoFalha(){
        Calendar antiga = Calendar.getInstance();
        antiga.set(1999, Calendar.JANUARY, 20);

        Leilao leilao1 = new CriadorDeLeilao()
                .para("TV de Plasma")
                .naData(antiga)
                .build();

        Leilao leilao2 = new CriadorDeLeilao()
                .para("Geladeira")
                .naData(antiga)
                .build();


        RepositorioDeLeiloes daoFalso = mock(RepositorioDeLeiloes.class);
        when(daoFalso.correntes()).thenReturn(Arrays.asList(leilao1,leilao2));

        doThrow(new RuntimeException()).when(daoFalso).atualiza(leilao1);

        RepositorioCarteiro correioFalso = mock(RepositorioCarteiro.class);

        EncerradorDeLeilao encerradorDeLeilao = new EncerradorDeLeilao(daoFalso, correioFalso);
        encerradorDeLeilao.encerra();

        verify(daoFalso).atualiza(leilao2);
        verify(correioFalso).envia(leilao2);
    }
}
