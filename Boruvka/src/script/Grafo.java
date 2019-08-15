package script;

import java.util.Random;

public class Grafo {

    private int mat[][];
    private int nVertices = 6;
    
    public Grafo(int nVertices){
        this.nVertices = nVertices;
        iniciaMatriz();
    }

    private void iniciaMatriz(){
        Random r = new Random();
        mat = new int[nVertices][nVertices];
        for(int i=0; i<nVertices; i++)
            for(int j=0; j<nVertices; j++)
                if(i != j)
                    mat[j][i] = mat[i][j] = r.nextInt(50);
                else
                    mat[j][i] = mat[i][j] = 0;
    }

    public int[][] getMatrizAdj() {
        return mat;
    }

    public int getTotalVertices() {
        return nVertices;
    }
    
    @Override
    public String toString() {
        // Grafos muito grandes irão gerar estouro de memoria neste metodo
        // Retorna apenas matriz adjacente de grafos pequenos
        if(nVertices > 100) return "";
        String s;
        s = "\n  ";
        for(int i=0; i<nVertices; i++)
            s += "  "+i;
        s += "\n--";
        for(int i=0; i<nVertices; i++)
            s += "---";
        for(int i=0; i<nVertices; i++){
            s += "\n"+i+"| ";
            for(int j=0; j<nVertices; j++)
                s += String.format("%2d ", mat[i][j]);
        }
        return s;
    }
    
}
