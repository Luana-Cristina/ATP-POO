package supermercado;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SuperMercado1 {

	private ArrayList<Produtos> produtos;

	public SuperMercado1(){
            this.produtos = new ArrayList<Produtos>();
	}

	public void adicionaProdutos(Produtos prod) {
            this.produtos.add(prod);
	}

	public void listarProdutos() {
            for(Produtos prod:produtos) {
		System.out.println(prod.toString());
            }
            System.out.println("Total = " + this.produtos.size() + " produtos com sucesso!\n");
	}
	
	public void excluirProdutos(Produtos prod){
            if (this.produtos.contains(prod)) {
            	this.produtos.remove(prod);
            	System.out.println("[Produto " + prod.toString() + "excluido com sucesso!]\n");
            }else{
                System.out.println("Produto inexistente!\n");
            }
	}

	public void excluirProdutos(){
            produtos.clear();
            System.out.println("Produtos excluidos com sucesso!\n");
	}
        
	public void gravarProdutos(){
            ObjectOutputStream outputStream = null;
            try {
		outputStream = new ObjectOutputStream (new FileOutputStream("mercado.txt"));
                    for(Produtos prod:produtos) {
			outputStream.writeObject(prod);
                    }
		}catch(FileNotFoundException ex){
			ex.printStackTrace();
		}catch (IOException ex) {
			ex.printStackTrace();
		}finally{
                    try {
                        if(outputStream != null ) {
                            outputStream.flush();
                            outputStream.close();
			}
			}catch(IOException ex){
				ex.printStackTrace();
			}
		}
	}
	public void recuperarProdutos(){
            ObjectInputStream inputStream = null;
            try{
		inputStream	= new ObjectInputStream (new FileInputStream ("mercado.txt"));
		Object obj = null;
		while((obj = inputStream.readObject ()) != null) {
                    if (obj instanceof Guloseimas){ 
                        this.produtos.add((Guloseimas)obj);
                    }else if(obj instanceof Bebidas){  
			this.produtos.add((Bebidas)obj);
                    }
		}
		}catch (EOFException ex){  
			System.out.println ("Fim do arquivo");
		}catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}catch (IOException ex) {
			ex.printStackTrace();
		}finally{
			try{
                            if(inputStream != null ){
                                inputStream.close();
				System.out.println("Produtos recuperados com sucesso!\n");
                            }
			}catch(IOException ex){
                            ex.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		SuperMercado1 merc  = new SuperMercado1();

		Guloseimas fini = new Guloseimas ("Fini", 1, "Azedinha");
		Guloseimas doritos = new Guloseimas ("Doritos", 2, "Pequeno");
		Bebidas  suco = new Bebidas("Prats", 2, "Laranja");
		Bebidas  coca = new Bebidas("Coca-Cola", 1, "Zero");
		merc.adicionaProdutos(fini);
		merc.adicionaProdutos(doritos);
		merc.adicionaProdutos(suco);
		merc.adicionaProdutos(coca);
		merc.listarProdutos();
		merc.gravarProdutos();
		merc.excluirProdutos(doritos);
		merc.listarProdutos();
		merc.excluirProdutos();
		merc.listarProdutos();
		merc.recuperarProdutos();
		merc.listarProdutos();
	}

}
