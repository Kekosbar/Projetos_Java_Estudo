package script;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class SQLite {

    private static final String URL = "jdbc:sqlite:";
    private static final String BANCO = "dados.db";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private Connection connection = null;
    private static SQLite instancia;
    private static final String ERROFATAL = "\nERRO FATAL, Contate o desenvolvedor. TEL: (38)988050711\n";

    private SQLite(){
    }
    
    public static synchronized SQLite getInstancia(){
        if(instancia == null)
            instancia = new SQLite();
        if(!instancia.isConect())
            instancia.conectar();
        return instancia;
    }
    
    public boolean conectar() {
        try {
            if (!isConect()) {
                //Class.forName(DRIVER);
                connection = DriverManager.getConnection(URL + BANCO);
                return true;
            } else {
                System.out.println("Já esta conectado");
            }
        } catch (SQLException e) {
            printSQLException(e);
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
                return false;
            }
        } else {
            return false;
        }
    }
    
    public boolean executeScript() {
        String query = "CREATE TABLE Tabela(int n)";
        try {
            PreparedStatement prepare = connection.prepareStatement(query);
            prepare.executeUpdate();
            //this.connection.commit();
            System.out.println("Criação de Estruturas das Tabelas OK");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    private void printSQLException(SQLException ex) {
        Logger.getLogger(SQLite.class.getName()).log(Level.SEVERE, null, ex);
        int erroCode = ex.getErrorCode();
        String erro = "ERRO! ("+ erroCode +") ";
        switch(erroCode){
            case 0:
                erro += "O Banco de dados não foi encontrado"+ERROFATAL;
                break;
            case 1062:
                erro += "Este registro já foi cadastrado\n";
                erro += "Mensagem do Banco de dados: '"+ex.getMessage()+"'";
                break;
            case 1292:
                erro += "O valor da data é invalido. Data inexistente";
                break;
            default:
                erro += ex.getMessage();
        }
        mostraErro(erro);
    }
 
    private void mostraErro(String erro){
        JOptionPane optionPane = new JOptionPane(erro, JOptionPane.ERROR_MESSAGE);
        JDialog dialog = optionPane.createDialog("ERRO");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }
}
