package script;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class TrataCliente extends Thread{
    
    private Socket socket;
    private BufferedReader entrada;
    private PrintWriter saida;
    private ObjectOutputStream objectOut;
    private Arquivo arquivo;

    public TrataCliente(Socket socket) {
        this.socket = socket;
        try {
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            saida = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            objectOut = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
    
    @Override
    public void run(){
        String op = "1";
        try{
            arquivo = new Arquivo(saida, entrada);
            while(true){
                switch(op){
                    case "1":
                        objectOut.flush();
                        Pasta pasta = arquivo.lerPastas();
                        if(pasta != null) System.out.println(pasta.nome);
                        objectOut.writeObject(pasta);
                        break;
                }
            op = entrada.readLine();
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Perdeu a conexão");
            finalizaConnect();
        }
    }
    
    public void finalizaConnect(){
        try{
            socket.close();
            entrada.close();
            saida.close();
            System.out.println("Finalizando conexão e reiniciando");
            Main.iniciaServer();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
