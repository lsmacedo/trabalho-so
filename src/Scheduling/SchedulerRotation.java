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
        System.out.println(pedidos.size());
     mostrarPedidos();
        while (!pedidos.isEmpty()) {
            for (int i = 0; i < pedidos.size(); i++) {
                System.out.println(pedidos2.size()+"macarrao");
                System.out.println(pedidos.size());
               // cliente = adicionarOuRecuperarCliente(pedidos.get(i));
                pedidos.get(i).setPeso(atualizarAcumulador2(pedidos.get(i).getPeso()));
                //atualizarCliente(horas, cliente, pedidos.get(i));
                System.out.println(pedidos.get(i).getPeso());
                if ((int)pedidos.get(i).getPeso()==0) {
                    pedidos2.add(pedidos.get(i));
                    pedidos.remove(pedidos.get(i));
                }
           }
            System.out.println(pedidos2.size()+"batata");
        }
        System.out.println(pedidos2.size());
        this.mostrarSimulacaoCompleta2();
    }
}





