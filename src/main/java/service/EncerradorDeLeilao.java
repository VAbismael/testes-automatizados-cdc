package service;

import entity.Leilao;
import repository.RepositorioCarteiro;
import repository.RepositorioDeLeiloes;

import java.util.Calendar;
import java.util.List;

public class EncerradorDeLeilao {

    private int total = 0;
    private final RepositorioDeLeiloes dao;
    private final RepositorioCarteiro repositorioCarteiro;


    public EncerradorDeLeilao(RepositorioDeLeiloes dao, RepositorioCarteiro repositorioCarteiro) {
        this.dao = dao;
        this.repositorioCarteiro = repositorioCarteiro;
    }

    public void encerra(){

        List<Leilao> todosLeiloesCorrentes = dao.correntes();

        for (Leilao leilao : todosLeiloesCorrentes){
            try{
                if(comecouSemanaPassada(leilao)){
                    leilao.encerra();
                    total++;
                    dao.atualiza(leilao);
                    repositorioCarteiro.envia(leilao);
                }
            }catch (Exception ignored){

            }

        }
    }

    private boolean comecouSemanaPassada(Leilao leilao){
        return diasEntre(leilao.getData(), Calendar.getInstance()) >= 7;
    }

    private int diasEntre(Calendar inicio, Calendar fim) {
        Calendar data = (Calendar) inicio.clone();
        int diasNoIntervalo = 0;
        while (data.before(fim)){
            data.add(Calendar.DAY_OF_MONTH, 1);
            diasNoIntervalo++;
        }
        return diasNoIntervalo;
    }

    public  int getTotalEncerrados(){
        return total;
    }
}
