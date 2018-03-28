import java.io.IOException;

public class Fila {

    public static void main(String[] args) {
        try {
            Pedido[] pedidos = DAO.lerPedidos();
            for (Pedido p : pedidos)
                System.out.println(p);
        }
        catch (IOException e) {
            System.err.println("Ops:");
            e.printStackTrace();
        }
    }

}
