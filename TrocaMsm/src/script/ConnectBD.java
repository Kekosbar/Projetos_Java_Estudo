package script;

import java.sql.*;

public class ConnectBD {
    
    private static final String USUARIO = "root";
    private static final String SENHA = "caiquegalo13sis";
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/";
    private static final String BANCO = "TrocaMSM";
    private static final String COMPLEMENTO = "?useSSL=true";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private Connection connection = null; 

    public ConnectBD(){
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL + BANCO + COMPLEMENTO, USUARIO, SENHA);
        } catch (ClassNotFoundException e) {
            System.out.println("Falha ao encontrar o driver...");
        } catch (SQLException e) {
            System.out.println("Falha ao conectar ao servidor SQL...");
        }
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
    
    public boolean isConect(){
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
    
    public boolean enviarCadastro(Cadastro cadastro){
        String table = "Cadastro";
        String query = " insert into " + table + " (nome, senha, email, datanasc, sexo)"
                + " values (?, ?, ?, ?, ?)";
        try {
            PreparedStatement prepare = connection.prepareStatement(query);
            prepare.setString(1, cadastro.getNome());
            prepare.setString(2, cadastro.getSenha());
            prepare.setString(3, cadastro.getEmail());
            prepare.setString(4, cadastro.getStringDataNasc("yyyy-MM-dd"));
            prepare.setString(5, ""+cadastro.getSexo());
            prepare.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
