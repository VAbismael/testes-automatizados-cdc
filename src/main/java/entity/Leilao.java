package entity;

import java.util.*;

public class Leilao {

    private String Nome;
    private List<Lance> lances;
    private Calendar data;

    public Leilao() {
        super();
    }

    public Leilao(String nome) {
        this();
        this.Nome = nome;
        this.lances = new ArrayList<>();
        
    }

    public Leilao(String nome, Calendar data) {
        this();
        this.Nome = nome;
        this.data = data;
        this.lances = new ArrayList<>();
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public List<Lance> getLances() {
        return Collections.unmodifiableList(lances);
    }

    public void setLances(List<Lance> lances) {
        this.lances = lances;
    }

    public void propoe(Lance lance){

        if (lances.isEmpty() || podeDarLance(lance)
            ){
            lances.add(lance);
        }
    }

    private boolean podeDarLance(Lance lance) {
        return !ultimoLanceDado()
                .getUsuario()
                .equals(lance.getUsuario())
                && qtdDeLancesDo(lance) < 5;
    }

    private int qtdDeLancesDo(Lance lance) {
        int total = 0;
        for (Lance l : lances){
            if(l.getUsuario().equals(lance.getUsuario())){
                total++;
            }
        }
        return total;
    }

    private Lance ultimoLanceDado() {
        return lances.get(lances.size() - 1);
    }

    @Override
    public String toString() {
        return "Leilao [" +
                "    Nome=" + Nome +
                ",     lances=" + lances +
                "]";
    }

    public void encerra() {
    }

    public boolean isEncerrado() {
        return true;
    }
}
