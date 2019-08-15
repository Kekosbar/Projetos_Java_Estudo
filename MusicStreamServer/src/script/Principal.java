package script;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Principal {

    private static ServerSocket server;
    private static TrataCliente cliente;
    private static int porta = 2121;
    
    public static void main(String arg[]){
        while(true){
        System.out.println("Ouvindo na porta: "+porta);
            try {
                server = new ServerSocket(porta);
                cliente = new TrataCliente(server.accept());
                cliente.comunica();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            } finally{
                cliente.finalizaConexao();
            try {
                server.close();
            } catch (IOException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        System.out.println("Finalizado");
        }
    }
}
