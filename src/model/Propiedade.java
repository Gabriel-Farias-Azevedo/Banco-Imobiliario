package model;

class Propriedade {
    private String nome;
    private int preco;
    private Jogador dono;
    private int casas;
    private boolean hotel;
    private boolean especial;

    public Propriedade(String nome, int preco) {
        this(nome, preco, false);
    }

    public Propriedade(String nome, int preco, boolean especial) {
        this.nome = nome;
        this.preco = preco;
        this.especial = especial;
        this.casas = 0;
        this.hotel = false;
    }

    public boolean temDono() { return dono != null; }
    public void setDono(Jogador jogador) { this.dono = jogador; }
    public Jogador getDono() { return dono; }
    public int getPreco() { return preco; }
    public String getNome() { return nome; }
    public boolean isEspecial() { return especial; }

    // Construir casa/hotel
    public boolean construirCasa() {
        if (casas < 4 && !hotel) {
            casas++;
            return true;
        } else if (casas == 4 && !hotel) {
            hotel = true;
            return true;
        }
        return false;
    }

    public int calcularAluguel() {
        if (hotel) return 500;
        if (casas > 0) return casas * 100;
        return 0;
    }
}
