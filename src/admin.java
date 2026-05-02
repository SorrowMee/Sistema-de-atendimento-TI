public class admin {
    private int id;
    private String nome;
    private String email;
    private static int contadorid = 1; 
	//construtor definindo id, nome, usuarios
    public admin(String nome, String email) {
        this.id = contadorid++;
        this.nome = nome;
        this.email = email;
    }
	//getters e setter utilizando o this acima
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
}

//essas classes seperadas estão definindo funções para serem puxadas e usadas em outras classes utilizando
//setters e getters assim organizando o codigo para melhor alteração