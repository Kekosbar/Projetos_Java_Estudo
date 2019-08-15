package script;

import java.sql.*;

public class ConnectSQL {

    private static final String USUARIO = "caique";
    private static final String SENHA = "449e149sis";
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/";
    private static final String BANCO = "xenastore?useSSL=true";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private   Connection connection = null; 

    public boolean  conectar() {
         	try{
         		if(!isConect() ){
         			Class.forName(DRIVER);
         			connection = DriverManager.getConnection(URL+BANCO, USUARIO, SENHA);
         			return true;
         		}else
         			System.out.println("Já esta conectado");
         	}catch(ClassNotFoundException e){
         		System.out.println("Falha ao encontrar o driver...");
         	}catch(SQLException e){
         		System.out.println("Falha ao conectar ao servidor SQL...");
         	}
         	return false;
    }
    
    public boolean disconectar(){
   		try{
   			if(!connection.isClosed()){
	    			connection.close();
	    			return true;
		    	}else{	
		    		System.out.println("Já esta desconectado");
		    		return false;
		    	}
    		}catch(SQLException e){
    			System.out.println("Falha ao desconectar...");
    			return false;
    		}
    }
    
    public boolean isConect(){
	    if(connection != null){
	         try {
	              return !connection.isClosed();
	          } catch (SQLException e) {
	               System.out.println("Falha na verificação da conexão");
	               return false;
	          }
	    }else{
	    		return false;
	    	}
    }
    
    public String enviaDados(String nome, String sobNome,String nascimento, String nacionalidade){
	    String table = "cadastros";
	    String query = " insert into "+table+" (nome, sobNome, nascimento, nacionalidade)"
			        + " values (?, ?, ?, ?)";
	    try {
		    PreparedStatement prepare = connection.prepareStatement(query);
		    prepare.setString(1, nome);
		    prepare.setString(2, sobNome);
		    prepare.setString(3, nascimento);
		    prepare.setString(4, nacionalidade);
                    prepare.execute();
                    return "Dados enviados";
		} catch (SQLException e) {
                    e.printStackTrace();
                    return "Ocorreu um erro ";
		}
    }

}
