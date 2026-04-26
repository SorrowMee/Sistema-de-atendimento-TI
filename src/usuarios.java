
public class usuarios {
	private int id;
	private String nome;
	private String email;
	private static int contadorid = 1;
	
	//construtor definindo id, nome, usuarios
	public usuarios(String nome, String email) {
		this.id = contadorid++;
		this.nome = nome;
		this.email = email;
		
	}
	
	//getters e setter utilizando o this acima
	public int getId() { return id;}
	
	public String getNome() { return nome; }
	public void setNome(String nome) {this.nome = nome;}
	
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	
	

}
