package script;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public final class Individuo implements Comparable<Individuo>{

    // Vetor de arestas c, denomina as caracteristicas do individuo
    private Aresta c[] = new Aresta[Principal.VERTICES];
    private int peso; // Somatório do peso do conjunto aresta w[A]
    private final int aptidao; // Quantidade de adaptação ao ambiente, Quanto > melhor
    private static ArrayList<Aresta> arestas;
    
    // Construtor gera um individuo aleatório
    public Individuo(){
        if(arestas == null) novaLista();
        // Random para pegar arestas aleatórias da lista
        Random rand = new Random();
        for(int i=0; i<Principal.VERTICES; i++){
            if(arestas.isEmpty()) novaLista();
            int index = rand.nextInt(arestas.size());
            c[i] = arestas.remove(index);
        }
        calculaPeso(); // Calcula somatório de totas as aresta, Peso total
        aptidao = Aptidao.aptidao(this); // Calcula aptidão do individuo que esta sendo gerado
    }
    
    // Construtor gera um individuo com caracteristicas passadas por parametro
    public Individuo(Aresta c[]){
        this.c = c;
        calculaPeso();
        aptidao = Aptidao.aptidao(this);
    }
    
    // Lista com as arestas possiveis do grafo
    public static void novaLista(){
        arestas = new ArrayList<>(Principal.arestasPossiveis);
        Collections.sort(arestas);
    }
    
    // Clonar caracteristicas do individuo
    public Aresta[] clonar(){
        Aresta[] c2 = new Aresta[Principal.VERTICES];
        for(int i=0; i<Principal.VERTICES; i++)
            c2[i] = c[i];
        return c2;
    }

    // Calcula o peso total, somatório do peso de totas as arestas
    public int calculaPeso(){
        peso = 0;
        for(Aresta aresta : c)
            peso += aresta.getPeso();
        return peso;
    }
    
    
    // GETTERS E SETTERS
    //==============================================
    public Aresta[] getC() {
        return c;
    }
    
    public Aresta getC(int x) {
        return c[x];
    }

    public int getPeso() {
        return peso;
    }

    public int getAptidao() {
        return aptidao;
    }

    @Override
    public String toString() {
        String s = "";
        for(Aresta aresta : c)
            s += aresta.toString();
        s += "\nPeso total: "+peso;
        s += "\nAptidão: "+aptidao;
        s += "\nCaminho: "+Caminho.verifica(this);
        s += "\nTodos os vertices: "+Caminho.todosVertices(c);
        return s;
    }

    @Override
    public int compareTo(Individuo i) {
        if(aptidao > i.aptidao)
            return 1;
        else if(aptidao < i.aptidao)
            return -1;
        else
            return 0;
    }
    
}
