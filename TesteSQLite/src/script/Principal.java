package script;

public class Principal {

    public static void main(String[] args) {
        SQLite sql = SQLite.getInstancia();
        System.out.println("Conexão: "+sql.isConect());
        sql.executeScript();
    }
    
}
