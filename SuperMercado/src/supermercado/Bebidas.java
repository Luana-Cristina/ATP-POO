package supermercado;

public class Bebidas extends Produtos {
    
    private static final long serialVersionUID = 1L;

    public Bebidas(String nome, int quantidade, String descricao) {
        super(nome, quantidade, descricao);
        this.categoria = "Bebidas";
    }

    @Override
    public String tipoProd() {
        return "Refrigerantes, sucos, água de coco,\n vinho, cerveja etc.";
    }
        
}
