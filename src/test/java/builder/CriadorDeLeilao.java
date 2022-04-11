package builder;

import entity.Lance;
import entity.Leilao;
import entity.Usuario;

import java.util.Calendar;

public class CriadorDeLeilao {

    private Leilao leilao;
    private Calendar data;

    public CriadorDeLeilao para(String nome){
        this.leilao = new Leilao(nome);
        return this;
    }

    public CriadorDeLeilao naData(Calendar inicio){
        this.data = inicio;
        return this;
    }

    public CriadorDeLeilao lance(Usuario usuario, double valor){
        leilao.propoe(new Lance(usuario, valor));
        return this;
    }
    public Leilao build(){
        leilao.setData(data);
        return leilao;
    }


}
