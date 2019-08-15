package connect.socket;

import java.net.ServerSocket;

public class ConnectSocket implements Runnable{

    private ServerSocket server;
    
    public ConnectSocket(int porta) throws Exception{
        server = new ServerSocket(porta);
        new Thread(this).start();
        System.out.println("Servidor ouvindo na porta:" + porta);
    }
    
    public void run(){
        try{
            while(true){
                new TrataCliente(server.accept()).start();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        try{
            new ConnectSocket(2323);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
