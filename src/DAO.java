import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DAO {

    public static void main(String[] args) {
        try {
            String[] a = lerPedidos();
            for (String pedido : a) {
                String[] gambi = pedido.split(";");
                String nomeCliente = gambi[0];
                float peso = Float.parseFloat(gambi[1]);
                float precoPorKg = Float.parseFloat(gambi[2]);
                System.out.println("Nome: " + nomeCliente + " | Peso: " + peso + " | Preço por Kg: " + precoPorKg );
            }
        }
        catch (IOException e) {
            System.err.println("Ops");
        }
}

    public static String[] lerPedidos() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("arquivo.txt"));

        String linha = br.readLine();
        int tam = Integer.parseInt(linha);
        int i = 0;
        String[] pedidos = new String[tam];

        while ((linha = br.readLine()) != null) {
            pedidos[i++] = linha;
        }

        br.close();
        return pedidos;
    }

}
