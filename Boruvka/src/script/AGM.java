package script;

import java.util.ArrayList;

public class AGM {
    // Classe responsavel por gerar a arvore gerador minima
    // Struct aresta | Uma subclasse de AGM
    class Aresta{
        int v, w; // Vetores da aresta

        public Aresta(int v, int w) {
            this.v = v;
            this.w = w;
        }
        // Função para comparar os vertices de arestas em busca de vertices ==
        // Conta as repetições
        public int compararVertices(Aresta a){
            int repeticao = 0;
            boolean b = a.v == v || a.v == w;
            if(b) repeticao++;
            b = a.w == v || a.w == w;
            if(b) repeticao++;
            return repeticao;
        }

        @Override
        public String toString() {
            return String.format("%s - %s\n", v, w);
        }
        
    }
    
    private Grafo grafo;
    private Aresta[] solucao; // Vetor que armazenara a solução
    private static final int INFINITO = 999999; // Peso infinito
    
    public AGM(Grafo grafo) {
        this.grafo = grafo;
        this.solucao = new Aresta[grafo.getTotalVertices()-1];
    }
    
    public void processa(){
        // Recolhe a matriz adjacente do grafo
        int matAdj[][] = grafo.getMatrizAdj();
        int tam = grafo.getTotalVertices(); // Tamanho do grafo
        // Vetor floresta conterá varias listas arvoress
        // Arvores por sua vez é uma lista com as aresta que a compôem
        ArrayList<Integer>[] floresta = new ArrayList[tam];
        // Lista para salvar arestas que não entrarem na floresta
        ArrayList<Aresta> vetSoltos = new ArrayList<>();
        int atricuicao = 0; // Total de arestas salvas no vetor solução
        
        // 1`Passo: Para cada vertice escolher arco de peso minimo
        for(int i=0; i<tam; i++){
            // Pega o indice da coluna com o menor valor
            int index = arestaCustoMinimo(matAdj[i]);
            Aresta aresta = new Aresta(i, index);
            // Verifica se irá gerar um ciclo antes de adicionar
            if(!verificaCiclo(aresta)){
                solucao[atricuicao++] = aresta; // Add no vetor solução
                adicionaNaArvore(floresta, aresta);
            }else
                vetSoltos.add(aresta);
        }
        // 2` Passo: Cria arvores para vertices que ainda não entram na floresta
        criarArvoreDaFloresta(vetSoltos, floresta);
        // 3` Passo: unir as arvores em uma so, resultando na AGM
        int tamFloresta = arvoresNaFloresta(floresta);
        // Enquanto a floresta não unir todas os vertices em uma so arvore
        while(tamFloresta > 1){
            // Procurar o menor arco para unir as duas arvores
            int peso = INFINITO; // Peso minimo
            Aresta aresta = null;
            for(Integer i : floresta[0])
                for(Integer j : floresta[1])
                    if(matAdj[i][j] != 0 && matAdj[i][j] < peso){
                        peso = matAdj[i][j];
                        aresta = new Aresta(i, j);
                    }
            solucao[atricuicao++] = aresta;
            // Uni as duas arvores
            floresta[0].addAll(floresta[1]);
            tamFloresta--; // reduz tamanho da floresta
            floresta[1] = floresta[tamFloresta];
        }
    }
    
    private void adicionaNaArvore(ArrayList<Integer>[] floresta, Aresta aresta){
        // Pecorre as arvores da floresta
        // Cada floresta[i] é uma arvore da floresta
        for(int i=0; i<floresta.length; i++){
            if(floresta[i] == null){
                // Adiciona uma nova arvore na floresta
                floresta[i] = new ArrayList<>();
                floresta[i].add(aresta.v);
                floresta[i].add(aresta.w);
                return;
            }else{
                // Percorre os vertices da arvore em busca de vertice repetido
                for(Integer vertice : floresta[i]){
                    if(vertice == aresta.v){
                        floresta[i].add(aresta.w); return;
                    }
                    if(vertice == aresta.w){
                        floresta[i].add(aresta.v); return;
                    }
                }
            }
        }
    }
    
    // Retorna qual o vertice irá gerar um arco de custo minimo
    private int arestaCustoMinimo(int coluna[]){
        int menor = INFINITO; // Peso infinito
        int index = 0; // Index do vetor que possui o maior valor
                   // O index corresponde a coluna na matriz adjacente
        for(int i=0; i<coluna.length; i++){
            if(coluna[i] != 0 && coluna[i] < menor){
                menor = coluna[i];
                index = i;
            }
        }
        // Retorna a posição do menor valor na coluna
        return index; // Corresponde ao vertice da coluna
    }
    
    private boolean verificaCiclo(Aresta aresta){
        // Variavel para contar o numero de repetição | não pode >= 2
        int repeticao = 0;
        // Percorre o vetor solução em busca de ciclo
        for(Aresta a : solucao){
            if(a == null) continue;
            // compara duas arestas em busca de vertices iguais
            repeticao += a.compararVertices(aresta);
            if(repeticao >= 2) return true;
        }
        // Chegou até aki não houve ciclo
        return false;
    }
    
    private void criarArvoreDaFloresta(ArrayList<Aresta> lista, ArrayList<Integer>[] floresta){
        if(lista.isEmpty()) return;
        int i;
        for(i=0; i<floresta.length; i++)
            if(floresta[i] == null) break;
        if(i == floresta.length) return;
        for(Aresta a : lista){
            boolean v = true, w = true;
            for(int k=0; k<i; k++){
                for(Integer vet : floresta[k])
                    if(a.v == vet){ v=false; break;}
                if(!v) break;
            }
            for(int k=0; k<i; k++){
                for(Integer vet : floresta[k])
                    if(a.w == vet){ w=false; break;}
                if(!w) break;
            }
            if(v){
                floresta[i] = new ArrayList<>();
                floresta[i++].add(a.v);
            }
            if(w){
                floresta[i] = new ArrayList<>();
                floresta[i++].add(a.w);
            }
        }
    }
    
    private int arvoresNaFloresta(ArrayList<Integer>[] floresta){
        int tam = 0;
        for(ArrayList<Integer> arvore : floresta)
            if(arvore == null) return tam;
            else tam++;
        return tam;
    }
    
    @Override
    public String toString() {
        String s = "";
        for(Aresta a : solucao)
            s += a;
        return s;
    }
    
}
