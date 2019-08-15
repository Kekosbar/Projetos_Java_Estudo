package script;

public class No {
    
    public static final byte ESQUERDA = 0;
    public static final byte DIREITA = 1;
    public static final byte CIMA = 2;
    public static final byte BAIXO = 3;
    
    private Quadro quadro;
    private No direita;
    private No esquerda;
    private No cima;
    private No baixo;
    private No noPai;
    private byte passoAnterior;
    //Construtor

    public No(Quadro quadro, No anterior, byte passoAnterior) {
        this.quadro = quadro;
        this.noPai = anterior;
        this.passoAnterior = passoAnterior;
        direita = null;
        esquerda = null;
        cima = null;
        baixo = null;
    }

    public No setESQUERDA(Quadro quadro) {
        if(quadro == null) return null;
        return esquerda = new No(quadro, this, ESQUERDA);
    }

    public No setDIREITA(Quadro quadro) {
        if(quadro == null) return null;
        return direita = new No(quadro, this, DIREITA);
    }

    public No setCIMAA(Quadro quadro) {
        if(quadro == null) return null;
        return cima = new No(quadro, this, CIMA);
    }

    public No setBAIXO(Quadro quadro) {
        if(quadro == null) return null;
        return baixo = new No(quadro, this, BAIXO);
    }

    
    public No getDIREITA() {
        return direita;
    }

    public No getESQUERDA() {
        return esquerda;
    }

    public No getCIMA() {
        return cima;
    }

    public No getBAIXO() {
        return baixo;
    }

    public Quadro getQuadro() {
        return quadro;
    }

    public void setNoPai(No noPai) {
        this.noPai = noPai;
    }

    public No getNoPai() {
        return noPai;
    }

    public byte getPassoAnterior() {
        return passoAnterior;
    }
    

}
