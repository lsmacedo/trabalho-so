package Scheduling;

import DAO.PedidoDAO;
import Objects.Pedido;

import java.util.ArrayList;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class SchedulerRotation extends Scheduler {

    protected List<Pedido> pedidos2;
    protected List<Pedido> pedidos3;

    public SchedulerRotation() throws IOException {
        super();
        horas=0;
    }

    @Override
    public void setPedidos() throws IOException {
        pedidos = new ArrayList<>();
        pedidos2 = PedidoDAO.lerPedidos(ORDEM_CHEGADA);
        pedidos3 = new ArrayList<>();
        for (Pedido p : pedidos2) pedidos3.add(p.clone());
    }

    @Override
    public void simularPedidos() {
        while (!pedidos2.isEmpty()) {
            for (int i = 0; i < pedidos2.size(); i++) {
                cliente = adicionarOuRecuperarCliente(pedidos2.get(i));
                pedidos2.get(i).setPeso(atualizarAcumulador2(pedidos2.get(i).getPeso()));
                Pedido temp;
                if (pedidos.contains(pedidos2.get(i)))
                    temp = null;
                else
                    temp = pedidos2.get(i);
                atualizarCliente(this.horas, cliente, temp);
                if ((int) pedidos2.get(i).getPeso() == 0) {
                    pedidos.add(pedidos2.get(i));
                    Collections.sort(cliente.getPedidos());
                    pedidos2.remove(pedidos2.get(i));
                    i--;
                }
            }
        }
        this.pedidos = this.pedidos3;
        this.mostrarSimulacaoCompleta();
    }

    private float atualizarAcumulador2(float peso) {
        acumulador = peso;
        this.horas++;
        if (acumulador > 35) {
            acumulador -= 35;
        }
        else acumulador = 0;
        return acumulador;
    }
}