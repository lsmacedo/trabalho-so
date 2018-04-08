package DataStructures;

import DAO.PedidoDAO;
import Objects.Cliente;
import Objects.Pedido;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrdemPedidos {

    public static final int ORDEM_CHEGADA = 0;
    public static final int MENOR_PESO_PRIMEIRO = 1;
    public static final int ATENDIMENTO_RODIZIO = 2;
    public static final int MAIOR_PRECO_KG_PRIMEIRO = 3;

    private List<Cliente> clientes;
    private List<Pedido> pedidos;
    private float acumulador;
    private int horas;
    private int totalHoras;
    private Cliente cliente;
    private BinaryHeap heap;

    public OrdemPedidos(int ordem)throws IOException{
        this.clientes = new ArrayList<>();
        this.acumulador = 0;
        this.horas = 1;
        this.totalHoras = 0;
        this.setHeap(ordem);
        this.setPedidos(ordem);

    }

    public void setPedidos(int ordem) throws IOException{
        switch (ordem){
            case ORDEM_CHEGADA:
                pedidos = PedidoDAO.lerPedidos(ORDEM_CHEGADA);
                break;
            case MENOR_PESO_PRIMEIRO:
                pedidos = PedidoDAO.lerPedidos(MENOR_PESO_PRIMEIRO);
                break;
            case MAIOR_PRECO_KG_PRIMEIRO:
                pedidos = PedidoDAO.lerPedidos(MAIOR_PRECO_KG_PRIMEIRO);
                break;
        }
    }

    public void setHeap(int ordem) {
        switch (ordem){
            case MENOR_PESO_PRIMEIRO:
                heap = new BinaryHeap();
                break;
            case MAIOR_PRECO_KG_PRIMEIRO:
                heap = new BinaryHeap(false);
                break;
        }
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
            cliente = adicionarOuRecuperarCliente(pedido);
            atualizarAcumulador(pedido.getPeso());
            atualizarCliente(horas, cliente, pedido);
        }
        this.mostrarSimulacaoCompleta();
    }

    private void inserirPedidosHeap(){
        for(Pedido pedido : pedidos) {
            heap.add(pedido);
        }
    }

    public void simularPedidosHeap(){
        inserirPedidosHeap();
        pedidos.clear();
        while (!heap.isEmpty()){
            Pedido pedidoAtual = (Pedido) heap.remove();
            pedidos.add(pedidoAtual);
            cliente = adicionarOuRecuperarCliente(pedidoAtual);
            atualizarAcumulador(pedidoAtual.getPeso());
            atualizarCliente(horas, cliente, pedidoAtual);
        }
        this.mostrarSimulacaoCompleta();
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

    private void mostrarSimulacaoCompleta(){
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

    private Cliente adicionarOuRecuperarCliente(Pedido pedido) {
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
