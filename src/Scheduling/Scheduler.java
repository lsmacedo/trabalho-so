package Scheduling;

import Objects.Cliente;
import Objects.Pedido;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Scheduler {

    public static final int ORDEM_CHEGADA = 0;
    public static final int MENOR_PESO_PRIMEIRO = 1;

    public static final int MAIOR_PRECO_KG_PRIMEIRO = 3;

    protected List<Cliente> clientes;
    protected List<Pedido> pedidos;
    protected List<Pedido> pedidos2;
    protected float acumulador;
    protected int horas;
    protected int totalHoras;
    protected Cliente cliente;


    public Scheduler() throws IOException{
        this.clientes = new ArrayList<>();
        this.acumulador = 0;
        this.horas = 1;
        this.totalHoras = 0;
        this.setPedidos();
    }

    public abstract void setPedidos() throws IOException;
    public abstract void simularPedidos();

    protected void atualizarAcumulador(float peso){
        acumulador += peso;
        while (acumulador >= 35) {
            horas++;
            acumulador-=35;
        }
    }

    protected float atualizarAcumulador2(float peso) { // André
        acumulador = peso;
        horas++;
        if (acumulador > 35) {
            while (acumulador > 35) {
                acumulador -= 1;
            }
        } return acumulador;
    }


    private void mostrarPedidos(){
        for (Pedido p : pedidos){
            System.out.println("Pedido: " + p);
        }
        System.out.println();
    }

    private void mostrarTempoPrimeiroLote(){
        for (Cliente c : clientes){
            int tempoDecorrido;
            tempoDecorrido = c.getPedidos().get(0).getTempoDeTermino();
            System.out.println("Tempo para receber o primeiro lote do cliente " + c.getNome() + ": " + tempoDecorrido+ " horas");
        }
        System.out.println();
    }

    private void mostrarTempoTotal(){
        for(Cliente c:clientes) {
            totalHoras += c.getTempoEspera();
            System.out.println("Tempo de espera do cliente "+ c.getNome()+ ": " + c.getTempoEspera()+ " horas");
        }
        System.out.println();
    }

    private void mostrarTempoMedio(){
        System.out.println("\nTempo médio de espera: "+ (float)totalHoras/clientes.size()+" horas");
    }

    protected void mostrarSimulacaoCompleta(){
        this.mostrarPedidos();
        this.mostrarTempoPrimeiroLote();
        this.mostrarTempoTotal();
        this.mostrarTempoMedio();
    }

    protected void atualizarCliente(int horas, Cliente cliente, Pedido pedido) {
        pedido.setTempoDeTermino(horas);
        cliente.setTempoEspera(horas);
        cliente.addPedido(pedido);
    }

    protected Cliente adicionarOuRecuperarCliente(Pedido pedido) {
        Cliente cliente;
        if (clientes.contains(new Cliente(pedido.getCliente()))) {
            int posNaLista = clientes.indexOf(new Cliente(pedido.getCliente()));
            cliente = clientes.get(posNaLista);
        }
        else {
            cliente = new Cliente(pedido.getCliente());
            clientes.add(cliente);
        }
        return cliente;
    }

}
