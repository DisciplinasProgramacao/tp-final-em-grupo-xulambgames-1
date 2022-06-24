package jogos;
import java.io.Serializable;

import categoriasjogos.JogoCategorizavel;
import controladores.JogoStateControlador;

public class  Jogo implements Comparable<Jogo>,Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 2760006039823000143L;

//atributos da classe
private double precoOriginal;
private int numeroVendas;
private double preco;
private JogoCategorizavel categoria;
private JogoStateControlador controlador;
private String nome;
private double desconto;

//construtor da classe.
public Jogo(String nome, double precoOriginal, JogoCategorizavel categoria, double desconto, JogoStateControlador controlador) {
	this.controlador=controlador;
this.setNome(nome);
this.categoria=categoria;
	this.setPrecoOriginal(precoOriginal);
	this.desconto=desconto;
	this.setPreço();
}
//gets e sets da classe

public void setDesconto(double desconto) {
	this.desconto=desconto;
	this.categoria=this.controlador.verificarEstado(this.desconto);
}
public double getDesconto() {
	return this.desconto;
}
public void setCategoria(JogoCategorizavel categoria) {
	this.categoria=categoria;
	this.setPreço();
}
public JogoCategorizavel getCategoria() {
	return this.categoria;
}
public void setNome(String nome) {
	this.nome=nome;
}
public int getNumeroVendas() {
	return numeroVendas;
}

public void addVendas() {
	this.numeroVendas++;
}
public void setNumeroVendas(int numeroVendas) {
	this.numeroVendas = numeroVendas;
}
public double getPrecoOriginal() {
	return this.precoOriginal;
}
public double getPreco() {
	return this.preco;
}
public String getNome(){
return this.nome;
}
public void setPrecoOriginal(double precoOriginal) {
	this.precoOriginal=precoOriginal;
}
/*metodo set do preço funciona diferente, ele verifica se o objeto tem desconto, se sim, ele aplica
 * o desconto
 */

private void setPreço() {
	try {
		this.preco=this.categoria.calcularPreco(this.precoOriginal, this.desconto);
	} catch (Exception e) {
		e.printStackTrace();
	}
}
@Override
public String toString() {
	StringBuilder espacos = new StringBuilder("");
	while(this.nome.length() + 3 + espacos.length() != 27) {
		espacos.append(" ");
	}
	return this.nome + espacos + this.getPreco() +espacos+this.getCategoria()+ "\n";
}
//Overide no equals e no compareTo para funcionar no treeset
@Override
public boolean equals(Object jogoComparado) {
	if(this.numeroVendas==((Jogo) jogoComparado).getNumeroVendas())
		return true;
	return false;
}
@Override
public int compareTo(Jogo jogoComparado) {
	if(this.toString().compareTo(jogoComparado.toString())==0) {
		return 0;
	}
	if(this.toString().compareTo(jogoComparado.toString())==-1) {
		return -1;
	}
	return 1;
}
}


