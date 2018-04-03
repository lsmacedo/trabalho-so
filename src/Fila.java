import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Fila {

    public static void main(String[] args) {
        try {

            List<Cliente> clientes = new ArrayList<>();
            Pedido[] pedidos = DAO.lerPedidos();
            float acumulador = 0; int horas = 1; int totalHoras=0;
            Cliente cliente;

            for (Pedido pedido : pedidos) {
                if (clientes.contains(new Cliente(pedido.getCliente()))) {
                    int posNaLista = clientes.indexOf(new Cliente(pedido.getCliente()));
                    cliente = clientes.get(posNaLista);
                }
                else {
                    cliente = new Cliente(pedido.getCliente());
                    clientes.add(cliente);
                }
                System.out.println("Pedido: " + pedido);

                acumulador += pedido.getPeso();
                while (acumulador >= 35) {
                    horas++;
                    acumulador-=35;
                }

                pedido.setTempoDeTermino(horas);
                cliente.setTempoEspera(horas);
                cliente.addPedido(pedido);
            }

            System.out.println();

            for (Cliente c : clientes){

                int tempoDecorrido;
                tempoDecorrido = c.getPedidos().get(0).getTempoDeTermino();
                System.out.println("Tempo para receber o primeiro lote do cliente " + c.getNome() + ": " + tempoDecorrido);

            }

            System.out.println();

            for(Cliente c:clientes) {
                totalHoras += c.getTempoEspera();
                System.out.println("Tempo de espera do cliente "+ c.getNome()+ ": " + c.getTempoEspera()+ " horas");

            }
            System.out.println("\nTempo médio de espera: "+ (float)totalHoras/clientes.size()+" horas");

        }
        catch (IOException e) {
            System.err.println("Ops:");
            e.printStackTrace();
        }
    }

}
