import java.util.Scanner;
import javax.swing.JOptionPane;



public class main {
	public static void main(String[] args) {
		Scanner leitor = new Scanner(System.in);
		sistemachamado sistema = new sistemachamado();
		int opcao = -1;
		
		
		String menu = "Sistema de chamados \n" +
					  "1 - Cadastrar usuario";
		
		while (opcao != 0) {
			String leitura = JOptionPane.showInputDialog(menu);
			if (leitura == null) break;
			
			opcao = Integer.parseInt(leitura);
			
		switch (opcao) {
			case 1:
				int id = Integer.parseInt(JOptionPane.showInputDialog("ID"));
				String nome = JOptionPane.showInputDialog("Nome");
				String email = JOptionPane.showInputDialog("Email");
				
				sistema.cadastrarUsuario(id, nome, email);
				break;
				
				
			case 2: 
				System.out.println("ID");
				int idT = leitor.nextInt();
				leitor.nextLine();
				System.out.println("Nome");
				String nomeT = leitor.nextLine();
				System.out.println("Email");
				String especialidade = leitor.nextLine();
				
				sistema.cadastrartecnicos(idT, nomeT, especialidade);
				break;
				
			case 3:
				System.out.println("ID");
				int idC = leitor.nextInt();
				leitor.nextLine();
				System.out.println("Nome");
				String nomeC = leitor.nextLine();
				
				sistema.cadastrarcategoria(idC, nomeC);
				break;
				
				
			case 6:
				String lista = sistema.listausuarios();
				JOptionPane.showMessageDialog(null, lista);
				
				break;
			case 0:
				break;
			default:
				System.out.println("opcao invalida");
		}
			
		}
	}

}
