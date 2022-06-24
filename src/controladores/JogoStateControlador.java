package controladores;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import categoriasjogos.JogoCategorizavel;
import categoriasjogos.Lancamento;
import categoriasjogos.Premium;
import categoriasjogos.Promocao;
import categoriasjogos.Regular;

public class JogoStateControlador implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = -3500877234078870109L;
private Map<String,JogoCategorizavel> estadosDePrecos;
public JogoStateControlador() {
	this.estadosDePrecos=new HashMap<String,JogoCategorizavel>();
	Lancamento lancamento=new Lancamento();
	Premium premium=new Premium();
	Promocao promocao=new Promocao();
	Regular regular=new Regular();
	this.estadosDePrecos.put("lancamento", lancamento);
	this.estadosDePrecos.put("promocao",promocao);
	this.estadosDePrecos.put("premium",premium);
	this.estadosDePrecos.put("regular",regular);
}
public JogoCategorizavel verificarEstado(double desconto) {
	if(desconto==1.1) {
		return this.estadosDePrecos.get("lancamento");
	}
	else if(desconto==1) {
		return estadosDePrecos.get("premium");
	}
	else if(desconto>=0.7 && desconto < 1) {
		return estadosDePrecos.get("regular");
	}
	else {
		return estadosDePrecos.get("promocao");
	}
	
}
}
