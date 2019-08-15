package script;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectSQL {

    private static final String USUARIO = "root";
    private static final String SENHA = "caiquegalo13sis";
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/";
    private static final String BANCO = "Futsall?useSSL=true";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private   Connection connection = null; 

    public ConnectSQL(){
        conectar();
    }
    // FUNÇÕES DE CONEXÃO AO BANCO
    //===================================
    public boolean conectar() {
        try {
            if (!isConect()) {
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(URL + BANCO, USUARIO, SENHA);
                return true;
            } else {
                System.out.println("Já esta conectado");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Falha ao encontrar o driver...");
        } catch (SQLException e) {
            System.out.println("Falha ao conectar ao servidor SQL...");
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
    
    // FUNÇÕES DE INSERÇÃO
    //==================================
    public boolean cadastrarCampeonato(Campeonato campeonato){
        String query = " insert into Campeonato (nome, ano)"
                + " values (?, ?)";
        PreparedStatement prepare;
        try {
            prepare = connection.prepareStatement(query);
            prepare.setString(1, campeonato.getNome());
            prepare.setInt(2, campeonato.getAno());
            prepare.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean cadastrarTime(Time time){
        String query = " insert into Times (nome, cidade, estado)"
                + " values (?, ?, ?)";
        PreparedStatement prepare;
        try {
            prepare = connection.prepareStatement(query);
            prepare.setString(1, time.getNome());
            prepare.setString(2, time.getCidade());
            prepare.setString(3, time.getEstado());
            prepare.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean cadastrarTime_Info(Time time, Campeonato campeonato){
        String query = " insert into TIME_INFO (Idtime, Idcamp)"
                + " values (?, ?)";
        PreparedStatement prepare;
        try {
            prepare = connection.prepareStatement(query);
            prepare.setInt(1, time.getId());
            prepare.setInt(2, campeonato.getId());
            prepare.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean cadastrarJOGADOR_Info(Jogador jogador, Campeonato campeonato){
        String query = " insert into JOGADOR_INFO (ID_JOGADOR, ID_CAMP)"
                + " values (?, ?)";
        PreparedStatement prepare;
        try {
            prepare = connection.prepareStatement(query);
            prepare.setInt(1, jogador.getId());
            prepare.setInt(2, campeonato.getId());
            prepare.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean cadastrarJogadores(Jogador jogador, Time time){
        String query = " insert into Jogador (NOME, IdTIME)"
                + " values (?, ?)";
        PreparedStatement prepare;
        try {
            prepare = connection.prepareStatement(query);
            prepare.setString(1, jogador.getNome());
            prepare.setInt(2, time.getId());
            prepare.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean cadastrarPartida(int idCamp, int rodada, Time timeLocal, Time timeVisitante, int golsLocal, int golsVisistnate){
        String query = "INSERT INTO Partidas (IdCampeonato, RODADA, IdTIMELOCAL, IdTIMEVISITANTE, GOLSLOCAL, GOLSVISITANTE)"
                + "VALUES (?,?,?,?,?,?)";
        PreparedStatement prepare;
        try {
            prepare = connection.prepareStatement(query);
            prepare.setInt(1, idCamp);
            prepare.setInt(2, rodada);
            prepare.setInt(3, timeLocal.getId());
            prepare.setInt(4, timeVisitante.getId());
            prepare.setInt(5, golsLocal);
            prepare.setInt(6, golsVisistnate);
            prepare.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    // FUNÇÕES DE SELECT
    //==================================
    public int getIdCampeonato(String nome){
        String query = "SELECT id FROM Campeonato WHERE NOME LIKE '"+nome+"'";
        try {
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(query);
            if(rs != null) {
                rs.next();
            }
            return rs.getInt("id");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public ArrayList<Partida> getAllPartidas(int idCamp){
        String query = "SELECT IdTIMELOCAL, IdTIMEVISITANTE FROM Partidas WHERE IdCampeonato=" + idCamp;
        ArrayList<Partida> listPartidas = new ArrayList<>();
        try {
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                int timeLocal = rs.getInt("IdTIMELOCAL");
                int timeVisitante = rs.getInt("IdTIMEVISITANTE");
                listPartidas.add(new Partida(timeLocal, timeVisitante));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listPartidas;
    }
    
    public int getIdTime(String nome){
        String query = "SELECT id FROM Times WHERE NOME LIKE '"+nome+"'";
        try {
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(query);
            if(rs != null) {
                rs.next();
            }
            return rs.getInt("id");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public Campeonato[] getAllCampeonatos(){
        String query = "SELECT ID, NOME, ANO FROM Campeonato";
        Campeonato camps[] = null;
        ArrayList<Campeonato> campList = new ArrayList<>();
        try {
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                String nome = rs.getString("NOME");
                int id = rs.getInt("ID");
                int ano = rs.getInt("ANO");
                campList.add(new Campeonato(id, nome, ano));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        camps = new Campeonato[campList.size()];
        camps = campList.toArray(camps);
        /*
        for(Campeonato camp : camps){
            camp.setTimes(getTimesInCamp(camp));
            for(Time time : camp.getTimes()){
                time.setJogadores(getJogadoresInTime(time));
                getINFOR_TIME(camp, time);
            }
        }
        */
        return camps;
    }
    
    public Time[] getAllTimes(){
        String query = "SELECT * FROM Times";
        Time[] times = null;
        ArrayList<Time> listTime = new ArrayList<>();
        try {
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("ID");
                String nome = rs.getString("NOME");
                String cidade = rs.getString("CIDADE");
                String estado = rs.getString("ESTADO");
                listTime.add(new Time(id, nome, cidade, estado));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        times = new Time[listTime.size()];
        times = listTime.toArray(times);
        return times;
    }
    
    public void getINFOR_TIME(Campeonato campeonato){
        String query = "SELECT ID, nome, VITORIA*3 + EMPATES AS Pts, JOGOS, VITORIA, EMPATES, DERROTAS, GP, GC, GP-GC AS SG FROM TIME_INFO \n" +
                        "INNER JOIN Times ON ID = IdTIME\n" +
                        "WHERE IDCAMP = "+campeonato.getId()+"\n" +
                        "ORDER BY Pts DESC, SG DESC, GP DESC";
        try {
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                String nome = rs.getString("nome");
                int id = rs.getInt("ID");
                Time time = new Time(id, nome);
                time.setJogos(rs.getInt("JOGOS"));
                time.setVitorias(rs.getInt("VITORIA"));
                time.setEmpates(rs.getInt("EMPATES"));
                time.setDerrotas(rs.getInt("DERROTAS"));
                time.setGP(rs.getInt("GP"));
                time.setGC(rs.getInt("GC"));
                time.setPontos(rs.getInt("Pts"));
                time.setSaldo(rs.getInt("SG"));
                campeonato.getTimes().add(time);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private ArrayList<Time> getTimesInCamp(Campeonato camp){
        String query = "SELECT ti.* FROM Campeonato ca INNER JOIN TIME_INFO inf\n" +
                        "ON ca.id = inf.idCamp INNER JOIN Times ti\n" +
                        "ON inf.idTime = ti.id\n" +
                        "WHERE ca.id = "+camp.getId();
        ArrayList<Time> timeList = new ArrayList<>();
        try {
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("ID");
                String nome = rs.getString("NOME");
                String CIDADE = rs.getString("CIDADE");
                String ESTADO = rs.getString("ESTADO");
                timeList.add(new Time(id, nome, CIDADE, ESTADO));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return timeList;
    }
    
    private ArrayList<Jogador> getJogadoresInTime(Time time){
        String query = "SELECT jo.* FROM Jogador jo INNER JOIN Times ti \n" +
                        "ON ti.id = jo.idtime\n" +
                        "WHERE ti.id = "+time.getId();
        ArrayList<Jogador> jogadoresList = new ArrayList<>();
        try {
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("IDJOGADOR");
                String nome = rs.getString("NOME");
                jogadoresList.add(new Jogador(id, nome));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jogadoresList;
    }
    
    // FUNÇÕES DE DELETE
    //==================================
    public boolean removeCampeonato(Campeonato campeonato){
        String query = "DELETE FROM Campeonato WHERE ID="+campeonato.getId();
        PreparedStatement prepare;
        try {
            prepare = connection.prepareStatement(query);
            prepare.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
