package script;

import static janela.JAN_Principal.JLmusicStatus;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connect {

    private Socket socket;
    private BufferedReader entrada;
    private PrintWriter saida;
    private ObjectInputStream objectIn;
    private String ip;
    
    public Connect(String ip){
        try {
            this.ip = ip;
            socket = new Socket(ip, 2121);
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            saida = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            objectIn = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public boolean isConnectado(){
        return socket.isConnected();
    }
    
    public String[] enviarCaminho(String caminho){
        saida.println("cam"); // codigo de solicitação
        saida.println(caminho);
        String nomes[];
        try {
            nomes = (String[]) objectIn.readObject();
        } catch (IOException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return nomes;
    }
    
    public void solicitaMusic(final String music) throws IOException{
        saida.println("sarq");
        saida.println(music);
        Socket client = null;
        DataInputStream dis = null;
        FileOutputStream fos = null;
        BufferedReader entrada = null;
        try {
            JLmusicStatus.setText("Downloading...");
            client = new Socket(ip, 2122);
            entrada = new BufferedReader(new InputStreamReader(client.getInputStream()));
            int filesize = Integer.parseInt(entrada.readLine());
            dis = new DataInputStream(client.getInputStream());
            fos = new FileOutputStream(ArquivaMusic.pasta + "\\" + music);
            byte[] buffer = new byte[filesize];

            int read = 0;
            int remaining = filesize;
            while ((read = dis.read(buffer, 0, Math.min(buffer.length, remaining))) > 0) {
                remaining -= read;
                fos.write(buffer, 0, read);
            }
            
            AutoPlay.enfileira(music);
            JLmusicStatus.setText("Carregado com sucesso");
        } catch (IOException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
            JLmusicStatus.setText("Falha no download...");
        } finally {
            try {
                dis.close();
                fos.close();
                entrada.close();
                client.close();
            } catch (IOException ex) {
                Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void finaliza() throws IOException{
        entrada.close();
        saida.close();
        objectIn.close();
        socket.close();
    }
    
}
