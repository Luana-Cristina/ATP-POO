package supermercado;

public final class Guloseimas extends Produtos {
    private static final long serialVersionUID = 1L;

    public Guloseimas(String nome, int quantidade, String descricao) {
        super(nome, quantidade, descricao);
        this.categoria = "Guloseima";
    }    

    @Override
    public String tipoProd() {
        return "Balas, bombons, balas de gelatina,\n chocolate, pirulitos, paçoca, suspiros etc.";
    }
    
}
