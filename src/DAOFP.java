import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DAOFP {

    public static PedidoFP[] lerPedidos() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("arquivo.txt"));

        String linha = br.readLine();
        int tam = Integer.parseInt(linha);
        int i = 0;
        String[] pedidos = new String[tam];
        PedidoFP[] retorno = new PedidoFP[tam];

        while ((linha = br.readLine()) != null) {
            pedidos[i++] = linha;
        }

        i = 0;

        for (String pedido : pedidos) {
            String[] gambi = pedido.split(";");
            String nomeCliente = gambi[0];
            float peso = Float.parseFloat(gambi[1]);
            float precoPorKg = Float.parseFloat(gambi[2]);
            PedidoFP p = new PedidoFP(new Cliente(nomeCliente), peso, precoPorKg);
            retorno[i++] = p;
        }

        br.close();
        return retorno;
    }

}
