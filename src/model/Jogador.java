package model;

import java.util.*;

class Jogador {
    private String nome;
    private int saldo;
    private Piao piao;
    private List<Propriedade> propriedades;

    public Jogador(String nome) {
        this.nome = nome;
        this.saldo = 4000; // saldo inicial
        this.piao = new Piao();
        this.propriedades = new ArrayList<>();
    }

    public void debitar(int valor) { saldo -= valor; }
    public void creditar(int valor) { saldo += valor; }
    public int getSaldo() { return saldo; }
    public Piao getPiao() { return piao; }
    public List<Propriedade> getPropriedades() { return propriedades; }
    public String getNome() { return nome; }
}
