package script;

public class Agente {

    
    public void start(){
        System.out.println(quadro);
        No raiz = new No(quadro.getMatriz(), null, (byte) -1);
        
        byte numIni = 8;
        byte linIni = 2;
        byte colIni = 2;
        raiz = processa(raiz, numIni, linIni, colIni);
        
        numIni = 7;
        linIni = 2;
        colIni = 1;
        raiz = processa(raiz, numIni, linIni, colIni);
        
        numIni = 6;
        linIni = 2;
        colIni = 0;
        raiz = processa(raiz, numIni, linIni, colIni);
        Quadro.LIMITE_LINHA--;
        
        numIni = 5;
        linIni = 1;
        colIni = 2;
        raiz = processa(raiz, numIni, linIni, colIni);
        
        numIni = 4;
        linIni = 1;
        colIni = 1;
        raiz = processa(raiz, numIni, linIni, colIni);
        
        numIni = 0;
        linIni = 0;
        colIni = 0;
        raiz = processa(raiz, numIni, linIni, colIni);
        
    }
    
    private No processa(No raiz, byte numIni, byte linIni, byte colIni){
        if(verificaObjetivo(raiz.getQuadro(), numIni, linIni, colIni))
            return raiz;
        
        raiz.setNoPai(null);
        Fila fila = new Fila();
        fila.add(raiz);
        No no;
        do{
            no = fila.remove();
            expandeNo(no);
            addFilhosNaFila(no, fila);
        }while(! (verificaObjetivo(no.getQuadro(), numIni, linIni, colIni)));
        
        //System.out.println(no.getMatriz());
        passosAoObjetivo(no);
        
        return no;
    }
    
    private void expandeNo(No no){
        Quadro quadro = no.getQuadro();
        Quadro quadroResult;
        // ESQUERDA
        if(No.DIREITA != no.getPassoAnterior()){
            quadroResult = Quadro.simula_esquerda(quadro);
            no.setESQUERDA(quadroResult);
        }
        // DIREITA
        if(No.ESQUERDA != no.getPassoAnterior()){
            quadroResult = Quadro.simula_direita(quadro);
            no.setDIREITA(quadroResult);
        }
        // CIMA
        if(No.BAIXO != no.getPassoAnterior()){
            quadroResult = Quadro.simula_cima(quadro);
            no.setCIMAA(quadroResult);
        }
        // BAIXO
        if(No.CIMA != no.getPassoAnterior()){
            quadroResult = Quadro.simula_baixo(quadro);
            no.setBAIXO(quadroResult);
        }
    }
    
    private void addFilhosNaFila(No no, Fila fila){
        if(no.getESQUERDA() != null) fila.add(no.getESQUERDA());
        if(no.getDIREITA() != null) fila.add(no.getDIREITA());
        if(no.getCIMA() != null) fila.add(no.getCIMA());
        if(no.getBAIXO() != null) fila.add(no.getBAIXO());
    }
    
    private boolean verificaObjetivo(Quadro quadro, byte numIni, byte linIni, byte colIni){
        byte mat[][] = matriz.getMatriz();
        byte num = numIni;
        for(byte i=linIni; i<Quadro.LIMITE_LINHA; i++){
            for(byte j=colIni; j<Quadro.LIMITE_COLUNA; j++){
                if(mat[i][j] != num)
                    return false;
                num++;
            }
            colIni = 0;
        }
        return true;
    }
    
    private void passosAoObjetivo(No no){
        Stack pilha = new Stack<No>();
        while(no != null){
            pilha.push(no);
            no = no.getNoPai();
        }
        while(!pilha.empty()){
            No tipoNo = (No) pilha.pop();
            System.out.println(tipoNo.getMatriz());
        }
    }
    
}
