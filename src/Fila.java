import java.io.IOException;

public class Fila {

    public static void main(String[] args) {
        try {


            Pedido[] pedidos = DAO.lerPedidos();
            float acumulador = 0;
            int horas = 1;
            for (Pedido p : pedidos) {
                System.out.println("Pedido: " + p);
                acumulador += p.getPeso();
                if (acumulador >= 35) {
                    horas++;
                    acumulador-=35;
                }
                System.out.println("Tempo para receber o lote: " + horas);
            }


        }
        catch (IOException e) {
            System.err.println("Ops:");
            e.printStackTrace();
        }
    }

}
