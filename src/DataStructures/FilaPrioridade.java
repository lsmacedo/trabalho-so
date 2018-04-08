package DataStructures;

import DAO.PedidoDAO;
import Objects.Cliente;
import Objects.PedidoFP;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FilaPrioridade {

    public static void main(String[] args) {
        try {
            List<Cliente> clientes = new ArrayList<>();  // Lista de clientes
            PedidoFP[] pedidos = PedidoDAO.lerPedidosFP();     // Lista de pedidos
            HeapMax fp = new HeapMax();    // DataStructures.OrdemChegada de prioridades para o escalonador
            float acumulador = 0;                        // Recebe a quantidade de roupas lavada
            int horas = 1;                               // Quantidade de horas passadas
            PedidoFP pedido;                             // Objects.Pedido atual
            Cliente cliente;                             // Objects.Cliente atual

            // Percorrendo lista de pedidos e adicionando na fila de prioridade
            for (PedidoFP p : pedidos) fp.insert(p);

            // Percorre pedidos da fila de prioridade
            while (!fp.isEmpty()) {
                pedido = (PedidoFP) fp.removeMax();

                if (clientes.contains(new Cliente(pedido.getCliente()))) {
                    int posNaLista = clientes.indexOf(new Cliente(pedido.getCliente()));
                    cliente = clientes.get(posNaLista);
                } else {
                    cliente = new Cliente(pedido.getCliente());
                    clientes.add(cliente);
                }

                System.out.println("Objects.Pedido: " + pedido);

                acumulador += pedido.getPeso();
                while (acumulador >= 35) {
                    horas++;
                    acumulador -= 35;
                }

                cliente.setTempoEspera(horas);
                pedido.setTempoDeTermino(horas);
                cliente.addPedido(pedido);
            }

            System.out.println();

            for (Cliente c : clientes) {

                int tempoDecorrido;
                tempoDecorrido = c.getPedidos().get(0).getTempoDeTermino();
                System.out.println("Tempo para receber o primeiro lote do cliente " + c.getNome() + ": " + tempoDecorrido);

            }

            System.out.println();

            for (Cliente c : clientes) {
                horas += c.getTempoEspera();
                System.out.println("Tempo de espera do cliente " + c.getNome() + ": " + c.getTempoEspera() + " horas");

            }
            System.out.println("\nTempo médio de espera: " + (float) horas / clientes.size() + " horas");

        } catch (IOException e) {

        }
    }

}

class HeapMax<E extends Comparable<E>> {
    private Object S[];
    private int last;
    private int capacity;

    public HeapMax() {
        S = new Object[11];
        last = 0;
        capacity = 7;
    }

    public int size() {
        return last;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public E max() throws HeapException {
        if (isEmpty())
            throw new HeapException("The heap is empty.");
        else
            return (E) S[1];
    }

    private int compare(Object x, Object y) {
        return ((E) x).compareTo((E) y);
    }

    public void insert(E e) throws HeapException {
        if (size() == capacity)
            throw new HeapException("Heap overflow.");
        else {
            last++;
            S[last] = e;
            upHeapBubble();
        }
    }

    public E removeMax() throws HeapException {
        if (isEmpty())
            throw new HeapException("Heap is empty.");
        else {
            E max = max();
            S[1] = S[last];
            last--;
            downHeapBubble();
            return max;
        }
    }

    private void downHeapBubble() {
        int index = 1;
        while (true) {
            int child = index * 2;
            if (child > size())
                break;
            if (child + 1 >= size()) {
                //if there are two children -> take the smalles or
                //if they are equal take the left one
                child = findMax(child, child + 1);
            }
            if (compare(S[index], S[child]) >= 0)
                break;
            swap(index, child);
            index = child;
        }
    }

    private void upHeapBubble() {
        int index = size();
        while (index > 1) {
            int parent = index / 2;
            if (compare(S[index], S[parent]) <= 0)
                //break if the parent is greater or equal to the current element
                break;
            swap(index, parent);
            index = parent;
        }
    }

    private void swap(int i, int j) {
        Object temp = S[i];
        S[i] = S[j];
        S[j] = temp;
    }

    private int findMax(int leftChild, int rightChild) {
        if (compare(S[leftChild], S[rightChild]) <= 0)
            return leftChild;
        else
            return rightChild;
    }

}

class HeapException extends RuntimeException {
    public HeapException() {
    }

    ;

    public HeapException(String msg) {
        super(msg);
    }
}