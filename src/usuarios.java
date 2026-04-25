
public class usuarios {
	private int id;
	private String nome;
	private String email;
	
	//construtor definindo id, nome, usuarios
	public usuarios(int id, String nome, String email) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		
	}
	
	//getters e setter utilizando o this acima
	public int getId() { return id;}
	public void setId(int id) { this.id = id; }
	
	public String getNome() { return nome; }
	public void setNome(String nome) {this.nome = nome;}
	
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	
	

}
