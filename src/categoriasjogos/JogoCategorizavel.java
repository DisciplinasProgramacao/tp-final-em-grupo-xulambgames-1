package categoriasjogos;

public interface JogoCategorizavel {
public double calcularPreco(double precoOriginal, double desconto);
public double getPontos();
public boolean verificarCategoria(double desconto);
}
