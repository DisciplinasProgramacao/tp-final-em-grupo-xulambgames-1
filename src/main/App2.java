package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import clientes.Cliente;
import jogos.Jogo;
import lojal.XulambGames;

public class App2 {
	static Scanner entrada; 
	static XulambGames minhaLoja;;

	public static void main(String[] args) {
		FileInputStream dados;
		int escolha2 = 0;
		try {
			dados = new FileInputStream("01.txt");
		ObjectInputStream dadosRead;
			dadosRead = new ObjectInputStream(dados);
			

		 dadosRead.close();
		}
		catch (FileNotFoundException e) {
			minhaLoja = new XulambGames("01.txt");
        }catch(IOException ex){
         
        }
		entrada = new Scanner(System.in, "UTF-8");
		
		
	
		while(escolha2 != 4) {
			menuPrincipal();
			escolha2 = entrada.nextInt();
			entrada.nextLine();
			minhaLoja = escolhaMenuPrincipal(escolha2, minhaLoja, entrada);
		}
		

		minhaLoja.fecharLoja("01");
		
		entrada.close();
	}
	
	public static XulambGames menuPrincipalAdmin(XulambGames loja, Scanner entrada) {
		int escolha = 0;
		while(escolha != 6) {
			System.out.println();
			System.out.println("==== Admin ====");
			System.out.println("1 - Realizar compra");
			System.out.println("2 - Adicionar jogo na loja");
			System.out.println("3 - Cadastrar Cliente");	
			System.out.println("4 - Exibir Jogos disponíveis");
			System.out.println("5 - Exibir clientes cadastrados");
			System.out.println("6 - Sair");
			escolha = entrada.nextInt();
			entrada.nextLine();
			if(escolha!=6) {
				loja = escolhaMenuAdmin(escolha, loja, entrada, null);
			}
		}
		return loja;
	}
	
	public static XulambGames escolhaMenuAdmin(int escolha, XulambGames loja, Scanner entrada, Cliente clienteCadastrado) {
		switch(escolha) {
		case 1: 
			if(clienteCadastrado != null) {
			loja = realizarVenda(loja, entrada, clienteCadastrado);
			}else System.out.println("\n* Opção indisponível no momento. Será implementada na próxima atualização. *");
		break;
		case 2: loja = adicionarJogoLoja(loja, entrada);
		break;
		case 3: loja = cadastrarCliente(loja, entrada);
		break;
		case 4: exibirJogosDisponiveis(loja);
		break;
//		case 5: exibirClientes(loja);
		case 5: System.out.println(loja.relatorioClientes());
		break;
		}
		
		return loja;
	}
	public static void menuPrincipal() {
		System.out.println();
		System.out.println("=== Menu Principal ===");
		System.out.println("1 - Fazer login");
		System.out.println("2 - Fazer cadastro");
		System.out.println("3 - Menu Admin");
		System.out.println("4 - Sair");
	}
	
	public static XulambGames escolhaMenuPrincipal(int escolha, XulambGames loja, Scanner entrada) {
		
		switch(escolha) {
		case 1: validarLogin(loja, entrada);
		break;
		case 2: loja = cadastrarCliente(loja, entrada);
		break;
		case 3: loja = menuPrincipalAdmin(loja, entrada);
		break;
		}
		return loja;
	}
	public static void menuCliente() {
		System.out.println();
		System.out.println("1 - Realizar Compra");
		System.out.println("2 - Mudar Categoria");
		System.out.println("3 - Informações sobre a conta");
		System.out.println("5 - Histórico de Compras");
		System.out.println("4 - Sair");
	}
	
	public static XulambGames escolhaMenuCliente(int escolha, XulambGames loja, Scanner entrada, Cliente cliente) {
		
		switch(escolha) {
			case 1: loja = realizarVenda(loja, entrada, cliente);
			break;
			case 2: loja = mudarCategoriaCliente(loja, entrada, cliente);
			break;
			case 3: exibirInformaçõesConta(cliente);
			break;
			case 5: System.out.println(cliente.relatorio());
			break;
		}
		
		return loja;
	}
	
	public static void exibirInformaçõesConta(Cliente cliente) {
		System.out.println(cliente);
		
	}

	private static XulambGames mudarCategoriaCliente(XulambGames loja, Scanner entrada, Cliente cliente) {
		int escolha = 0;
		exibirCategorias();
		escolha = entrada.nextInt();
		
		switch(escolha) {
		case 1:
			cliente.setTipoCLiente("cadastrado");
			break;
		case 2: 
			cliente.setTipoCLiente("empolgado");
			break;
		case 3: 
			cliente.setTipoCLiente("fanatico");
			break;	
		
		}
		return loja;
	}

	public static void MenuVendaJogos() {
		System.out.println();
		System.out.println("1 - Adicionar jogo");
		System.out.println("2 - Remover jogo");
		System.out.println("3 - Exibir carrinho");
		System.out.println("4 - Fechar pedido");
	}
	
	public static XulambGames realizarVenda(XulambGames loja, Scanner entrada, Cliente cliente) {
		int escolhaMenuJogos = 0;
			
			int index = loja.getClientes().indexOf(cliente);
			try {

				
				
				try {
				cliente.iniciarCompra();
				}catch(Exception e) {
					cliente.limparCarrinhoAtual();
				}
				String jogo = null;
				Jogo vendaJogo = null;
				while(escolhaMenuJogos != 4) {
					System.out.println();
					MenuVendaJogos();
					escolhaMenuJogos = entrada.nextInt();
					
					switch(escolhaMenuJogos) {
					case 1: 
							exibirJogosDisponiveis(loja);
							 System.out.print("Digite o nome do jogo");
							 System.out.print(" ou 0 para voltar.\n");
							 vendaJogo = null;
							 	 
							 jogo = entrada.nextLine();			
							 jogo = entrada.nextLine();	
							   if(!jogo.equals("0")) {
								try{
								 vendaJogo = loja.getJogo(jogo);
								 }catch(Exception e) {
									 System.out.println();
									 System.out.println(e.getMessage());
									 System.out.println();
									 System.out.print("Digite o nome do jogo");
									 System.out.print(" ou 0 para voltar.\n");
									 jogo = entrada.nextLine();
									 if(!jogo.equals("0"))
									 vendaJogo = loja.getJogo(jogo);
								 }
								 if(vendaJogo != null)
								 cliente.addJogo(vendaJogo);
							 }
					break;
					case 2: 
							System.out.println(cliente.getCarrinhoAtual());
							System.out.println("Remover qual jogo?");
							String jogopararemover = entrada.next();
							cliente.removeJogo(jogopararemover);
							
					break;
					case 3: System.out.println("=== Carrinho ===");
							System.out.println();
							System.out.println(cliente.getCarrinhoAtual().exibirCarrinho());
							break;
					case 4:
						if(cliente.getCarrinhoAtual().getJogos().size() == 0){ 
							System.out.println("Não há itens no carrinho! Compra não efetuada! ");
						}else {
							System.out.println(cliente.getCarrinhoAtual().exibirCarrinho());
							System.out.println();
							System.out.println("Compra efetuada com sucesso!");
							cliente.finalizarCompra();}
							break;

					}
				
				}
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
				if(cliente.getCarrinhoAtual().getJogos().size() > 0) {
				loja.getClientes().set(index, cliente);
			}
//		}
		return loja;
	}
	
	public static XulambGames adicionarJogoLoja(XulambGames loja, Scanner entrada) {
		String nome;
		Double preco;
		double desconto = 0;
		
		System.out.println("Nome do jogo ");
		nome = entrada.nextLine();
		System.out.println("Preço do jogo ");
		preco = entrada.nextDouble();
		System.out.println("Desconto ");
		desconto = entrada.nextDouble();

			loja.addJogoParaTabela(nome, preco, desconto);
			
		return loja;
		
	}
	
	public static XulambGames cadastrarCliente(XulambGames loja, Scanner entrada) {
		String nome;
		String nomeUsuario;
		String senha;
		int categoria;
		
		System.out.println("Nome do cliente ");
		nome = entrada.next();
		
		System.out.println("Nome de Usuário ");
		nomeUsuario = entrada.next();
		
		System.out.println("Senha ");
		senha = entrada.next();
		
		exibirCategorias();
		categoria = entrada.nextInt();
		
		switch(categoria) {
		case 1: loja.addCliente(nome,"cadastrado", nomeUsuario, senha);
				break;
		case 2: loja.addCliente(nome,"empolgado", nomeUsuario, senha);
				break;
		case 3: loja.addCliente(nome,"fanatico", nomeUsuario, senha);
				break;
		}
		
		return loja;
	}
	public static void exibirCategorias() {
		System.out.println();
		System.out.println("--Categorias--");
		System.out.println("1 - Cliente Normal");
		System.out.println("2 - Cliente Empolgado");
		System.out.println("3 - Cliente Fanático");
		System.out.print("Escolha a categoria: ");
	}

	public static XulambGames validarLogin(XulambGames loja, Scanner entrada) {
		String nomeUsario;
		String senha;
		int escolhaMenuCliente = 0;
		System.out.println("Nome de usuário ");
		nomeUsario = entrada.next();
		
		System.out.println("Senha ");
		senha = entrada.next();
		
		boolean cliente = loja.getClientes().stream().anyMatch(c -> c.getNomeUsuario().equals(nomeUsario.trim()) && c.getSenha().equals(senha.trim()));
		if(cliente) {
			Cliente clienteCadastrado = loja.getClientes().stream().filter(c -> c.getNomeUsuario().equals(nomeUsario.trim()) && c.getSenha().equals(senha.trim())).collect(Collectors.toList()).get(0);
			while(escolhaMenuCliente != 4) {
				menuCliente();
				escolhaMenuCliente = entrada.nextInt();
				loja = escolhaMenuCliente(escolhaMenuCliente,loja, entrada,clienteCadastrado);
			}
			
		}else {
			System.out.println("\n* Nome de usuário ou senha incorretor ou Cadastro inexistente *");
		}
		
		return loja;
	}
	public static void exibirJogosDisponiveis(XulambGames loja) {
		Set<Jogo> jogos = loja.getTabelaDeJogos();
		
		System.out.println("\nJogo" + " ------------------ " + "Preço"+ " ------------------ "+"Tipo De Jogo");
		jogos.stream().forEach(System.out::print);
		System.out.println();
	}
	
	public static XulambGames exibirClientes(XulambGames loja) {
		
		loja.getClientes().stream().forEach(System.out::print);
		return loja;
	}
}
