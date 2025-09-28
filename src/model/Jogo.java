package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Jogo {
    private List<Jogador> jogadores;
    private Tabuleiro tabuleiro;
    private Banco banco;
    private Prisao prisao;
    private int jogadorDaVez;

    public Jogo(int numJogadores) {
        this.jogadores = new ArrayList<>();
        this.tabuleiro = new Tabuleiro();
        this.banco = new Banco();
        this.prisao = new Prisao();
        this.jogadorDaVez = 0;

        for (int i = 0; i < numJogadores; i++) {
            jogadores.add(new Jogador("Jogador " + (i + 1)));
        }
    }

    public int[] lancarDados() {
        return Dado.rolar();
    }

    public boolean deslocarPiao(int[] valores) {
        Jogador atual = jogadores.get(jogadorDaVez);
        int soma = Arrays.stream(valores).sum();
        boolean moveu = tabuleiro.moverPiao(atual, soma);

        // Efeitos: aluguel, prisão ...
        tabuleiro.verificarEfeitos(atual, this, valores);
        return moveu;
    }

    public boolean comprarPropriedade() {
        Jogador atual = jogadores.get(jogadorDaVez);
        return tabuleiro.comprarPropriedade(atual, banco);
    }

    public boolean construirCasa() {
        Jogador atual = jogadores.get(jogadorDaVez);
        Propriedade prop = tabuleiro.getPropriedadeNaPosicao(atual.getPiao().getPosicao());
        if (prop != null && prop.getDono() == atual) {
            return prop.construirCasa();
        }
        return false;
    }

    public void proximoJogador() {
        jogadorDaVez = (jogadorDaVez + 1) % jogadores.size();
    }

    public void verificarFalencia(Jogador jogador) {
        if (jogador.getSaldo() < 0) {
            jogadores.remove(jogador);
            for (Propriedade p : jogador.getPropriedades()) {
                p.setDono(null);
            }
        }
    }

    public Prisao getPrisao() { return prisao; }
    public Jogador getJogadorDaVez() { return jogadores.get(jogadorDaVez); }
}
