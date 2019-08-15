package script;

import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Quantidade de vertices no grafo: ");
        int vertices = in.nextInt();
        Grafo grafo = new Grafo(vertices);
        System.out.println(grafo);
        AGM arvore = new AGM(grafo);
        long tempoInicial = System.currentTimeMillis();
        arvore.processa();
        long tempoFinal = System.currentTimeMillis() - tempoInicial;
        System.out.println("\n"+arvore);
        System.out.println("Tempo de execução: "+tempoFinal+" ms");
    }
    
}
