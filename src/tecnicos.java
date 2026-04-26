
public class tecnicos {
	private int id;
	private String nome;
	private String especialidade;
	private static int contadorid = 1;
	
	//construtor definindo id, nome, usuarios
	public tecnicos(String nome, String especialidade) {
		this.id = contadorid++;
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
