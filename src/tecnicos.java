
public class tecnicos {
	private int id;
	private String nome;
	private String especialidade;
	private String perfil;
	private static int contadorid = 1;
	

	public tecnicos(String nome, String especialidade, String perfil) {
		this.id = contadorid++;
		this.nome = nome;
		this.especialidade = nome;
		this.perfil = perfil;
		
	}
	
	public int getId() { return id;}
	public void setId(int id) { this.id = id; }
	
	public String getNome() { return nome; }
	public void setNome(String nome) {this.nome = nome;}
	
	public String getEspecialidade() {return especialidade;}
	public void setEspecialidade(String especialidade) {this.especialidade = especialidade;}
	
	
    public String getPerfil() {
        return perfil;
    }

	
	

}
