
package supermercado;

public class Laticinios extends Produtos{

    public Laticinios(String nome, int quantidade, String descricao) {
        super(nome, quantidade, descricao);
        this.categoria = "Laticínios";
    }

    @Override
    public String tipoProd() {
        return "Queijos, iogurtes, leites, requeijão,\n creme de leite etc.";

    }
    
}
