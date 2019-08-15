package script;

import java.util.Random;

public class Quadro {

    private static byte ordem;
    private byte[][] mat;
    private byte pos_X; // Posição do 0 in X e Y
    private byte pos_Y;
    // Limites para mover
    private static byte limite_X;
    private static byte limite_Y;

    public Quadro(byte ordem) {
        Quadro.ordem = ordem;
        this.mat = new byte[ordem][ordem];
        limite_X = limite_Y = (byte) (ordem-1);
        inicia();
        embaralhar();
    }
    
    private Quadro(Quadro quadro){
        this.mat = new byte[ordem][ordem];
        for(int i=0; i<ordem; i++){
            for(int j=0; j<ordem; j++){
                mat[i][j] = quadro.mat[i][j];
            }
        }
        pos_X = quadro.pos_X;
        pos_Y = quadro.pos_Y;
    }
    
    private void inicia(){
        byte num = (byte) (ordem * ordem);
        for(int i=0; i<ordem; i++){
            for(int j=0; j<ordem; j++){
                mat[i][j] = --num;
                if(num == 0){
                    pos_X = (byte) (i-1);
                    pos_Y = (byte) (j-1);
                }
            }
        }
    }
    
    private void embaralhar(){
        Random rand = new Random();
        for(int i=0; i<100; i++){
            switch(rand.nextInt(4)){
                case 0:
                    moverCima(); break;
                case 1:
                    moverBaixo(); break;
                case 2:
                    moverEsquerda(); break;
                case 3:
                    moverDireita(); break;
            }
        }
    }
    
    /*========================================
    ----        METODOS STATICOS        ------
    ==========================================*/
    
    public static Quadro simula_cima(Quadro quadro){
        Quadro quadroResult = new Quadro(quadro);
        if(quadroResult.moverCima()){
            return quadroResult;
        }else
            return null;
    }
    
    public static Quadro simula_baixo(Quadro quadro){
        Quadro quadroResult = new Quadro(quadro);
        if(quadroResult.moverCima()){
            return quadroResult;
        }else
            return null;
    }
    
    public static Quadro simula_direita(Quadro quadro){
        Quadro quadroResult = new Quadro(quadro);
        if(quadroResult.moverCima()){
            return quadroResult;
        }else
            return null;
    } 
    
    public static Quadro simula_esquerda(Quadro quadro){
        Quadro quadroResult = new Quadro(quadro);
        if(quadroResult.moverCima()){
            return quadroResult;
        }else
            return null;
    }
    
    /*========================================
    ------------------------------------------
    ==========================================*/
    
    public boolean moverCima(){
        if(pos_X > 0){
            byte x = (byte) (pos_X-1);
            byte y = pos_Y;
            troca(x, y);
            return true;
        }else 
            return false;
    }
    
    public boolean moverBaixo(){
        if(pos_X != limite_X){
            byte x = (byte) (pos_X+1);
            byte y = pos_Y;
            troca(x, y);
            return true;
        }else 
            return false;
    }
    
    public boolean moverEsquerda(){
        if(pos_Y > 0){
            byte x = pos_X;
            byte y = (byte) (pos_Y-1);
            troca(x, y);
            return true;
        }else 
            return false;
    }
    
    public boolean moverDireita(){
        if(pos_Y != limite_Y){
            byte x = pos_X;
            byte y = (byte) (pos_Y+1);
            troca(x, y);
            return true;
        }else 
            return false;
    }
    
    // Troca o 0 de posição
    private void troca(byte x, byte y){
        System.out.println("\nX: "+x+" Y: "+y);
        byte num = mat[x][y];
        mat[x][y] = mat[pos_X][pos_Y];
        mat[pos_X][pos_Y] = num;
        pos_X = x;
        pos_Y = y;
    }

    @Override
    public String toString() {
        String s = "";
        s += "\n----------------\n| ";
        for(int i=0; i<ordem; i++){
            for(int j=0; j<ordem; j++){
                if(mat[i][j] == 0)
                    s += "  | ";
                else
                    s += mat[i][j] + " | ";
            }
            s += "\n----------------\n| ";
        }
        return s;
    }
    
    
}
