package script;

import java.util.ArrayList;

public class Principal {
    
    public static final int VERTICES = 16;
    public static final int[][] GRAFO = {
        {0, 3,5,14, 7,11, 0, 0,0,0,0, 6,0, 0,3, 0},
        {3, 0,0, 0, 0, 3, 2, 0,4,9,0, 0,1, 0,0,18},
        {5, 0,0, 0, 2, 1, 0, 3,0,0,8, 1,0, 2,0, 0},
        {14,0,0, 0, 0, 0, 0, 0,2,3,0, 0,8,15,0, 0},
        {7, 0,2, 0, 0, 0,12, 1,0,6,7, 0,7, 0,0, 1},
        {11,3,1, 0, 0, 0, 0, 5,0,0,0, 0,6, 0,0,12},
        {0, 2,0, 0,12, 0, 0, 0,1,0,2, 0,0,11,1, 0},
        {0, 0,3, 0, 1, 5, 0, 0,0,7,0,13,0, 0,6,17},
        {0, 4,0, 2, 0, 0, 1, 0,0,0,0, 5,0, 1,0, 0},
        {0, 9,0, 3, 6, 0, 0, 7,0,0,0, 1,0, 0,0, 3},
        {0, 0,8, 0, 7, 0, 2, 0,0,0,0, 0,0, 5,9, 4},
        {6, 0,1, 0, 0, 0, 0,13,5,1,0, 0,0, 9,0, 0},
        {0, 1,0, 8, 7, 6, 0, 0,0,0,0, 0,0, 0,0, 0},
        {0, 0,2,15, 0, 0,11, 0,1,0,5, 9,0, 0,2, 3},
        {3, 0,0, 0, 0, 0, 1, 6,0,0,9, 0,0, 2,0, 4},
        {0,18,0, 0, 1,12, 0,17,0,3,4, 0,0, 3,4, 0}
        };

    // Vetor que conterá somente as rotas existentes no grafo
    // Exemplo: Da cidade 2 a 3 valor 0, ou seja, não existe uma aresta entre estes 2 vertices
    // Conceito de IA: Sempre reduzir o seu espaço de estado
    public static final ArrayList<Aresta> arestasPossiveis = new ArrayList<>();
    
    
    public static void main(String[] args) {
        System.out.println("    ALGORITMO GENÉTICO");
        System.out.println("================================\n");
        // Carrega a lista de arestas possiveis do GRAFO
        geraArrayArestasPossiveis();
        // Inicia população com numeros de individuos na população inicial
        Populacao populacao = new Populacao(500);
        System.out.println(populacao);
        
        // INICIA O PROCESSO DO AG
        final int geracoes = 25; // Qts de gerações á processar
        final int tamMaximo = 50000; // Tamanho maximo da população
        for(int i=0; i<geracoes; i++){
            // Gera uma nova população
            populacao.novaGeracao();
            // Elimina menos aptos se população em excesso
            populacao.elimina(tamMaximo);
            System.out.println(populacao);
        }
        // Recolhe o melhor individuo após o termino do processamento
        Individuo solucao = populacao.getMelhor();
        System.out.println("Resultado:\n"+solucao+"\n");
    }
    
    
    // Encontra as arestas possiveis
    private static void geraArrayArestasPossiveis(){
        for(int i=0; i<VERTICES; i++){
            for(int j=0; j<VERTICES; j++){
                if(GRAFO[i][j] != 0 && !seExiste(arestasPossiveis, new Aresta(i, j, 0))){
                    arestasPossiveis.add(new Aresta(i, j, GRAFO[i][j]));
                }
            }
        }
    }
    
    // Não salva arestas repetidas, verificando se ela já existe na lista
    private static boolean seExiste(ArrayList<Aresta> lista, Aresta aresta){
        for(Aresta a : lista){
            if(a.compara(aresta))
                return true;
        }
        return false;
    }
    
}
