import java.util.ArrayList;
import java.util.List;

public class Cliente {

    private String nome;
    private List<Pedido> pedidos;

    public Cliente(String nome) {
        this.nome = nome;
        this.pedidos = new ArrayList<>();
    }

    public void addPedido(Pedido p) {
        pedidos.add(p);
    }

    public String getNome() {
        return nome;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Cliente && this.nome.equals(o);
    }

}
