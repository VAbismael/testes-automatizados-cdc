package helper;

import builder.CriadorDeLance;
import builder.CriadorDeLeilao;
import builder.CriadorDeUsuario;
import entity.Lance;
import entity.Leilao;
import entity.Usuario;

public class MockHelper {

    public Usuario getUsuario(){
        return new CriadorDeUsuario()
                .comNome("Usuario")
                .build();
    }

    public Leilao getLeilao(){
        return new CriadorDeLeilao()
                .para("Leilão")
                .build();
    }

    public Lance getLance(){
        return new CriadorDeLance()
                .comUsuario(getUsuario())
                .comValor(1000.0)
                .build();
    }
}
