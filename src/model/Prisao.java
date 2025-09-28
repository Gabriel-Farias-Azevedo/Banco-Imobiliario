package model;

import java.util.HashSet;
import java.util.Set;

class Prisao {
    private Set<Jogador> presos = new HashSet<>();

    public void prender(Jogador j) { presos.add(j); }
    public void soltar(Jogador j) { presos.remove(j); }
    public boolean estaPreso(Jogador j) { return presos.contains(j); }
}
