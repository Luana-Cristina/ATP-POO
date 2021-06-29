package supermercado;

public class Bebidas extends Produtos {
    
    private static final long serialVersionUID = 1L;

    public Bebidas(String nome, int quantidade, String descricao) {
        super(nome, quantidade, descricao);
        this.tipo = "Bebidas";
    }

    @Override
    public String formaPag() {
        return "Bebidas pagamento em cartão";
    }
        
}
