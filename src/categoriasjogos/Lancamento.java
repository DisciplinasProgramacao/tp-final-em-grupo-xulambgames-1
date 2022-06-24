package categoriasjogos;

import java.io.Serializable;

public class Lancamento implements JogoCategorizavel,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3041764040437825276L;
	@Override
	public double calcularPreco(double precoOriginal, double desconto){
		return precoOriginal * desconto;
	}

	@Override
	public double getPontos() {
		return 3;
	}
	@Override
	public boolean verificarCategoria(double desconto) {
		if(desconto==1.1)
			return true;
					return false;
	}
	public int hashCode() {
		return Integer.parseInt("lancamento");
	}
	public String toString() {
		return "Lancamento";
	}
	public Lancamento() {}
}