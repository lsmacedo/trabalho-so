package Main;

import DataStructures.OrdemChegada;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try {

            Scanner in = new Scanner(System.in);
            int resp = -1;

            while (resp != 9) {

                System.out.println("\n----------ORDER SIMULATOR 9000----------\n");

                System.out.println("SELECIONE A ORDEM DOS PEDIDOS");
                System.out.println("1 - Ordem de chegada");
                System.out.println("2 - Menores pedidos primeiro");
                System.out.println("3 - Atendimento em rodízio");
                System.out.println("4 - Maior pagamento por kg primeiro");
                System.out.println("-------------------------------------------");
                System.out.println("9 - Sair do menu");

                System.out.print("\n >>  ");
                resp = in.nextInt();

                switch (resp) {

                    case 1:
                        OrdemChegada ordem = new OrdemChegada();
                        ordem.simularPedidos();
                        ordem.mostrarSimulacaoCompleta();
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 9:
                        return;

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
