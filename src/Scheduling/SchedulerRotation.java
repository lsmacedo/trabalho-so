package Scheduling;

import DAO.PedidoDAO;

import java.util.ArrayList;

import java.io.IOException;

public class SchedulerRotation extends Scheduler {

    public SchedulerRotation() throws IOException {
        super();
        pedidos2 = new ArrayList<>(pedidos.size());
        horas=0;
    }

    @Override
    public void setPedidos() throws IOException {
        pedidos = PedidoDAO.lerPedidos(ORDEM_CHEGADA);
    }

    @Override
    public void simularPedidos(){
     mostrarPedidos();
        while (!pedidos.isEmpty()) {
            for (int i = 0; i < pedidos.size(); i++) {
                cliente = adicionarOuRecuperarCliente(pedidos.get(i));
                pedidos.get(i).setPeso(atualizarAcumulador2(pedidos.get(i).getPeso()));
                atualizarCliente(this.horas, cliente, pedidos.get(i));
                if ((int)pedidos.get(i).getPeso()==0) {
                    pedidos2.add(pedidos.get(i));
                    pedidos.remove(pedidos.get(i));
                }
           }
        }
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





