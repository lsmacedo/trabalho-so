public class PedidoFP extends Pedido {
    //Extende Pedido porém sobrescreve método compareTo comparando o preço por kg

    public PedidoFP(Cliente cliente, float peso, float precoDoKg) {
        super(cliente, peso, precoDoKg);
    }

    @Override
    public int compareTo(Object o) {
        return (int) (this.getPrecoDoKg() - ((Pedido) o).getPrecoDoKg());
    }

}
