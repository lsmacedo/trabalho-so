package Objects;

public class PedidoFP extends Pedido {
    //Extende Objects.Pedido porém sobrescreve método compareTo comparando o preço por kg

    public PedidoFP(String cliente, float peso, float precoDoKg) {
        super(cliente, peso, precoDoKg);
    }

    @Override
    public int compareTo(Object o) {
        return (int) (this.getPrecoDoKg() - ((Pedido) o).getPrecoDoKg());
    }

}
