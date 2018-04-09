package Main;

import Scheduling.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try {

            Scanner in = new Scanner(System.in);
            int resp = -1;

            while (true) {

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

                Scheduler ordem = null;
                boolean inicializouScheduler = true;

                switch (resp) {

                    case 1:
                        ordem = new SchedulerFCFS();
                        break;
                    case 2:
                        ordem = new SchedulerSJN();
                        break;
                    case 3:
                        ordem = new SchedulerRotation();
                        break;
                    case 4:
                        ordem = new SchedulerPriority();
                        break;
                    case 9:
                        return;
                    default:
                        inicializouScheduler = false;
                }

                if (inicializouScheduler)
                    ordem.simularPedidos();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
