package entity;

import java.util.Objects;

public class Lance {

    private Usuario usuario;
    private Double valor;

    public Lance() {
        super();
    }

    public Lance(Usuario usuario, Double valor) {
        this();
        this.usuario = usuario;
        this.valor = valor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Lance [" +
                "    usuario=" + usuario +
                ",     valor=" + valor +
                "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lance lance = (Lance) o;
        return Objects.equals(usuario, lance.usuario) && Objects.equals(valor, lance.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, valor);
    }
}
