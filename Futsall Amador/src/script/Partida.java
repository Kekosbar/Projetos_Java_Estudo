package script;

public class Partida {

    private int timeLocal;
    private int timeVisitante;

    public Partida(int timeLocal, int timeVisitante) {
        this.timeLocal = timeLocal;
        this.timeVisitante = timeVisitante;
    }

    public boolean compara(Partida p){
        return this.timeLocal == p.timeLocal && 
               this.timeVisitante == p.timeVisitante;
    }
    
    public int getTimeLocal() {
        return timeLocal;
    }

    public void setTimeLocal(int timeLocal) {
        this.timeLocal = timeLocal;
    }

    public int getTimeVisitante() {
        return timeVisitante;
    }

    public void setTimeVisitante(int timeVisitante) {
        this.timeVisitante = timeVisitante;
    }

    @Override
    public String toString() {
        return "Partida{" + "timeLocal=" + timeLocal + ", timeVisitante=" + timeVisitante + '}';
    }
    
}
