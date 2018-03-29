import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Fila {

    public static void main(String[] args) {
        try {

            List<Cliente> clientes = new ArrayList<>();
            Pedido[] pedidos = DAO.lerPedidos();
            float acumulador = 0; int horas = 1; int totalHoras=1;

            for (Pedido p : pedidos) {
                if (!clientes.contains(p.getCliente())) {
                    clientes.add(p.getCliente());
                    p.getCliente().addPedido(p);
                } else {
                    int posNaLista = clientes.indexOf(p.getCliente());
                    clientes.get(posNaLista).addPedido(p);
                    p.setCliente(clientes.get(posNaLista));
                }

                System.out.println("Pedido: " + p);
                acumulador += p.getPeso();
                while (acumulador >= 35) {
                    horas++;
                    acumulador-=35;
                }
                p.getCliente().setTempoEspera(horas);
                System.out.println("Tempo para receber o lote: " + horas);
            }

            for(Cliente c:clientes) {
                totalHoras += c.getTempoEspera();
                System.out.println("Tempo de espera do cliente "+ c.getNome()+ ": " + c.getTempoEspera()+ " horas");

            }
            System.out.println("Tempo médio de espera: "+ (float)totalHoras/clientes.size()+" horas");




        }
        catch (IOException e) {
            System.err.println("Ops:");
            e.printStackTrace();
        }
    }

}
