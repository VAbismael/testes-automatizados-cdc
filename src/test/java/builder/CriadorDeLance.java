package builder;

import entity.Lance;
import entity.Usuario;
import helper.MockHelper;

public class CriadorDeLance extends MockHelper {

    private Usuario usuario;
    private Double valor;

    public CriadorDeLance comUsuario(Usuario usuario){
        this.usuario = usuario;
        return this;
    }

    public CriadorDeLance comValor(Double valor){
        this.valor = valor;
        return this;
    }

    public Lance build(){
        Lance lance = new Lance();
        lance.setUsuario(usuario);
        lance.setValor(valor);
        return lance;
    }
}
