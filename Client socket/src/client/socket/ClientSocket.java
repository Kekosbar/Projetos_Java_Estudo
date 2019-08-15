package client.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientSocket {
    
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Iniciando conexão");
        try {
            Socket socket = new Socket("192.168.1.10", 2323);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Resposta: "+ entrada.readLine());
            System.out.println("Resposta: "+ entrada.readLine());
            System.out.println("Resposta: "+ entrada.readLine());
            entrada.close();
            socket.close();
            System.out.println("Conexão finalizada");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
