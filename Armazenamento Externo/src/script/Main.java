package script;

import java.io.IOException;
import java.net.ServerSocket;

public class Main implements Runnable{

    private static ServerSocket server;
    
    public static void main(String[] args) {
        try {
            server = new ServerSocket(2525);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        iniciaServer();
    }

    public static void iniciaServer(){
        new Thread(new Runnable(){
            @Override
            public void run(){
                new Main().run();
            }
        }).start();
    }
    
    @Override
    public void run() {
        try {
            System.out.println("Ouvindo na porta: 2525");
            System.out.println("Aguardando conexão");
            new TrataCliente(server.accept()).start();
            System.out.println("Conexão estabelecida");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
    
}
