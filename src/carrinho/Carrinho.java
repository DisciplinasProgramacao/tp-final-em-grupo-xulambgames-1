package carrinho;
import java.io.Serializable;
import java.util.LinkedList;

import jogos.Jogo;

import java.time.*;

public class Carrinho implements Serializable {
	/**
	 * 
	 */
	//Aqui est]ao listado todos os atributos da classe
	
	private static final long serialVersionUID = -5389838334512037215L;
	private int totalDeJogos;
	private LinkedList<Jogo> jogos;
	private double precoTotal;
	private LocalDate dataDeCompra;
	
	// Construtor da classe, n�o precisa de nenhum parametro, apenas iniciar uma nova lista de jogos;
	public Carrinho() {
		this.jogos=new LinkedList<Jogo>();
		this.precoTotal=0;
		this.totalDeJogos=0;
	}
	//aqui est�o os gets e sets da classe
	public double getPrecoTotal(){
		return this.precoTotal;
	}
	public int getTotalDeJogos() {
		return totalDeJogos;
	}
	public LinkedList<Jogo> getJogos(){
		return this.jogos;
	}
	public LocalDate getDataDeCompra() {
		return dataDeCompra;
	}
	public void setDataDeCompra(LocalDate dataDeCompra) {
		this.dataDeCompra = dataDeCompra;
	}
	
	
	/*metodo usado para calcular o pre�o total do carrinho,
	ele caminha todo uma lista de jogos e pega o pre�o de cada um e no final aplica a regra de
	desconto. Para simplificar eu converti a regra de desconto para uma de pontua��o se tratei
	os que forem exce��o*/
	private void calcularPrecoTotal() {
		double total=0;
		double totalPontos=0;
		 for(Jogo item : this.jogos){
	            total=total+item.getPreco();
	            totalPontos=totalPontos+item.getCategoria().getPontos();
					
				}
		 
	  if(totalPontos>4) {
		  this.precoTotal=total*0.8;
	  }
	  else if(totalPontos==4) {
		  this.precoTotal=total*0.9;
	  }
	  else {
		  this.precoTotal=total;
	  }
		 }
		
	
	//metodo que adiciona um jogo para o carrinho para ser usado pelo cliente
	public void addJogo(Jogo novoJogo) {
		this.precoTotal=this.precoTotal+novoJogo.getPreco();
		this.jogos.add(novoJogo);
		calcularPrecoTotal();
		this.totalDeJogos++;
		
	}
	

	public void removeJogo(String nome) {
		Jogo JogoEncontrado = null;
		for(Jogo jogo: this.jogos) {
			if(jogo.getNome().trim().equals(nome.trim())) {
				JogoEncontrado = jogo;
			}
		}
		this.jogos.remove(JogoEncontrado);
	}
	
	public String toString() {
		StringBuilder relatorio = new StringBuilder("");
		if(this.dataDeCompra != null) {
			 relatorio.append("------- " + this.getDataDeCompra() + " -------\n");
		}
		relatorio.append("-- JOGOS --\n");
		LinkedList<Jogo> jogos = this.getJogos();
		for(Jogo jogo : jogos) {
			relatorio.append(jogo.getNome() +"\n");		
		}
		relatorio.append("\nTotal da Compra : " + this.getPrecoTotal() + "\n\n---------------------------");
		return relatorio.toString();
	}
	
	public String exibirCarrinho() {
		StringBuilder carrinho = new StringBuilder("");
		
		for(Jogo jogo : this.getJogos()) {
			carrinho.append(jogo.getNome() + "\n");
		}
		carrinho.append(".........................\n");
		carrinho.append("Total: "+this.getPrecoTotal());
		return carrinho.toString();
	}
	

}
