package script;

import java.awt.Desktop;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Principal {
    
    public static void main(String arg[]){
        GeraPDF pdf = new GeraPDF("Arquivo.pdf");
        pdf.criarImagem("src/img/emblemaCasa.png");
        pdf.criraTitulo("Assistidos");
        tabela(pdf);
        pdf.fechar();
        abrirPDF(pdf);
    }
    
    private static void abrirPDF(GeraPDF pdf){
        String arq = pdf.getLocalNomeArquivo();
        Desktop d = Desktop.getDesktop();
        try {
            d.open(new java.io.File(arq));
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void tabela(GeraPDF pdf){
        int colunas = 4;
        String[] cabecalho = new String[] {"N°","Nome","Idade","Peso"};
        String[] conteudo = new String[] {"1","Pablo Vitar","15","62,5",
                                          "2","Luciano Hulk","38","84,8",
                                          "3","Guilherme Almeida assunção","55","95,1"};
        int[] larguras = new int[] {1,5,2,2};
        pdf.criarTable(colunas, larguras, conteudo);
    }
}
