package script;

import java.io.File;

public class Arquivo {
       
    public String[] lerArquivos(String pasta){
        File folder = new File(pasta);
        File files[] = folder.listFiles();
        String nome[] = new String[files.length];
        for(int i=0; i<files.length; i++){
            nome[i] = files[i].getName();
        }
        return nome;
    }
    
    public File getMusic(String pasta, String music){
        File arqMusic = new File(pasta+"/"+music);
        System.out.println("Nome: "+arqMusic.getName());
        return arqMusic;
    }
    
}
