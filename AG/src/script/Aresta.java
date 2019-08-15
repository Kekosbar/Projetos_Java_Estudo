package script;

public class Aresta implements Comparable<Aresta>{

    private int v; // vertice v
    private int u; // vertice u
    private int peso; // peso da aresta

    public Aresta(int v, int u, int peso) {
        this.v = v+1;
        this.u = u+1;
        this.peso = peso;
    }
    
    public Aresta(Aresta a){
        this.v = a.v;
        this.u = a.u;
        this.peso = a.peso;
    }
    
    // Função para comparar os vertices de arestas em busca de vertices ==
    // Conta as repetições
    public boolean compara(Aresta a) {
        if(v == a.v|| v == a.u)
            if(u == a.v || u == a.u)
                if(a.v == v|| a.v == u)
                    if(a.u == v || a.u == u)
                        return true;
        return false;
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }

    public int getU() {
        return u;
    }

    public void setU(int u) {
        this.u = u;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return v+" - "+u+" Peso: "+peso+"\n";
    }

    @Override
    public int compareTo(Aresta a) {
        if(peso > a.peso)
            return 1;
        else if(peso < a.peso)
            return 1;
        return 0;
    }

}
