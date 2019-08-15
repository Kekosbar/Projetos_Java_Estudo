package script;

import java.io.Serializable;
import java.util.ArrayList;

public class Pasta implements Serializable{
    
    public String nome;
    public ArrayList<Pasta> subPastas = new ArrayList<>();
    public ArrayList<String> arquivos = new ArrayList<>();

    public Pasta(String nome) {
        this.nome = nome;
    }
    
}