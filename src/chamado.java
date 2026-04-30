
public class chamado {
	private int id;
	private String descricao;
	private String status;
	private usuarios usuarios;
	private tecnicos tecnicos;
	private categoria categoria;
	private static int contadorid = 1;
	
	
	
	public chamado(String descricao, String status, usuarios usuarios, tecnicos tecnicos, categoria categoria) {
		this.id = contadorid++;
		this.descricao = descricao;
		this.status = status;
		this.usuarios = usuarios;
		this.tecnicos = tecnicos;
		this.categoria = categoria;
		
	}
	
	
	public int getId() { return id;}
	public void setId(int id) { this.id = id; }
	
	public String getDescricao() { return descricao;}
	public void setDescricao(String descricao) { this.descricao = descricao; }
	
	public String getStatus() { return status;}
	public void setStatus(String status) { this.status = status; }
	
	public usuarios getUsuarios() { return usuarios;}
	public void setUsuarios(usuarios usuarios) { this.usuarios = usuarios; }
	
	public tecnicos getTecnicos() { return tecnicos;}
	public void setTecnicos(tecnicos tecnicos) { this.tecnicos = tecnicos; }
	
	public categoria getCategoria() { return categoria;}
	public void setCategoria(categoria categoria) { this.categoria = categoria; }
	

}
