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
            for (Pedido pedido : pedidos) {
                cliente = adicionarOuRecuperarCliente(pedido);
                pedido.setPeso((pedido.getPeso()-atualizarAcumulador2(pedido.getPeso())));
                atualizarCliente(horas, cliente, pedido);
                if( pedido.getPeso()==0) {
                    System.out.println(pedidos.size());
                    pedidos.remove(pedido);
                }
            }

        }
           // this.mostrarSimulacaoCompleta();
        }
        }





