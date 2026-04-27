import javax.swing.JOptionPane;
import java.io.*;

public class sistemachamado {
    usuarios[] usuarios = new usuarios[100];
    tecnicos[] tecnicos = new tecnicos[50];
    categoria[] categoria = new categoria[20];
    chamado[] chamado = new chamado[200];
    
    int qtdusuarios = 0, qtdtecnicos = 0, qtdcategoria = 0, qtdchamado = 0;

    // Métodos de Procura (Busca o objeto real pelo ID)
    public usuarios procurausu(int id) {
        for (int i = 0; i < qtdusuarios; i++) {
            if (usuarios[i].getId() == id) return usuarios[i];
        }
        return null;
    }

    public tecnicos procuratec(int id) {
        for (int i = 0; i < qtdtecnicos; i++) {
            if (tecnicos[i].getId() == id) return tecnicos[i];
        }
        return null;
    }

    public categoria procuracat(int id) {
        for (int i = 0; i < qtdcategoria; i++) {
            if (categoria[i].getId() == id) return categoria[i];
        }
        return null;
    }

    // Cadastros
    public void cadastrarUsuario(String nome, String email, String perfil) {
        if (qtdusuarios < usuarios.length) {
            usuarios[qtdusuarios] = new usuarios(nome, email, perfil);
            qtdusuarios++;
            salvarUsuariosArquivo();
        }
    }

    public void cadastrartecnicos(String nome, String especialidade) {
        if (qtdtecnicos < tecnicos.length) {
            tecnicos[qtdtecnicos] = new tecnicos(nome, especialidade);
            qtdtecnicos++;
        }
    }

    public void cadastrarcategoria(String nome) {
        if (qtdcategoria < categoria.length) {
            categoria[qtdcategoria] = new categoria(nome);
            qtdcategoria++;
            salvarcategoriaarquivo();
        }
    }

    public void cadastrarchamado(String desc, String status, int idU, int idT, int idC) {
        usuarios u = procurausu(idU);
        tecnicos t = procuratec(idT);
        categoria c = procuracat(idC);

        if (u != null && t != null && c != null) {
            chamado[qtdchamado++] = new chamado(desc, status, u, t, c);
            JOptionPane.showMessageDialog(null, "Chamado aberto!");
        } else {
            JOptionPane.showMessageDialog(null, "Erro: IDs inválidos!");
        }
    }

    // Listagens formatadas para o JOptionPane
    public String listausuarios() {
        String s = "--- USUÁRIOS ---\n";
        for (int i = 0; i < qtdusuarios; i++) 
            s += usuarios[i].getId() + " - " + usuarios[i].getNome() + " (" + usuarios[i].getPerfil() + ")\n";
        return s;
    }

    public String listatecnicos() {
        String s = "--- TÉCNICOS ---\n";
        for (int i = 0; i < qtdtecnicos; i++) 
            s += tecnicos[i].getId() + " - " + tecnicos[i].getNome() + "\n";
        return s;
    }

    public String listarcategorias() {
        String s = "--- CATEGORIAS ---\n";
        for (int i = 0; i < qtdcategoria; i++) 
            s +=categoria[i].getNome() + "\n";
        return s;
    }

    // Persistência em Arquivo
    public void salvarUsuariosArquivo() {
        try (PrintWriter out = new PrintWriter(new FileWriter("usuarios.txt"))) {
            for (int i = 0; i < qtdusuarios; i++) {
                out.println(usuarios[i].getNome() + ";" + usuarios[i].getEmail() + ";" + usuarios[i].getPerfil());
            }
        } catch (IOException e) { System.out.println("Erro ao salvar."); }
    }

    public void carregarUsuariosArquivo() {
        try (BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] d = linha.split(";");
                if (d.length == 3) cadastrarUsuario(d[0], d[1], d[2]);
            }
        } catch (IOException e) { System.out.println("Novo arquivo será criado."); }
    }

    public boolean validarLogin(String email, String perfil) {
        for (int i = 0; i < qtdusuarios; i++) {
            if (usuarios[i].getEmail().equalsIgnoreCase(email) && usuarios[i].getPerfil().equals(perfil)) return true;
        }
        return false;
    }
    
    
    
    public void salvarcategoriaarquivo() {
        try (PrintWriter out = new PrintWriter(new FileWriter("categoria.txt"))) {
            for (int i = 0; i < qtdcategoria; i++) {
                out.println(categoria[i].getNome());
            }
        } catch (IOException e) { System.out.println("Erro ao salvar."); }
    }
    
    public void carregarCategoriasArquivo() {
        try (BufferedReader br = new BufferedReader(new FileReader("categorias.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] d = linha.split(";");
                if (d.length == 1) cadastrarcategoria(d[0]);	
            }
        } catch (IOException e) { System.out.println("Novo arquivo será criado."); }
    }

    
    
}