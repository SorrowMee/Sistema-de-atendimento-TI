import javax.swing.JOptionPane;

public class sistemachamado {
	usuarios[] usuarios = new usuarios[100];
	tecnicos[] tecnicos = new tecnicos[50];
	categoria[] categoria = new categoria[20];
	chamado[] chamado = new chamado[200];
	int qtdusuarios = 0;
	int qtdtecnicos = 0;
	int qtdcategoria = 0;
	int qtdchamado = 0;
	
	public void cadastrarUsuario(String nome, String email) {
		if (qtdusuarios < usuarios.length) {
			usuarios[qtdusuarios] = new usuarios(nome, email);
			qtdusuarios++;
			System.out.println("Usuario cadastrado com sucesso!");			
		} else {
			System.out.println("Limite alcançado");
		}
	}
	
	
	
	public String listausuarios() {
		String texto = "--- LISTA DE USUÁRIOS ---\n";
        for (int i = 0; i < qtdusuarios; i++) {
            texto += "ID: " + usuarios[i].getId() + " | Nome: " + usuarios[i].getNome() + "\n" + usuarios[i].getEmail();
        }
        return texto;
	}
	
	public void cadastrartecnicos(String nomeT, String especialidadeT) {
		if (qtdtecnicos < tecnicos.length) {
			tecnicos[qtdtecnicos] = new tecnicos(nomeT, especialidadeT);
			qtdtecnicos++;
			System.out.println("Tecnico cadastrado com sucesso!");			
		} else {
			System.out.println("Limite alcançado");
		}
	}
	
	
	//public void listatecnicos() {
		//for (int i = 0; i < qtdtecnicos; i++) {
			//System.out.println("ID" + tecnicos[i].getId() + " Nome " + tecnicos[i].getNome());
			
		//}
	//}
	
	
	public void cadastrarcategoria(String nomeC) {
		if (qtdcategoria < categoria.length) {
			categoria[qtdcategoria] = new categoria (nomeC);
			qtdcategoria++;
			System.out.println(" cadastrado com sucesso!");			
		} else {
			System.out.println("Limite alcançado");
		}
	}
	
	
	public void cadastrarchamado(String descricao, String status, int IDU, int IDT, int IDC) { 
		usuarios u = procurausu(IDU);
		tecnicos t = procuratec(IDT);
		categoria c = procuracat(IDC);
		
		if(u != null && t != null && c != null) {
			if(qtdchamado < chamado.length) {
				chamado[qtdchamado] = new chamado(descricao, status, u, t, c);
				qtdchamado++;
				JOptionPane.showMessageDialog(null, "Chamado criado com sucesso");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Erro");
		}
	}
		

}
