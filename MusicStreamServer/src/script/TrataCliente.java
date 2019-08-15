package script;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TrataCliente {

    private Socket socket;
    private String caminho;
    private BufferedReader entrada;
    private PrintWriter saida;
    private ObjectOutputStream objectOut;

    public TrataCliente(Socket socket) {
        this.socket = socket;
        try {
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            saida = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            objectOut = new ObjectOutputStream(socket.getOutputStream());
            caminho = "C:\\MusicasStream";
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void comunica() {
        System.out.println("Conexão aceita");
        try{
            while(true){
                String res = entrada.readLine();
                switch(res){
                    case "cam":
                        caminho = entrada.readLine();
                        String nomes[] = new Arquivo().lerArquivos(caminho);
                        objectOut.writeObject(nomes);
                        objectOut.flush();
                        objectOut.reset();
                        break;
                    case "sarq":
                        enviarMusica(entrada.readLine());
                        break;
                    case "fim":
                        return;
                }
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }catch(NullPointerException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void enviarMusica(final String nameMusic) throws IOException{
        System.out.println("Musica solicitada: "+nameMusic);
        new Thread(){
            @Override
            public void run() {
                System.out.println("Iniciou a thread");
                Socket socket = null;
                ServerSocket server = null;
                PrintWriter saida = null;
                DataOutputStream dos = null;
                FileInputStream fis = null;
                try {
                    System.out.println("Aguardando conexão");
                    server = new ServerSocket(2122);
                    socket = server.accept();
                    System.out.println("Conexão aceita");
                    saida = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
                    File file = new File(caminho+"\\"+nameMusic);
                    saida.println(""+file.length());
                    System.out.println(file.getName());
                    dos = new DataOutputStream(socket.getOutputStream());
                    fis = new FileInputStream(caminho+"\\"+nameMusic);
                    byte[] buffer = new byte[(int) file.length()];

                    while (fis.read(buffer) > 0) {
                        dos.write(buffer);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(TrataCliente.class.getName()).log(Level.SEVERE, null, ex);
                }finally{
                    try {
                        fis.close();
                        dos.close();
                        saida.close();
                        socket.close();
                        server.close();
                    } catch (IOException ex) {
                        Logger.getLogger(TrataCliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                System.out.println("SRVER FIM MUSICA SEND");
            }
        }.start();
    }
    
    public void finalizaConexao(){
        try {
            entrada.close();
            saida.close();
            socket.close();
            objectOut.close();
        } catch (IOException ex) {
            Logger.getLogger(TrataCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
