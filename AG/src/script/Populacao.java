package script;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Populacao {
    
    // Lista de individuos na população
    private ArrayList<Individuo> individuos = new ArrayList<>();
    private int geracao = 0; // Apenas para saber quantos gerações foram geradas
    private Individuo melhor; // Salva a melhor solução para o problema

    // Construtor da classe
    public Populacao(int inicial) {
        System.out.println("População inicial: "+inicial+"\n");
        for(int i=0; i<inicial; i++){
            Individuo ind = new Individuo();
            individuos.add(ind);
        }
        melhorIndividuo();
    }
    
    // Remove individuos até atingir o numero maximo de individuos permitidos
    public void elimina(int tamMin){
        if(individuos.size() > tamMin){
            Collections.sort(individuos); // Ordem para remover os menos aptos
            while(individuos.size() > tamMin){
                individuos.remove(0);
            }
        }
    }
    
    // Gera uma nova geração de individuos por cruzamento e mutação, "A mutação evita individuos com caminhos invalidos com 80% de precisão"
    public void novaGeracao() {
        geracao++; // Uma nova geração será gerada
        // Ira receber os filhos gerados
        Individuo[] novaPopulacao = new Individuo[individuos.size()];
        Iterator<Individuo> it = individuos.iterator();// Iterator para percorrer a lista de individuos
        int index = 0;
        while(it.hasNext()){
            Individuo pai = it.next();
            if(!it.hasNext()) break;// Se não houver proximo pare
            Individuo pai2 = it.next();
            // Filhos gerados no cruzamento entram na nova populacao
            Individuo[] filhos = Cruzamento.cruza(pai, pai2);
            novaPopulacao[index++] = filhos[0];
            novaPopulacao[index++] = filhos[1];
        }
        // Adiciona os novas filos a população
        for (Individuo ind : novaPopulacao) {
            if(ind != null)// Caso haja individuos nulos
                individuos.add(ind);
        }
        melhorIndividuo();
    }
    
    // Encontra apos cada nova geração o melhor individuo
    private void melhorIndividuo(){
        if(individuos.isEmpty()) return;
        // Melhor salvara o individuo mais apto, ou seja, com melhor caminho
        if(melhor == null)
            melhor = individuos.get(0);
        int maiorAptdao = melhor.getAptidao();
        // Busca pelo individuo de maior aptidão
        for(Individuo ind : individuos){
            if(ind.getAptidao() > maiorAptdao){
                maiorAptdao = ind.getAptidao();
                melhor = ind;
            }
        }
    }
    
    // GETTERS E SETTERS
    //=============================================================
    public ArrayList<Individuo> getIndividuos() {
        return individuos;
    }

    public void setIndividuos(ArrayList<Individuo> individuos) {
        this.individuos = individuos;
    }

    public int getGeracao() {
        return geracao;
    }

    public Individuo getMelhor() {
        return melhor;
    }

    @Override
    public String toString() {
        String s = "";
        s += "\nGeração: "+geracao;
        s += "\nPopulação: "+individuos.size();
        s += "\nAptidão do melhor: "+melhor.getAptidao();
        s += "\nPeso total do melhor: "+melhor.getPeso();
        s += "\nAptidão média: "+Aptidao.calcula_mediaAptidao(this);
        s += "\n==================================";
        return s;
    }
    
    
}
