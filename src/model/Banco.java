package model;

class Banco {
    private int saldo;

    public Banco() { this.saldo = 200000; }

    public void creditar(int valor) { saldo += valor; }
    public void debitar(int valor) { saldo -= valor; }
    public int getSaldo() { return saldo; }
}
