public class Pedido {

    private Cliente cliente;
    private float peso;
    private float precoDoKg;

    public Pedido(Cliente cliente, float peso, float precoDoKg) {
        this.cliente = cliente;
        this.peso = peso;
        this.precoDoKg = precoDoKg;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getPrecoDoKg() {
        return precoDoKg;
    }

    public void setPrecoDoKg(float precoDoKg) {
        this.precoDoKg = precoDoKg;
    }
}
