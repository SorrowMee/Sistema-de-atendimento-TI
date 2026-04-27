public class usuarios {
    private int id;
    private String nome;
    private String email;
    private String perfil;
    private static int contadorid = 1;

    public usuarios(String nome, String email, String perfil) {
        this.id = contadorid++;
        this.nome = nome;
        this.email = email;
        this.perfil = perfil;
    }


    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getPerfil() { return perfil; }

   
    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
}