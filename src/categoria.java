
public class categoria {
	private int id;
	private String nome;
	
	//construtor definindo id, nome, usuarios
	public categoria(int id, String nome) {
		this.id = id;
		this.nome = nome;
		
	}
	
	//getters e setter utilizando o this acima
	public int getId() { return id;}
	public void setId(int id) { this.id = id; }
	
	public String getNome() { return nome; }
	public void setNome(String nome) {this.nome = nome;}
	
	

}
