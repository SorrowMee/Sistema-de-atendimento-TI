
public class sistemachamado {
	usuarios[] usuarios = new usuarios[100];
	tecnicos[] tecnicos = new tecnicos[50];
	categoria[] categoria = new categoria[20];
	chamado[] chamado = new chamado[200];
	int qtdusuarios = 0;
	int qtdtecnicos = 0;
	int qtdcategoria = 0;
	int qtdchamado = 0;
	
	public void cadastrarUsuario(int id, String nome, String email) {
		if (qtdusuarios < usuarios.length) {
			usuarios[qtdusuarios] = new usuarios(id, nome, email);
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
	
	public void cadastrartecnicos(int idT, String nomeT, String especialidadeT) {
		if (qtdtecnicos < tecnicos.length) {
			tecnicos[qtdtecnicos] = new tecnicos(idT, nomeT, especialidadeT);
			qtdtecnicos++;
			System.out.println("Tecnico cadastrado com sucesso!");			
		} else {
			System.out.println("Limite alcançado");
		}
	}
	
	
	public void listatecnicos() {
		for (int i = 0; i < qtdtecnicos; i++) {
			System.out.println("ID" + tecnicos[i].getId() + " Nome " + tecnicos[i].getNome());
			
		}
	}
	
	
	public void cadastrarcategoria(int idC, String nomeC) {
		if (qtdcategoria < categoria.length) {
			categoria[qtdcategoria] = new categoria (idC, nomeC);
			qtdcategoria++;
			System.out.println(" cadastrado com sucesso!");			
		} else {
			System.out.println("Limite alcançado");
		}
	}
	
	
	

}
