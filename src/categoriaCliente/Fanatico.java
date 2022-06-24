package categoriaCliente;

import java.io.Serializable;
import java.time.LocalDate;

import carrinho.Carrinho;
import jogos.Jogo;
import lojal.XulambGames;

public class Fanatico implements clienteCategorizavel, Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 6359007403293069314L;
public static double desconto=0.7;

	@Override
	public double finalizarCompra(Carrinho carrinhoAtual, XulambGames referencia) throws Exception {
		if(carrinhoAtual!=null) {
			 for(Jogo item : carrinhoAtual.getJogos()){
				 item.addVendas();
				 referencia.addNumeroTotalVendas(item.getNumeroVendas());
			 }
			double precoTotal=carrinhoAtual.getPrecoTotal();
			LocalDate dataAtual = LocalDate.now();
			carrinhoAtual.setDataDeCompra(dataAtual);
			return precoTotal *desconto;
		}
		else {
			throw new Exception("carrinho não foi criado ainda");
		}
	}
	public String toString() {
		return "Fanatico";
	}
}
