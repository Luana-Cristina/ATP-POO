package supermercado;

public final class Guloseimas extends Produtos {
    private static final long serialVersionUID = 1L;

    public Guloseimas(String nome, int quantidade, String descricao) {
        super(nome, quantidade, descricao);
        this.tipo = "Guloseimas";
    }

    @Override
    public String formaPag() {
        return "Guloseimas pagamento em dinheiro.";
    }
    
}

