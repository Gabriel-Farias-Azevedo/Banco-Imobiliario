package model;

import java.util.*;

class Tabuleiro {
    private List<Propriedade> propriedades;

    public Tabuleiro() {
        propriedades = new ArrayList<>();
        // Exemplo de casas iniciais
        propriedades.add(new Propriedade("Av. Paulista", 200));
        propriedades.add(new Propriedade("Copacabana", 180));
        propriedades.add(new Propriedade("Ipanema", 220));
        propriedades.add(new Propriedade("Vá para prisão", 0, true));
    }

    public boolean moverPiao(Jogador jogador, int passos) {
        return jogador.getPiao().mover(passos, propriedades.size());
    }

    public Propriedade getPropriedadeNaPosicao(int pos) {
        if (pos >= 0 && pos < propriedades.size()) {
            return propriedades.get(pos);
        }
        return null;
    }

    public boolean comprarPropriedade(Jogador jogador, Banco banco) {
        Propriedade prop = getPropriedadeNaPosicao(jogador.getPiao().getPosicao());
        if (prop != null && !prop.temDono() && !prop.isEspecial() && jogador.getSaldo() >= prop.getPreco()) {
            jogador.debitar(prop.getPreco());
            banco.creditar(prop.getPreco());
            prop.setDono(jogador);
            jogador.getPropriedades().add(prop);
            return true;
        }
        return false;
    }

    // Verifica efeitos: aluguel e prisão
    public void verificarEfeitos(Jogador jogador, Jogo jogo, int[] dados) {
        Propriedade prop = getPropriedadeNaPosicao(jogador.getPiao().getPosicao());

        if (prop == null) return;

        // Prisão
        if (prop.isEspecial() && prop.getNome().contains("prisão")) {
            jogo.getPrisao().prender(jogador);
            return;
        }

        // Aluguel
        if (prop.temDono() && prop.getDono() != jogador) {
            int aluguel = prop.calcularAluguel();
            jogador.debitar(aluguel);
            prop.getDono().creditar(aluguel);
            jogo.verificarFalencia(jogador);
        }

        // Saída da prisão se tirar doubles
        if (jogo.getPrisao().estaPreso(jogador) && dados[0] == dados[1]) {
            jogo.getPrisao().soltar(jogador);
        }
    }
}
