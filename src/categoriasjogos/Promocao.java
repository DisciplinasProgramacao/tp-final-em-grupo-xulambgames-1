package categoriasjogos;

import java.io.Serializable;

public class Promocao implements JogoCategorizavel,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5741575360858303865L;
	@Override
	public double calcularPreco(double precoOriginal, double desconto){
			return precoOriginal*desconto;
	}

	@Override
	public double getPontos() {
		return 0.01;
	}
	@Override
	public boolean verificarCategoria(double desconto) {
		if(desconto>=0.5 && desconto <0.7)
			return true;
					return false;
	}
	@Override
	public int hashCode() {
		return Integer.parseInt("promocao");
	}
	public String toString() {
		return "Promocao";
	}
public Promocao() {}
}
