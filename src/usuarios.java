public class usuarios {
    private int id;
    private String nome;
    private String email;
    private String perfil; 
    private static int contadorid = 1;
	//construtor definindo id, nome, usuarios
    public usuarios(String nome, String email, String perfil) {
        this.id = contadorid++;
        this.nome = nome;
        this.email = email;
        this.perfil = perfil;
    }
	//getters e setter utilizando o this acima
    public String getPerfil() {
        return perfil;
    }


    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
}


//essas classes seperadas estão definindo funções para serem puxadas e usadas em outras classes utilizando
//setters e getters assim organizando o codigo para melhor alteração