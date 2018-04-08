package Scheduling;

import DAO.PedidoDAO;
import Objects.Pedido;

import java.io.IOException;

public class SchedulerFCFS extends Scheduler {

    public SchedulerFCFS() throws IOException {
        super();
    }

    @Override
    public void setPedidos() throws IOException {
        pedidos = PedidoDAO.lerPedidos(ORDEM_CHEGADA);
    }

    @Override
    public void simularPedidos(){
        for (Pedido pedido : pedidos) {
            cliente = adicionarOuRecuperarCliente(pedido);
            atualizarAcumulador(pedido.getPeso());
            atualizarCliente(horas, cliente, pedido);
        }
        this.mostrarSimulacaoCompleta();
    }

}
