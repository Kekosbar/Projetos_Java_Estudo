package script;

import java.util.ArrayList;
import java.util.Iterator;

public class Fila {

    private ArrayList<No> fila = new ArrayList<>();
    
    public void add(No no){
        fila.add(no);
    }
    
    public No remove(){
        return fila.remove(0);
    }
    
    public int tamanho(){
        return fila.size();
    }

    @Override
    public String toString() {
        String s = "";
        Iterator it = fila.iterator();
        while(it.hasNext())
            s += it.next()+"\n";
        return s;
    }
    
}
