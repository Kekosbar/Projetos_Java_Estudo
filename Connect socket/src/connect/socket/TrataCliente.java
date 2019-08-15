package connect.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class TrataCliente extends Thread{
    
    private Socket socket;
    private BufferedReader entrada;
    private PrintWriter saida;

    public TrataCliente(Socket socket) {
        this.socket = socket;
        try {
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            saida = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void run(){
        saida.println("Conexao estabelecida");
        saida.println("Teste bem sucedido");
        saida.println("Finalizando conexao");
        try {
            entrada.close();
            saida.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
}
