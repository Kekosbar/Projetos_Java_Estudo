package script;

import java.util.ArrayList;

public class Time implements Comparable<Time>{

    private int id;
    private String nome;
    private String cidade;
    private String estado;
    private int pontos;
    private int jogos;
    private int vitorias;
    private int empates;
    private int derrotas;
    private int GP;
    private int GC;
    private int saldo;
    private ArrayList<Jogador> jogadores = new ArrayList<>();

    public Time(String nome, String cidade, String estado) {
        this.nome = nome;
        this.cidade = cidade;
        this.estado = estado;
    }

    public Time(int id, String nome, String cidade, String estado) {
        this.id = id;
        this.nome = nome;
        this.cidade = cidade;
        this.estado = estado;
    }

    public Time(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Jogador> getJogadores() {
        return jogadores;
    }

    public void setJogadores(ArrayList<Jogador> jogadores) {
        this.jogadores = jogadores;
    }

    public int getJogos() {
        return jogos;
    }

    public void setJogos(int jogos) {
        this.jogos = jogos;
    }

    public int getVitorias() {
        return vitorias;
    }

    public void setVitorias(int vitorias) {
        this.vitorias = vitorias;
    }

    public int getEmpates() {
        return empates;
    }

    public void setEmpates(int empates) {
        this.empates = empates;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public void setDerrotas(int derrotas) {
        this.derrotas = derrotas;
    }

    public int getGP() {
        return GP;
    }

    public void setGP(int GP) {
        this.GP = GP;
    }

    public int getGC() {
        return GC;
    }

    public void setGC(int GC) {
        this.GC = GC;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "Time{" + "nome=" + nome + ", cidade=" + cidade + ", estado=" + estado + '}';
    }

    @Override
    public int compareTo(Time t) {
        if(pontos > t.pontos)
            return -1;
        else if(pontos == t.pontos){
            if(saldo > t.saldo)
                return -1;
            else if(saldo == t.saldo)
                if(GP > t.GP)
                    return -1;
                else if(GP < t.GP)
                        return -1;
                    else
                        return 0;
        }else
            return 1;
        return 0;
    }
    
}
