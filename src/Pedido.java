public class Pedido {

    private Cliente cliente;
    private float peso;
    private float precoDoKg;
    private int tempoDeTermino;

    public Pedido(Cliente cliente, float peso, float precoDoKg) {
        this.cliente = cliente;
        this.peso = peso;
        this.precoDoKg = precoDoKg;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public int getTempoDeTermino() {
        return tempoDeTermino;
    }

    public void setTempoDeTermino(int tempoDeTermino) {
        this.tempoDeTermino = tempoDeTermino;
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


    @Override
    public String toString() {
        return "Nome: " + this.cliente.getNome() + " | Peso: " + this.peso + " kg | Preço por kg: R$ " + this.precoDoKg;
    }

}
