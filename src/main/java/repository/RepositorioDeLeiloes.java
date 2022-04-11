package repository;

import entity.Leilao;

import java.util.List;

public interface RepositorioDeLeiloes {

    List<Leilao> correntes();
    void atualiza(Leilao leilao);

    List<Leilao> encerrados();
}
