package categoriaCliente;

import java.io.Serializable;
import java.time.LocalDate;

import carrinho.Carrinho;
import jogos.Jogo;
import lojal.XulambGames;

public class Cadastrado implements clienteCategorizavel, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5041395047569678590L;

	@Override
	public double finalizarCompra(Carrinho carrinhoAtual,XulambGames referencia) throws Exception {
		if(carrinhoAtual!=null) {
				 for(Jogo item : carrinhoAtual.getJogos()){
					 item.addVendas();
					referencia.addNumeroTotalVendas(item.getNumeroVendas());
				 }
				double precoTotal=carrinhoAtual.getPrecoTotal();
				LocalDate dataAtual = LocalDate.now();
				carrinhoAtual.setDataDeCompra(dataAtual);
				
				return precoTotal;
			}
			else {
				throw new Exception("carrinho n�o foi criado ainda");
			}
		}
	public String toString() {
		return "Cadastrado";
	}
}
