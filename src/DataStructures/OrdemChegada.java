package DataStructures;

import DAO.PedidoDAO;
import Objects.Cliente;
import Objects.Pedido;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrdemChegada {

    private List<Cliente> clientes;
    private Pedido[] pedidos;
    private float acumulador = 0;
    private int horas = 1;
    private int totalHoras=0;
    private Cliente cliente;

    public OrdemChegada()throws IOException{
        this.clientes = new ArrayList<>();
        this.pedidos = PedidoDAO.lerPedidos();
        this.acumulador = 0;
        this.horas = 1;
        this.totalHoras = 0;
    }

    private void atualizarAcumulador(float peso){
        acumulador += peso;
        while (acumulador >= 35) {
            horas++;
            acumulador-=35;
        }
    }

    private void mostrarPedidos(){
        for (Pedido p : pedidos){
            System.out.println("Pedido: " + p);
        }
        System.out.println();
    }

    public void simularPedidos(){
        for (Pedido pedido : pedidos) {
            cliente = adicionarOuRecuperarCliente(clientes, pedido);
            atualizarAcumulador(pedido.getPeso());
            atualizarCliente(horas, cliente, pedido);
        }
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

    public void mostrarSimulacaoCompleta(){
        this.mostrarPedidos();
        this.mostrarTempoPrimeiroLote();
        this.mostrarTempoTotal();
        this.mostrarTempoMedio();
    }

    private void atualizarCliente(int horas, Cliente cliente, Pedido pedido) {
        pedido.setTempoDeTermino(horas);
        cliente.setTempoEspera(horas);
        cliente.addPedido(pedido);
    }

    private Cliente adicionarOuRecuperarCliente(List<Cliente> clientes, Pedido pedido) {
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
