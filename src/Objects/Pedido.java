package Objects;

import Scheduling.Scheduler;

public class Pedido implements Comparable{

    private int ordemPedido;
    private String cliente;
    private float peso;
    private float precoDoKg;
    private int tempoDeTermino;

    public Pedido(int ordemPedido, String cliente, float peso, float precoDoKg) {
        this.ordemPedido = ordemPedido;
        this.cliente = cliente;
        this.peso = peso;
        this.precoDoKg = precoDoKg;
    }

    public String getCliente() {
        return cliente;
    }

    public int getTempoDeTermino() {
        return tempoDeTermino;
    }

    public void setTempoDeTermino(int tempoDeTermino) {
        this.tempoDeTermino = tempoDeTermino;
    }

    public void setCliente(String cliente) {
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
        return "Nome: " + this.cliente + " | Peso: " + this.peso + " kg | Preço por kg: R$ " + this.precoDoKg;
    }

    @Override
    public int compareTo(Object o) {
        switch (this.ordemPedido) {
            case Scheduler.ORDEM_CHEGADA:
            case Scheduler.MENOR_PESO_PRIMEIRO:
                return (int) (this.getPeso() - ((Pedido) o).getPeso());
            case Scheduler.MAIOR_PRECO_KG_PRIMEIRO:
                return (int) (((Pedido) o).getPrecoDoKg() - this.getPrecoDoKg());
        }
        return 0;
    }

}
