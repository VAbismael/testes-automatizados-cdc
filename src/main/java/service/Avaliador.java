package service;

import entity.Lance;
import entity.Leilao;

import java.util.*;

public class Avaliador {

    private double maiorDeTodos = Double.NEGATIVE_INFINITY;
    private double menorDeTodos = Double.POSITIVE_INFINITY;
    private List<Lance> maiores;

    public void avalia(Leilao leilao){
        if(leilao.getLances().isEmpty()){
            throw new RuntimeException("Não é possível avaliar um leilão sem lances");
        }
        for (Lance lance : leilao.getLances()){
            if (lance.getValor() > maiorDeTodos){
                maiorDeTodos = lance.getValor();
            }
            if(lance.getValor() < menorDeTodos){
                menorDeTodos = lance.getValor();
            }
        }

        pegaOsMaioresNo(leilao);
    }

    private void pegaOsMaioresNo(Leilao leilao) {
        maiores = new ArrayList<>(leilao.getLances());
        maiores.sort((o1, o2) -> {
            return o2.getValor().compareTo(o1.getValor());
        });

        maiores = maiores.subList(0, Math.min(maiores.size(), 3));
    }
    public List<Lance> getTresMaiores(){
        return this.maiores;
    }
    public double getMaiorLance(){
        return maiorDeTodos;
    }
    public double getMenorLance(){
        return menorDeTodos;
    }
}
