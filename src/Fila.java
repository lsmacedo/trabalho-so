import java.io.IOException;

public class Fila {

    public static void main(String[] args) {
        try {
            String[] a = DAO.lerPedidos();
            for (String pedido : a) {
                String[] gambi = pedido.split(";");
                String nomeCliente = gambi[0];
                float peso = Float.parseFloat(gambi[1]);
                float precoPorKg = Float.parseFloat(gambi[2]);
                System.out.println("Nome: " + nomeCliente + " | Peso: " + peso + " | Preço por Kg: " + precoPorKg );
            }
        }
        catch (IOException e) {
            System.err.println("Ops:");
            e.printStackTrace();
        }
    }

}
