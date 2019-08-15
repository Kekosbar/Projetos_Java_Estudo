package script;

import java.util.ArrayList;

public class Campeonato {

    private int id;
    private String nome;
    private int ano;
    private ArrayList<Time> times = new ArrayList<>();;

    public Campeonato(String nome, int ano) {
        this.nome = nome;
        this.ano = ano;
    }

    public Campeonato(int id, String nome, int ano) {
        this.id = id;
        this.nome = nome;
        this.ano = ano;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public ArrayList<Time> getTimes() {
        return times;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTimes(ArrayList<Time> times) {
        this.times = times;
    }

    @Override
    public String toString() {
        return "Campeonato{" + "nome=" + nome + ", ano=" + ano + ", qtsTimes=" +'}';
    }
    
}
