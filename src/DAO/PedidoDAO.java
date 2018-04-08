package DAO;

import Objects.Pedido;
import Objects.PedidoFP;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PedidoDAO {

    private static String[] lerRegistro() throws IOException {
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

    public static Pedido[] lerPedidos() throws IOException {
        String[] pedidos = lerRegistro();
        Pedido[] retorno = new Pedido[pedidos.length];
        for (int i = 0; i < pedidos.length; i++) {
            String[] gambi = pedidos[i].split(";");
            String nomeCliente = gambi[0];
            float peso = Float.parseFloat(gambi[1]);
            float precoPorKg = Float.parseFloat(gambi[2]);
            Pedido p = new Pedido(nomeCliente, peso, precoPorKg);
            retorno[i] = p;
        }
        return retorno;
    }

    public static PedidoFP[] lerPedidosFP() throws IOException {
        String[] pedidos = lerRegistro();
        PedidoFP[] retorno = new PedidoFP[pedidos.length];
        for (int i = 0; i < pedidos.length; i++) {
            String[] gambi = pedidos[i].split(";");
            String nomeCliente = gambi[0];
            float peso = Float.parseFloat(gambi[1]);
            float precoPorKg = Float.parseFloat(gambi[2]);
            PedidoFP p = new PedidoFP(nomeCliente, peso, precoPorKg);
            retorno[i] = p;
        }
        return retorno;
    }

}
