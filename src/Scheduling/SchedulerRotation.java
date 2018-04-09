package Scheduling;

import DAO.PedidoDAO;
import java.text.DecimalFormat;
import Objects.Pedido;
import java.util.ArrayList;

import java.io.IOException;
import java.util.List;

public class SchedulerRotation extends Scheduler {
    protected List<Pedido> pedidos2=pedidos;

    public SchedulerRotation() throws IOException {
        super();
    }

    @Override
    public void setPedidos() throws IOException {
        pedidos = PedidoDAO.lerPedidos(ORDEM_CHEGADA);
    }

    @Override
    public void simularPedidos(){
        while (pedidos.size()>0) {
            for (int i = 0; i < pedidos.size(); i++) {
                cliente = adicionarOuRecuperarCliente(pedidos.get(i));
                pedidos.get(i).setPeso(atualizarAcumulador2(pedidos.get(i).getPeso()));
                atualizarCliente(horas, cliente, pedidos.get(i));
                if( pedidos.get(i).getPeso()==0) {
                    pedidos.remove(pedidos.get(i));
                }
            }

        }
        this.mostrarSimulacaoCompleta();
    }
}





