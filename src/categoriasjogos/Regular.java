package categoriasjogos;

import java.io.Serializable;

public class Regular implements JogoCategorizavel,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8580726288800815316L;
	@Override
	public double calcularPreco(double precoOriginal, double desconto){
		
			return precoOriginal*desconto;
	}
	@Override
	public double getPontos() {
		return 1;
	}
	@Override
	public boolean verificarCategoria(double desconto) {
		if(desconto>=0.7 && desconto < 1)
			return true;
					return false;
	}
	@Override
	public int hashCode() {
		return Integer.parseInt("regular");
	}
	public String toString() {
		return "regular";
	}
	public Regular() {}

}
