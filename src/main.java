import java.util.Scanner;
import javax.swing.JOptionPane;



public class main {
	public static void main(String[] args) {
		Scanner leitor = new Scanner(System.in);
		sistemachamado sistema = new sistemachamado();
		int opcao = -1;
		
		Object[] perfis = { "Admin", "Tecnico", "Usuario" };
		
		int escolha = JOptionPane.showOptionDialog(null, "Como voce deseja acessar o sistema?", "Login do sistema", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, perfis, perfis[0]);
		String perfillogado;
		if (escolha == 0) perfillogado = "Admin";
		else if(escolha == 1) perfillogado = "Tecnico";
		else if(escolha == 2) perfillogado = "Usuario";
		else return;
		
		
		
		while (opcao != 0) {
			String[] botoes;
			if(perfillogado.equals("Admin")) {
				botoes = new String[] {"Cadastrar usuario", "cadastrar tecnico", "cadastrar categoria", "listar chamados", "sair"};
				
			}else if (perfillogado.equals("tecnico")) {
				botoes = new String[] {"Alterar Status", "Listar chamados", "Sair"};
			} else {
				botoes = new String[] {"abrir chamado", "Listar meus chamados", "Sair"};
			}
			
			int clique = JOptionPane.showOptionDialog(null, "Ola " + perfillogado + "O que deseja fazer?", "Menu principal" , JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, botoes, botoes[0]);
			
			
			if(clique == -1 || botoes[clique].equals("Sair")) {
				opcao = 0;
			} else {
				String acao = botoes[clique];
				
				switch (acao) {
				case "Cadastrar usuario":
					String nome = JOptionPane.showInputDialog("Nome:");
					String email = JOptionPane.showInputDialog("Email");
					sistema.cadastrarUsuario(nome, email);
					
				case "cadastrar tecnico":
					String nomeT = JOptionPane.showInputDialog("Nome: ");
					String emailT = JOptionPane.showInputDialog("Email:");
					sistema.cadastrartecnicos(nomeT, emailT);
					break;
				case "cadastrar categoria":
					String nomeC = JOptionPane.showInputDialog("Nome");
					sistema.cadastrarcategoria(nomeC);
					break;
				case "abrir chamado":
					String descricao = JOptionPane.showInputDialog("Descrição do problema:");
					String status = "Aberto";
					
					int IDU = Integer.parseInt(JOptionPane.showInputDialog(sistema.listausuarios() + "\nDigite o ID do Usuário:"));
					int IDT = Integer.parseInt(JOptionPane.showInputDialog(sistema.listausuarios() + "\nDigite o ID do Usuário:"));
					int IDC = Integer.parseInt(JOptionPane.showInputDialog(sistema.listausuarios() + "\nDigite o ID do Usuário:"));
					
					
					sistema.cadastrarchamado(descricao, status, IDU, IDT, IDC);
					break;
				}
			}
		}
		
			
		}
			
	public static String perfil(String perfil) {
		String display = "Logado como:" + perfil + "\n";
		
		
		if (perfil.equals("Admin")) {
			display += "1 - Cadastrar usuario \n" +
					   "2 - Cadastrar tecnico \n" +
					   "3 - Cadastrar tecnicos \n"+
					   "4 - Abrir Chamados \n" +
					   "5 - Alterar status";
		} else if (perfil.equals("Tecnico")) {
			display += "2- esse teste funcionou";
		} else if (perfil.equals("Usuario")) {
			display += "3- esse teste funcionou";
		}
		
		display += "Sair";
		return display;
	}

}



//String menu = "Sistema de chamados \n" +
		 // "1 - Cadastrar usuario \n" +
		  //"2 - Cadastrar tecnicos \n" +
		  //"3 - Cadastrar categorias \n" +
		  //"4 - Abrir Chamados \n" +
		  //"5 - Alterar Status \n" +
		  //"6 - Listar dados \n";
//String leitura = JOptionPane.showInputDialog(menu);
//if (leitura == null) break;

//opcao = Integer.parseInt(leitura);

