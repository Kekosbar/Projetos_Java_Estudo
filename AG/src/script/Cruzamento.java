package script;

import java.util.ArrayList;
import java.util.Random;

public class Cruzamento {

    public static Individuo[] cruza(Individuo ind, Individuo ind2){
        Aresta[] c = ind.clonar();// Retorna todas as caracteristicas de ind
        Aresta[] c2 = ind2.clonar();
        
        Random rand = new Random();
        // Sorteia duas caracteristicas aleatoria para entrar em uma pos aleatoria
        
        int index = rand.nextInt(Principal.VERTICES);
        int index2 = rand.nextInt(Principal.VERTICES);
        c[index] = c2[index2];
        c[index] = c2[index2];
        // Mesmo procedmento anterior so que para o c2
        index = rand.nextInt(Principal.VERTICES);
        index2 = rand.nextInt(Principal.VERTICES);
        c2[index] = c[index2];
        c2[index] = c[index2];
        // Mutação para tentar gerar filhos com caminhos válidos
        mutacao(c);
        mutacao(c2);
        
        // Gera os filhos
        Individuo filho = new Individuo(c);
        Individuo filho2 = new Individuo(c2);
        Individuo[] filhos = {filho, filho2};
        return filhos;
    }
    
    // Função responsável por fazer uma mutação no vetor do individuo gerado
    // Tenta mutar os individuos para que tenha um caminho valido
    // Sua precisão é de 70%
    private static boolean mutacao(Aresta[] arestas){
        Aresta[] visitada = new Aresta[arestas.length];
        int[] Vet_visitados = new int[Principal.VERTICES];
        int inicial = arestas[0].getV();
        int busca = arestas[0].getU();// vertice busca sera buscado como proximo destino
        int encontrou = 0;
        // Visitou a aresta
        addVisitaAresta(visitada, arestas[0]);
        // Adiciona uma visita, visitou o vertice inicial
        addVisitaVertice(Vet_visitados, inicial);
        addVisitaVertice(Vet_visitados, busca);
        // Percorrera o caminho do individuo, o concertando se possivel
        while(encontrou < Principal.VERTICES){
            boolean encontrado = false;
            for(int j=1; j<Principal.VERTICES; j++){
                int v = arestas[j].getV();
                int u = arestas[j].getU();
                // Se a aresta ja foi visitada
                if(!verArestaVisitada(visitada, arestas[j]))
                    continue;
                // Se encontrar o vertice de busca
                if(busca == v && !verVetJaVisitado(Vet_visitados, v)){
                    encontrado = true;
                    encontrou++;
                    // adiciona visita
                    addVisitaAresta(visitada, arestas[j]);
                    addVisitaVertice(Vet_visitados, v);
                    busca = u;
                    break;
                }else 
                if(busca == u && !verVetJaVisitado(Vet_visitados, u)){
                    encontrado = true;
                    encontrou++;
                    addVisitaAresta(visitada, arestas[j]);
                    addVisitaVertice(Vet_visitados, u);
                    busca = v;
                    break;
                }
            }
            // Se o vertice buscado não foi encontrado o caminho esta invalido
            if(!encontrado){
                encontrou++;
                // Encontra uma aresta qualquer para reconectar o caminho
                int x = concerta(busca, arestas, encontrou, visitada, Vet_visitados);
                if(x == -1) break;
                busca = x;// Proximo vertice a ser encontrado
            }
        }
        int peso = Principal.GRAFO[busca-1][inicial-1];
        if(peso != 0){
            Aresta a = new Aresta(busca - 1, inicial - 1, peso);
            arestas[Principal.VERTICES - 1] = a;
        }
        return true;
    }
    
    // Busca uma aresta para substituir, reconectando o caminho
    private static int concerta(int busca, Aresta[] arestas, int pos, Aresta[] visitada, int[] vet_visitados){
        ArrayList<Aresta> possiveis = new ArrayList<>();// Encontra arestas possiveis que conecte com o vertice
        for(int i=0; i<Principal.VERTICES; i++){
            if(Principal.GRAFO[busca-1][i] != 0)
                possiveis.add(new Aresta(busca-1, i, Principal.GRAFO[busca-1][i]));
        }
        Random rand = new Random();
        Aresta a = null;
        while(true){
            // Sorteia uma aresta que de seguimento no caminho
            int index = rand.nextInt(possiveis.size());
            a = possiveis.remove(index);
            if(verArestaVisitada(visitada, a) && !verVetJaVisitado(vet_visitados, a.getU()))
                break;
            if(possiveis.isEmpty())
                return -1;
        }
        arestas[pos] = a;
        addVisitaVertice(vet_visitados, a.getU());
        addVisitaAresta(visitada, a);
        return a.getU();
    }
    
    private static boolean verVetJaVisitado(int[] vet_visitado, int v){
        for(int vet : vet_visitado){
            if(vet == 0)
                break;
            else if(vet == v)
                return true;
        }
        return false;
    }
    
    private static void addVisitaVertice(int[] vet_visitados, int v){
        for(int i=0; i<vet_visitados.length; i++){
            if(vet_visitados[i] == 0){
                vet_visitados[i] = v;
                return;
            }
        }
    }
    
    private static boolean verArestaVisitada(Aresta[] visitada, Aresta a){
        for(Aresta aresta : visitada){
            if(aresta == null){
                return true;
            }else if(aresta.compara(a))
                return false;
        }
        return true;
    }
    
    private static void addVisitaAresta(Aresta[] visitados, Aresta a){
        for(int i=0; i<visitados.length; i++){
            if(visitados[i] == null){
                visitados[i] = a;
                return;
            }
        }
    }
    
    
}
