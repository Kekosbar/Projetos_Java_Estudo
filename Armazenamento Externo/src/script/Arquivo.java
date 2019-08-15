package script;

import java.io.BufferedReader;
import java.io.File;
import java.io.PrintWriter;

public class Arquivo {
 
    private Pasta folderPai;   
    private String dirName = "/media/caique/Documents/Documentos/Caique/UFVJM";
    private PrintWriter saida;
    private BufferedReader entrada;

    public Arquivo(PrintWriter saida, BufferedReader entrada) {
        this.saida = saida;
        this.entrada = entrada;
    }
    
    public Pasta lerPastas(){
        File pastaPai = new File(dirName);
        folderPai = new Pasta(pastaPai.getName());
        pecorrePasta(pastaPai, folderPai);
        return folderPai;
    }
    
    public void pecorrePasta(File folder, Pasta pasta){
        File files[] = folder.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                pasta.subPastas.add(new Pasta(file.getName()));
                pecorrePasta(file, pasta.subPastas.get(pasta.subPastas.size()-1));
            }else
                pasta.arquivos.add(file.getName());
        }
    }
    
}
