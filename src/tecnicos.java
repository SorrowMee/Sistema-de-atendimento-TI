
public class tecnicos {
	private int id;
	private String nome;
	private String email;
	private String especialidade;
	private String perfil;
	private static int contadorid = 1;
	
	//construtor definindo id, nome, usuarios
	public tecnicos(String nome, String especialidade,String email, String perfil) {
		this.id = contadorid++;
		this.nome = nome;
		this.email = email;
		this.especialidade = especialidade;
		this.perfil = perfil;
		
	}
	//getters e setter utilizando o this acima
	public int getId() { return id;}
	public void setId(int id) { this.id = id; }
	
	public String getNome() { return nome; }
	public void setNome(String nome) {this.nome = nome;}
	
	public String getEspecialidade() {return especialidade;}
	public void setEspecialidade(String especialidade) {this.especialidade = especialidade;}
	
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email= email;}
	
	
    public String getPerfil() {
        return perfil;
    }

	
	

}


//essas classes seperadas estão definindo funções para serem puxadas e usadas em outras classes utilizando
//setters e getters assim organizando o codigo para melhor alteração