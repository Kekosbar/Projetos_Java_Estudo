package script;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AutoPlay {

    private static ArrayList<String> fila = new ArrayList<String>();
    private static boolean repeti = false;
    
    public static Tocador tocarNovaMusic(Tocador tocador, Connect connect, String music) {
        if(!procuraItem(music)) enfileira(music);
        if (tocador != null) 
            if(!tocador.isStopTotal()) tocador.stop();
        if(!ArquivaMusic.verificaArquivoExiste(music))
            try {
                connect.solicitaMusic(music);
            } catch (IOException ex) {
                Logger.getLogger(AutoPlay.class.getName()).log(Level.SEVERE, null, ex);
            }
        tocador = new Tocador(music);
        tocador.play();
        return tocador;
    }
    
    public static void enfileira(String music){
        if(fila.size() == 3) desemfileita();
        fila.add(music);
        imprimiLista();
    }
    
    public static String desemfileita(){
        String music = fila.remove(0);
        ArquivaMusic.apagaMusic(music);
        System.out.println("Desenfileirou: "+music+"\n---------------------------------------------------------");
        return music;
    }
    
    public static int tamanhoDaDila(){
        return fila.size();
    }
    
    private static boolean procuraItem(String encontrar){
        int tam = fila.size();
        for(int i=0; i<tam; i++){
            if(fila.get(i).equals(encontrar))
                return true;
        }
        return false;
    }
    
    private static void imprimiLista(){
        int tam = fila.size();
        for(int i=0; i<tam; i++){
            System.out.println(fila.get(i));
        }
    }
    
    public static void novaFila(String str[]){
        int tam = fila.size();
        ArquivaMusic.apagaMusic(fila.get(tam-1));
        fila.removeAll(fila);
        for(int i=0; i<tam; i++)
            fila.add(str[i]);
        System.out.println("----------------------------------------------------");
        imprimiLista();
    }
    
    public static String[] Aleatorio(String vet[]){
        int x = vet.length;
        int tam = x;
        String[] aux = new String[tam];
        Random r = new Random();
        for(int i=0; i<tam; i++){
            int k = r.nextInt(x--);
            aux[i] = vet[k];
            vet[k] = vet[tam - 1 - i];
        }
        return aux;
    }

    public static boolean isRepeti() {
        return repeti;
    }
    
    public static void ativaRepeticao(){
        repeti = true;
    }
    
    public static void desativaRepeticao(){
        repeti = false;
    }
}
