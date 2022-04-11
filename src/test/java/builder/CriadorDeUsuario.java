package builder;

import entity.Usuario;

public class CriadorDeUsuario {

    private String nome;

    public CriadorDeUsuario comNome(String nome){
        this.nome = nome;
        return this;
    }

    public Usuario build(){
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        return usuario;
    }
}
