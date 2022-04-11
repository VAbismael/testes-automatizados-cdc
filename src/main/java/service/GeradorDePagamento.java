package service;

import entity.Leilao;
import entity.Pagamento;
import repository.RepositorioDeLeiloes;
import repository.RepositorioDePagamentos;

import java.util.Calendar;
import java.util.List;

public class GeradorDePagamento {
    private final RepositorioDePagamentos repositorioDePagamentos;
    private final RepositorioDeLeiloes leiloes;
    private final Avaliador avaliador;

    public GeradorDePagamento(RepositorioDePagamentos repositorioDePagamentos, RepositorioDeLeiloes leiloes, Avaliador avaliador) {
        this.repositorioDePagamentos = repositorioDePagamentos;
        this.leiloes = leiloes;
        this.avaliador = avaliador;
    }

    public void gera(){
        List<Leilao> leiloesEncerrados  = leiloes.encerrados();
        for(Leilao leilao : leiloesEncerrados){
            avaliador.avalia(leilao);

            Pagamento novoPagamento = new Pagamento(avaliador.getMaiorLance(), Calendar.getInstance());
            novoPagamento.salva(novoPagamento);

        }

    }
}
