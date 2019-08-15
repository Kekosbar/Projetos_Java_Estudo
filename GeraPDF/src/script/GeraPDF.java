package script;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GeraPDF {

    
    private static final String pasta = "PDFs";
    private String nomeArquivo;
    private Document document;
    private PdfWriter pdfWriter;
    public static final Font FONT = new Font(FontFamily.HELVETICA, 12, Font.NORMAL, GrayColor.GRAYWHITE);
    
    public GeraPDF(String nomeArquivo) {
        criarPasta();
        this.nomeArquivo = nomeArquivo;
        document = new Document();
        try {
             pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(pasta + "/" + nomeArquivo));
            document.open();    
        } catch (FileNotFoundException | DocumentException ex) {
            Logger.getLogger(GeraPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void criarPasta() {
        File diretorio = new File(pasta);
        if (!diretorio.exists()) {
            diretorio.mkdirs(); //mkdir() cria somente um diretório, mkdirs() cria diretórios e subdiretórios.
        } else {
            System.out.println("Diretório já existente");
        }
    }
    
    public void criraTitulo(String subtitulo){
        Font fonttitulo = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, BaseColor.BLACK);
        fonttitulo.setStyle(Font.BOLD);
        Paragraph pTitulo = new Paragraph("\n\n\n\n"+subtitulo+"\n", fonttitulo);
        pTitulo.setAlignment(Element.ALIGN_CENTER);
        add(pTitulo);
        
        fonttitulo = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, BaseColor.BLACK);
        fonttitulo.setStyle(Font.BOLD);
        pTitulo = new Paragraph(dataAtual()+"\n\n\n", fonttitulo);
        pTitulo.setAlignment(Element.ALIGN_CENTER);
        add(pTitulo);
    }
    
    public void criarTable(int colunas, int[] larguraColunas, String[] conteudos) {
    	// a table with three columns
        PdfPTable table = new PdfPTable(colunas);
        Font fonte = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, BaseColor.BLACK);
        try {
            table.setWidths(larguraColunas);
            // we add a cell with colspan 3
            for (String conteudo : conteudos) {
                Phrase p = new Phrase(conteudo, fonte);
                table.addCell(p);
            }
            document.add(table);
        } catch (DocumentException ex) {
            Logger.getLogger(GeraPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void criarImagem(String dest){
        PdfContentByte cb = pdfWriter.getDirectContentUnder();
        try {
            Image img = getWatermarkedImage(cb, Image.getInstance(dest));
            img.setAbsolutePosition(250, 725);
            document.add(img);
        } catch (BadElementException | IOException ex) {
            Logger.getLogger(GeraPDF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(GeraPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void fechar(){
        document.close();
    }
    
    public String getLocalNomeArquivo(){
        return pasta + "/" + nomeArquivo;
    }
    
    private void add(Element e){
        try {
            document.add(e);
        } catch (DocumentException ex) {
            Logger.getLogger(GeraPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String dataAtual(){
        Calendar hoje = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return format.format(hoje.getTime());
    }
    
    private Image getWatermarkedImage(PdfContentByte cb, Image img) throws DocumentException {
        img.setAbsolutePosition(500, 50);
        float width = img.getScaledWidth();
        float height = img.getScaledHeight();
        PdfTemplate template = cb.createTemplate(width, height);
        template.addImage(img, width, 0, 0, height, 0, 0);
        
        return Image.getInstance(template);
    }
    
}
