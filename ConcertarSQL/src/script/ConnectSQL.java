package script;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectSQL {

    private static final String URL = "jdbc:sqlite:";
    private static final String BANCO = "dados.db";
    private Connection connection = null;
    private static ConnectSQL instancia;
    private static final String ERROFATAL = "\nERRO FATAL, Contate o desenvolvedor. TEL: (38)988050711\n";

    private ConnectSQL(){
    }
    
    public static synchronized ConnectSQL getInstancia(){
        if(instancia == null)
            instancia = new ConnectSQL();
        if(!instancia.isConect())
            instancia.conectar();
        return instancia;
    }
    
    public boolean conectar() {
        try {
            if (!isConect()) {
                //Class.forName(DRIVER);
                connection = DriverManager.getConnection(URL + BANCO);
                System.out.println("Conectado com sucesso");
                return true;
            } else {
                System.out.println("Já esta conectado");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean disconectar() {
        try {
            if (!connection.isClosed()) {
                connection.close();
                return true;
            } else {
                System.out.println("Já esta desconectado");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Falha ao desconectar...");
            return false;
        }
    }

    public boolean isConect() {
        if (connection != null) {
            try {
                return !connection.isClosed();
            } catch (SQLException e) {
                System.out.println("Falha na verificação da conexão");
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }
    
    public ArrayList<String> getAllDoacaoComunidade(){
        String query = "SELECT dataRegistro FROM Saida_Produto";
        ArrayList<String> datas = new ArrayList<>();
        try{
            Statement prepare = connection.createStatement();
            ResultSet rs = prepare.executeQuery(query);
            while(rs.next()){
                datas.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datas;
    }
    
    public boolean atualizar(String dataAntiga, String dataNova){
        String query = "UPDATE Saida_Produto"
                    + " SET dataRegistro = ?"
                    + " WHERE dataRegistro LIKE ?";
        try{
            PreparedStatement prepare = connection.prepareStatement(query);
            prepare.setString(1, dataNova);
            prepare.setString(2, dataAntiga);
            prepare.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectSQL.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
}
