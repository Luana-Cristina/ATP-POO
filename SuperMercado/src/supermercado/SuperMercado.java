package supermercado;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class SuperMercado{
	private ArrayList<Produtos> produtos;

	public SuperMercado() {
            this.produtos = new ArrayList<Produtos>();
	}
        
	public String[] leValores (String [] dadosIn){
            
            String [] dadosOut = new String [dadosIn.length];

            for (int i = 0; i < dadosIn.length; i++)
            	dadosOut[i] = JOptionPane.showInputDialog  ("Entre com " + dadosIn[i]+ ": ");

            return dadosOut;
	}

	public Bebidas leBebidas(){

            String [] valores= new String [3];
            String [] nomeVal = {"Nome", "Quantidade", "Descrição"};
            valores = leValores (nomeVal);

            int quantidade = this.retornaInteiro(valores[1]);

            Bebidas bebidas = new Bebidas(valores[0], quantidade, valores[2]);
            return bebidas;
	}

	public Guloseimas leGuloseimas(){

            String [] valores = new String [3];
            String [] nomeVal = {"Nome", "Quantidade", "Descrição"};                
            valores = leValores(nomeVal);

            int quantidade = this.retornaInteiro(valores[1]);

            Guloseimas guloseimas = new Guloseimas(valores[0],quantidade, valores[2]);
            return guloseimas;
	}
        
        public Laticinios leLaticinios(){
            
            String [] valores = new String [3];
            String [] nomeVal = {"Nome", "Quantidade", "Descrição"};                
            valores = leValores(nomeVal);

            int quantidade = this.retornaInteiro(valores[1]);

            Laticinios laticinios = new Laticinios(valores[0],quantidade, valores[2]);
            return laticinios;
        }
        
	private boolean intValido(String s) {
            try {
                Integer.parseInt(s); 
                    return true;
            } catch (NumberFormatException e) { 
            	return false;
            }
	}
	public int retornaInteiro(String entrada) { 
            int numInt;

            while (!this.intValido(entrada)) {
                entrada = JOptionPane.showInputDialog(null, "Valor incorreto!\n\nDigite um número inteiro.");
            }
            return Integer.parseInt(entrada);
	}

	public void salvaProdutos(ArrayList<Produtos> produtos){
            ObjectOutputStream outputStream = null;
                try{
                    outputStream = new ObjectOutputStream 
                        (new FileOutputStream("mercado.txt"));
			for (int i=0; i < produtos.size(); i++)
                            outputStream.writeObject(produtos.get(i));
		} catch (FileNotFoundException ex){
			JOptionPane.showMessageDialog(null,"Impossível criar arquivo!");
		} catch (IOException ex) {
		} finally{  
			try {
                            if (outputStream != null) {
                                outputStream.flush();
				outputStream.close();
                            }
			}catch (IOException ex){
			}
		}
	}

	@SuppressWarnings("finally")
	public ArrayList<Produtos> recuperaProdutos(){
            ArrayList<Produtos> produtosTemp = new ArrayList<Produtos>();

            ObjectInputStream inputStream = null;

            try{	
                inputStream = new ObjectInputStream
                    (new FileInputStream("mercado.txt"));
		Object obj = null;
		while ((obj = inputStream.readObject()) != null){
                    if (obj instanceof Produtos){
			produtosTemp.add((Produtos) obj);
                    }   
		}          
		}catch (EOFException ex){ 
			System.out.println("Fim de arquivo.");
		} catch (ClassNotFoundException ex){
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null,"Arquivo com produtos não existe!");
		} catch (IOException ex) {
		} finally {  
			try {
                            if (inputStream != null) {
                                inputStream.close();
                            }
			}catch(final IOException ex) {
			}
			return produtosTemp;
		}
	}

	public void menuProdutos(){

            String menu = "";
            String entrada;
            int    opc1, opc2;

            do{
		menu = "Controle nosso mercadinho\n" +
			"Opções:\n" + 
			"1. Inserir Produtos\n" +
                        "2. Exibir Produtos\n" +
			"3. Limpar Produtos\n" +
			"4. Gravar Produtos\n" +
			"5. Recuperar Produtos\n" +
			"9. Sair";
			entrada = JOptionPane.showInputDialog (menu + "\n\n");
			opc1 = this.retornaInteiro(entrada);

			switch (opc1) {
			case 1:
				menu = "Entrada de Produtos\n" +
					"Opções:\n" + 
					"1. Bebidas\n" +
					"2. Guloseimas\n" +
                                        "3. Laticínios\n";

				entrada = JOptionPane.showInputDialog (menu + "\n\n");
				opc2 = this.retornaInteiro(entrada);

				switch (opc2){
				case 1: produtos.add((Produtos)leBebidas());
				break;
				case 2: produtos.add((Produtos)leGuloseimas());
				break;
                                case 3: produtos.add((Produtos)leLaticinios());
                                break;
				default: 
					JOptionPane.showMessageDialog(null,"Produto para entrada não escolhido!");
				}

				break;
			case 2:
				if (produtos.isEmpty()) {
					JOptionPane.showMessageDialog(null,"Entre com os produtos primeiramente");
					break;
				}
				String dados = "";
				for (int i=0; i < produtos.size(); i++)	{
                                    dados += produtos.get(i).toString() + "_________________________________\n";
				}
				JOptionPane.showMessageDialog(null,dados);
				break;
			case 3:
				if (produtos.isEmpty()) {
                                    JOptionPane.showMessageDialog(null,"Entre com os produtos primeiramente");
                                    break;
				}
				produtos.clear();
				JOptionPane.showMessageDialog(null,"Dados LIMPOS com sucesso!");
				break;
			case 4:
				if (produtos.isEmpty()) {
                                    JOptionPane.showMessageDialog(null,"Entre com os produtos primeiramente");
                                    break;
				}
				salvaProdutos(produtos);
				JOptionPane.showMessageDialog(null,"Dados SALVOS com sucesso!");
				break;
			case 5: 
				produtos = recuperaProdutos();
				if (produtos.isEmpty()) {
                                    JOptionPane.showMessageDialog(null,"Sem dados para apresentar.");
                                    break;
				}
				JOptionPane.showMessageDialog(null,"Dados RECUPERADOS com sucesso!");
				break;
			case 9:
				JOptionPane.showMessageDialog(null,"Fim do aplicativo Mercadinho");
				break;
			}
		}while(opc1 != 9);
	}

	public static void main (String [] args){

		SuperMercado merc = new SuperMercado();
		merc.menuProdutos();

	}
}
