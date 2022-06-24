package clientes;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Map;

import carrinho.Carrinho;
import categoriaCliente.clienteCategorizavel;
import jogos.Jogo;
import lojal.XulambGames;

public class Cliente implements Serializable {
/**
	 * 
	 */
	
	//Atributos da classe;
	private static final long serialVersionUID = -7805228302506628227L;
private String nome;
private String nomeUsuario;
private String senha;
private clienteCategorizavel tipoCliente;
private Map<String,clienteCategorizavel> estadosClientes;
private LinkedList<Carrinho> historicoDeCompras;
private Carrinho carrinhoAtual;
private XulambGames referencia;
	
//construtor da classe.
	public Cliente(String nome, String nomeUsuario, String senha, XulambGames referencia, clienteCategorizavel categoria,Map<String,clienteCategorizavel> estadosClientes) {
		this.estadosClientes=estadosClientes;
		this.nome=nome;
		this.nomeUsuario=nomeUsuario;
		this.senha=senha;
		this.tipoCliente=categoria;
		this.historicoDeCompras=new LinkedList<Carrinho>();	
		this.setReferencia(referencia);
	}
	
	//construtor da classe.
//Metodos gets/sets
public XulambGames getReferencia() {
		return referencia;
}

public void setReferencia(XulambGames referencia) {
		this.referencia = referencia;
}
public void setNome(String nome) {
	this.nome=nome;
}
public void setNomeUsuario(String nomeUsuario) {
	this.nomeUsuario=nomeUsuario;
}
public void setSenha(String senha) {
	this.senha=senha;
}
public void setTipoCLiente(String tipoCliente) {
	this.tipoCliente=this.estadosClientes.get(tipoCliente);
}
public void limparCarrinhoAtual() {
	this.carrinhoAtual = new Carrinho(); ;
}
public String getNome() {
	return this.nome;
}
public String getNomeUsuario() {
	return this.nomeUsuario;
}
public String getSenha() {
	return this.senha;
}
public clienteCategorizavel getTipoCliente() {
	return this.tipoCliente;
}
public LinkedList<Carrinho> getHistoricoDeCompras(){
	return this.historicoDeCompras;
}
public Carrinho getCarrinhoAtual() {
	return this.carrinhoAtual;
}

public void setHistoricoDeCompras(LinkedList<Carrinho> historicoDeCompras) {
	this.historicoDeCompras = historicoDeCompras;
}
/*Metodo para iniciar a compra. Ele verifica se jï¿½ existe alguma compra inicializada, se nï¿½o,
 ele inicializa um carrinho e o coloca como compra atual
*/

public void iniciarCompra()throws Exception {
	if(this.carrinhoAtual==null) {
		this.carrinhoAtual=new Carrinho();
	}
	else {
		throw new Exception("Carrinho jï¿½ existente");
	}
}
/*Metodo que adiciona um jogo para o carrinho, ele verifica se existe alguma compra inicializada
 * se sim, ele adiciona um jogo para o carrinho da tal
 */
public void addJogo(Jogo novoJogo)throws Exception {
	if(this.carrinhoAtual != null) {
		this.carrinhoAtual.addJogo(novoJogo);
	}
	else {
		throw new Exception("Compra nï¿½o inicializada");
	}
}

public void removeJogo(String jogo) {
	Jogo removido = null;
	for(Jogo jg : this.carrinhoAtual.getJogos()) {
		if(jg.getNome().equals(jogo)) {
			removido = jg;
		}
	}
	this.getCarrinhoAtual().getJogos().remove(removido);
	
}
/* Metodo que finaliza a compra.Ele primeiro verifica se existe alguma compra pendente, se sim
 * ele adiciona o contador de vendas de seus respectivos jogos, atualiza o contador de vendas da 
 * loja, calcula o preï¿½o total, salva o carrinho no historico com a data de finalizaï¿½ï¿½o e retorna
 * o preï¿½o total
 */
public double finalizarCompra()throws Exception {
	double precoTotal=this.tipoCliente.finalizarCompra(carrinhoAtual, referencia);
		this.historicoDeCompras.add(this.carrinhoAtual);
		limparCarrinhoAtual();
		return precoTotal;
}
@Override
public String toString() {
	
	StringBuilder espacos = new StringBuilder("");
	while(this.getNomeUsuario().length() + espacos.length() + 6 != 37) {
		espacos.append(" ");
	}
	
	return (this.getNomeUsuario() + espacos + this.tipoCliente);
}

public String relatorio() {
	LinkedList<Carrinho> historico = this.getHistoricoDeCompras();
	StringBuilder relatorio = new StringBuilder("");
	relatorio.append("=== Histórico de "+ this.getNome() + " ===\n\n");
	for(Carrinho carrinho : historico) {
		relatorio.append(carrinho + "\n");
	}
	
	return relatorio.toString();
}
}
