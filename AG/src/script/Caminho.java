package script;

import java.util.Arrays;

public class Caminho {
    
    // Verifica se o caminho é valido
    public static boolean verifica(Individuo ind){
        int tam = Principal.VERTICES*2;
        Aresta[] arestas = ind.getC();
        // Arestas ja visitados
        Aresta[] aresta_visitada = new Aresta[tam/2];
        // Vertices visitados
        int[] visitados = new int[tam];
        int inicial = arestas[0].getV();
        int busca = arestas[0].getU();
        Caminho caminho = new Caminho();
        int j=0;
        int encontrado=0;
        // Percorre as aresta do vetor c[] em busca do vertice busca
        // seguindo assim o caminho
        while (encontrado < Principal.VERTICES-1){
            boolean encontrou = false; // Se encontrar vertice busca recebe TRUE
            for (j=1; j<Principal.VERTICES; j++) {
                // Se aresta visitada retorna false
                if(!caminho.visita(aresta_visitada, arestas[j]))
                    continue;
                // Se encontrar o vertice de busca
                if(busca == arestas[j].getV()){
                    if(!caminho.visita(visitados, arestas[j].getV()))
                        return false;
                    busca = arestas[j].getU();
                    encontrou = true;
                    caminho.addVisita(aresta_visitada, arestas[j]);
                    break;
                }else if(busca == arestas[j].getU()){
                    if(!caminho.visita(visitados, arestas[j].getU()))
                        return false;
                    busca = arestas[j].getV();
                    encontrou = true;
                    caminho.addVisita(aresta_visitada, arestas[j]);
                    break;
                }
            }
            if(encontrou){
                encontrado++;
            }else
                return false;
        }
        // O caixeiro deve retorna a origem
        return inicial == busca;
    }
    
    // Se vertice v ja foi visitado retorna false
    public boolean visita(int[] visitados, int v){
        int i;
        for(i=0; i<visitados.length; i++){
            if(visitados[i] == 0){
                visitados[i] = v;
                return true;
            }else if(visitados[i] == v)
                return false;
        }
        return true;
    }
    
    // Se aresta a já foi visitada retorna false
    public boolean visita(Aresta[] visitados, Aresta a){
        int i;
        for(Aresta aresta : visitados){
            if(aresta == null){
                return true;
            }else if(aresta.compara(a))
                return false;
        }
        return true;
    }
    
    public void addVisita(Aresta[] visitados, Aresta a){
        int i;
        for(i=0; i<visitados.length; i++){
            if(visitados[i] == null){
                visitados[i] = a;
                return;
            }
        }
    }
    
    // Todos os vertices devem ser visitados
    public static boolean todosVertices(Aresta[] arestas){
        int[] vertices = new int[Principal.VERTICES*2];
        int i=0;
        for(Aresta a : arestas){
            vertices[i++] = a.getV();
            vertices[i++] = a.getU();
        }
        Arrays.sort(vertices);
        int atual = 1;
        for(i=0; i<Principal.VERTICES*2; i+=2){
            if(vertices[i] != atual++)
                return false;
        }
        return true;
    }
    
    // Verifica se há arestas repetidas
    public static int arestasRepetidas(Aresta[] c){
        int repeticao = 0;
        for(int i=0; i<c.length; i++){
            for(int j=0; j<c.length; j++){
                if(i != j && c[i].compara(c[j]))
                    repeticao++;
            }
        }
        return repeticao;
    }
}
