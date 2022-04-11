package dao;

import entity.Leilao;
import repository.RepositorioDeLeiloes;

import java.util.List;

public class LeilaoDao implements RepositorioDeLeiloes {

    @Override
    public List<Leilao> correntes() {
        return null;
    }

    @Override
    public void atualiza(Leilao leilao) {

    }

    @Override
    public List<Leilao> encerrados() {
        return null;
    }
}
