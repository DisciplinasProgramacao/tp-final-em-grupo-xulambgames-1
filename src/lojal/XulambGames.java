package lojal;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import categoriaCliente.Cadastrado;
import categoriaCliente.Empolgado;
import categoriaCliente.Fanatico;
import categoriaCliente.clienteCategorizavel;
import categoriasjogos.JogoCategorizavel;
import categoriasjogos.Lancamento;
import categoriasjogos.Premium;
import categoriasjogos.Promocao;
import categoriasjogos.Regular;
import clientes.Cliente;
import controladores.JogoStateControlador;
import jogos.Jogo;
public class XulambGames implements Serializable {
/**
	 * 
	 */

	private static final long serialVersionUID = -7141840469374892582L;
	//atributos da classe
private TreeSet<Jogo> tabelaDeJogos;
private LinkedList<Cliente> clientes;
private int numeroTotalVendas;
private double mediaDeVendas;
private Map<String,clienteCategorizavel> estadosClientes;
private String codigo;
private JogoStateControlador controlador;

//metodos get e sets
public double getMediaDeVendas() {
	return mediaDeVendas;
}

public int getNumeroTotalVendas() {
	return numeroTotalVendas;
}

public void addNumeroTotalVendas(int numeroTotalVendas) {
	this.numeroTotalVendas = this.numeroTotalVendas+numeroTotalVendas;
}
public Set<Jogo> getTabelaDeJogos() {
	return tabelaDeJogos;
}
public void setTabelaDeJogos(TreeSet<Jogo> tabelaDeJogos) {
	this.tabelaDeJogos = tabelaDeJogos;
}

public String getCodigo() {
	return codigo;
}
public void setCodigo(String codigo) {
	this.codigo = codigo;
}
public void setMediaDeVendas() {
	double total=0;
	for(Jogo item : this.tabelaDeJogos){
	   total=total+item.getPreco();
	}
	 this.mediaDeVendas=total/this.numeroTotalVendas;
}
//metodo para adicionar jogo para tabela de jogos
public void addJogoParaTabela(String nome, double precoOriginal, double desconto )throws Error {
    	JogoCategorizavel categoriaAux=this.controlador.verificarEstado(desconto);
    	Jogo novoJogo=new Jogo(nome, precoOriginal, categoriaAux,desconto, this.controlador);
    	this.tabelaDeJogos.add(novoJogo);
	
}

public Jogo getJogo(String nome) throws Exception {
	Jogo jogoEncontrado = null;
	TreeSet<Jogo> jogos = this.tabelaDeJogos;
	for(Jogo jogo : jogos) {
		if(jogo.getNome().toLowerCase().equals(nome.toLowerCase().trim())) {
			jogoEncontrado = jogo;	
		}
	}
	
	if(jogoEncontrado == null) {
		throw new Exception("Jogo não encontrado");
	}else System.out.println("Jogo adicionado ao carrinho!");
	 
	return jogoEncontrado;
}
/*metodo construtor ele cria um aquivo com o codigo passado por parametro e instancia uma treeset
 * para colocar os jogos a serem adicionados
 */
public XulambGames(String codigoLoja) {
	Cadastrado cadastrado=new Cadastrado();
	Fanatico fanatico=new Fanatico();
	Empolgado empolgado=new Empolgado();
	this.estadosClientes=new HashMap<String, clienteCategorizavel>();
	this.estadosClientes.put("cadastrado", cadastrado);
	this.estadosClientes.put("fanatico",fanatico);
	this.estadosClientes.put("empolgado",empolgado);
	this.controlador=new JogoStateControlador();
	this.setTabelaDeJogos(new TreeSet<>());
	this.codigo=codigoLoja;
	this.clientes = new LinkedList<>();
	File file=new File(codigoLoja+".txt");
	try {
		ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(file));
		oos.writeObject(this);
		oos.close();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
//metodo para fechar a loja, ele salva todos os dados no arquivo previamente criado
public void fecharLoja(String codigo) {
	String txt=codigo+".txt";
	try {
		ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(txt));
		oos.writeObject(this);
		oos.close();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
//metodo que imprime os jogos mais e menos vendidos
public String getExtremos() {
	Optional<Jogo> maiorJogo=tabelaDeJogos.stream().max((j1, j2)->extremosComparator(j1,j2));
	Optional<Jogo> menorJogo=tabelaDeJogos.stream().min((j1, j2)->extremosComparator(j1,j2));
	return("Menor jogo="+menorJogo + "\nMaior Jogo="+maiorJogo);
}
private int extremosComparator(Jogo jogoComparado1, Jogo jogoComparado2 ) {
	if (jogoComparado1.getPreco() > jogoComparado2.getPreco()) {
		return 1;
	}
	else if (jogoComparado1.getPreco() == jogoComparado2.getPreco()) {
		return 0;
	}
	else{
		return -1;
	}
	}

public LinkedList<Cliente> getClientes() {
	return clientes;
}

public void addCliente(String nome, String categoria, String nomeUsuario, String senha) {
Cliente novoCliente=new Cliente(nome,nomeUsuario, senha,this,this.estadosClientes.get(categoria),this.estadosClientes);
this.clientes.add(novoCliente);
}

public void setClientes(LinkedList<Cliente> novaLista) {

	this.clientes = novaLista;
}

public String relatorioClientes() {
	StringBuilder relatorio = new StringBuilder("");
	
	relatorio.append("============== CLIENTES DA LOJA ===============\n\n");
	relatorio.append("Nome de usuário");
	relatorio.append(" =========== ");
	relatorio.append("Tipo de Cliente\n\n");
	for(Cliente cliente : this.getClientes()) {
		relatorio.append(cliente + "\n");
	}
	relatorio.append("===============================================");
	return relatorio.toString();
}
}
