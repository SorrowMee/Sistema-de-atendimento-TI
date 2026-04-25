
public class tecnicos {
	private int id;
	private String nome;
	private String especialidade;
	
	//construtor definindo id, nome, usuarios
	public tecnicos(int id, String nome, String especialidade) {
		this.id = id;
		this.nome = nome;
		this.especialidade = nome;
		
	}
	
	//getters e setter utilizando o this acima
	public int getId() { return id;}
	public void setId(int id) { this.id = id; }
	
	public String getNome() { return nome; }
	public void setNome(String nome) {this.nome = nome;}
	
	public String getEmail() {return especialidade;}
	public void setEmail(String especialidade) {this.especialidade = especialidade;}
	
	

}
