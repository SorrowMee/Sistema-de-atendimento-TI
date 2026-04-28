public class admin {
    private int id;
    private String nome;
    private String email;
    private static int contadorid = 1; 

    public admin(String nome, String email) {
        this.id = contadorid++;
        this.nome = nome;
        this.email = email;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
}