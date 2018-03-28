import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DAO {

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
