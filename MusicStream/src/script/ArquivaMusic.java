package script;

import java.io.File;

public abstract class ArquivaMusic {

    public static final String pasta = "/media/Documents/MusicasStream";
    //private static ArrayList<File> lista = new ArrayList<File>();
    
    public static void criarPasta(){
        File dir = new File(pasta);
        if(dir.exists())
            System.out.println("Pasta ja existe");
        else{
            dir.mkdir();
            System.out.println("Pasta criada");
        }
        apagaTodasAsMusics();
    }
    
    public static boolean verificaArquivoExiste(String arquivo){
        File arq = new File(pasta+"\\"+arquivo);
        boolean b = arq.exists();
        return b;
    }
    
    public static void reservaDeleteMusic(String music){
        File arq = new File(pasta+"\\"+music);
        arq.deleteOnExit();
    }
    
    public static void apagaMusic(String music){
        File arq = new File(pasta+"\\"+music);
            arq.delete();
    }
    
    private static void apagaTodasAsMusics(){
        File dir = new File(pasta);
        File list[] = dir.listFiles();
        int tam = list.length;
        for(File file : list)
            file.delete();
    }
    
}
