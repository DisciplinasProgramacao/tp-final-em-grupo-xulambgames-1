package categoriasjogos;

import java.io.Serializable;

public class Premium implements JogoCategorizavel,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3041764040437825276L;
	@Override
	public double calcularPreco(double precoOriginal, double desconto){
		return precoOriginal;
	}

	@Override
	public double getPontos() {
		return 2;
	}
	@Override
	public boolean verificarCategoria(double desconto) {
		if(desconto==1)
			return true;
					return false;
	}
	public int hashCode() {
		return Integer.parseInt("premium");
	}
	public String toString() {
		return "Premium";
	}
	public Premium() {}

}
