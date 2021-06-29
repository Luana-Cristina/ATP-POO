package supermercado;

import java.io.Serializable;


public abstract class Produtos implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private String nome;
    private int quantidade;
    private String descricao;
    protected String tipo;
    
    public abstract String formaPag();

    public Produtos(String nome, int quantidade, String descricao) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao (String descricao) {
        this.descricao = descricao;
    }


    @Override
    public String toString() {
        String produto = "";
        produto += "Nome: " + this.nome + "\n";        
        produto += "Quantidade: " + this.quantidade + "\n";
        produto += "Descrição: " + this.descricao + "\n";
        produto += "Tipo: " + this.tipo + "\n";
        produto += "Forma de Pagamento: " + formaPag() + "\n";
        return produto;
    }
        
}
