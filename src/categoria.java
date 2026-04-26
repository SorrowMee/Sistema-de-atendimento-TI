
public class categoria {
	private int id;
	private String nome;
	private static int contadorid = 1;
	
	//construtor definindo id, nome, usuarios
	public categoria(String nome) {
		this.id = contadorid++;
		this.nome = nome;
		
	}
	
	//getters e setter utilizando o this acima
	public void setId(int id) { this.id = id; }
	
	public String getNome() { return nome; }
	public void setNome(String nome) {this.nome = nome;}
	
	

}
