package script;

import java.util.ArrayList;

public class Aptidao {

    // Calcula a média de aptidão de uma população
    public static int calcula_mediaAptidao(Populacao populacao){
        ArrayList<Individuo> individuos = populacao.getIndividuos();
        int aptidaoMedia = 0;
        for(Individuo ind : individuos)
            aptidaoMedia += ind.getAptidao();
        // Evita divisão por zero, pode ocorrer
        if(aptidaoMedia == 0) 
            return 0;
        else 
            return aptidaoMedia / individuos.size();
    }
    
    // Calcula a aptidão de um individuo
    public static int aptidao(Individuo ind){
        // apetidão medida em porcentagem
        int aptidao = 100 - ind.getPeso();
        // Se o caminho é valido aumenta a apetidão
        if(Caminho.verifica(ind))
            aptidao += 50;
        // Todos os vertices devem estar no caminho
        if(Caminho.todosVertices(ind.getC()))
            aptidao += 20;
        aptidao -= Caminho.arestasRepetidas(ind.getC()) * 5;
        return aptidao;
    }

}
