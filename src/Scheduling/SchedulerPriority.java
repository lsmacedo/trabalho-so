package Scheduling;

import DAO.PedidoDAO;
import DataStructures.BinaryHeap;
import Objects.Pedido;

import java.io.IOException;

public class SchedulerPriority extends Scheduler {

    private BinaryHeap heap;

    public SchedulerPriority() throws IOException {
        super();
        heap = new BinaryHeap();
    }

    private void inserirPedidos(){
        for(Pedido pedido : pedidos) {
            heap.add(pedido);
        }
    }

    @Override
    public void setPedidos() throws IOException {
        pedidos = PedidoDAO.lerPedidos(MAIOR_PRECO_KG_PRIMEIRO);
    }

    @Override
    public void simularPedidos(){
        inserirPedidos();
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
}
