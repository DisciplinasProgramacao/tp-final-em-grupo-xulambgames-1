package categoriaCliente;

import carrinho.Carrinho;
import lojal.XulambGames;

public interface clienteCategorizavel {
double finalizarCompra(Carrinho carrinhoAtual, XulambGames referencia) throws Exception;
}
