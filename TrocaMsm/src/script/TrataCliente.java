package script;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TrataCliente extends Thread{
    
    private Socket socket;
    private ObjectOutputStream saida;
    private ObjectInputStream entrada;

    public TrataCliente(Socket socket) {
        this.socket = socket;
        try {
            saida = new ObjectOutputStream(socket.getOutputStream());
            entrada = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }

    @Override
    public void run() {
        try {
            String key = (String) entrada.readObject();
            System.out.println("Key: " + key);
            if (key.equals("Cadastro")) cadastro();
            
        } catch (IOException ex) {
            Logger.getLogger(TrataCliente.class.getName()).log(Level.SEVERE, null, ex);
            return;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TrataCliente.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
    }
    
    private boolean cadastro() throws IOException, ClassNotFoundException{
        System.out.println("Aguardando objeto");
        Cadastro cadastro = (Cadastro) entrada.readObject();
        System.out.println("Recebeu objeto");
        ConnectBD bd = new ConnectBD();
        if(!bd.isConect()) return false;
        boolean r = bd.enviarCadastro(cadastro);
        bd.disconectar();
        System.out.println("Fim bd envia result");
        String resultado;
        if(r) resultado = "s"; // s para sucesso
        else resultado = "n"; // n para falha
        saida.writeObject(resultado);
        return true;
    }
    
}
