package model;

class Piao {
    private int posicao;

    public Piao() { this.posicao = 0; }

    public boolean mover(int passos, int limite) {
        posicao = (posicao + passos) % limite;
        return true;
    }

    public int getPosicao() { return posicao; }
}
