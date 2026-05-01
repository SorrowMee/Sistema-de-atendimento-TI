	import javax.swing.JOptionPane;
	import java.io.*;
	
	public class sistemachamado {
	    usuarios[] 	usuarios = new usuarios[100];
	    tecnicos[] tecnicos = new tecnicos[50];
	    categoria[] categoria = new categoria[20];
	    chamado[] chamado = new chamado[200];
	    
	    int qtdusuarios = 0, qtdtecnicos = 0, qtdcategoria = 0, qtdchamado = 0;
	
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
	
	    public void cadastrarUsuario(String nome, String email) {
	        if (qtdusuarios < usuarios.length) {
	            usuarios[qtdusuarios++] = new usuarios(nome, email, "Usuario");//nao mexer
	            salvarusuariosarquivo();
	        }
	    }
	
	    public void cadastrartecnicos(String nome, String especialidade, String email) {
	        if (qtdtecnicos < tecnicos.length) {
	            tecnicos[qtdtecnicos++] = new tecnicos(nome, especialidade, email, "Tecnico");//nao mexer
	            salvartecnicosarquivo();
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
	            salvarchamadoarquivo();
	            JOptionPane.showMessageDialog(null, "Chamado aberto!");
	        } else {
	            JOptionPane.showMessageDialog(null, "Erro: IDs inválidos!");
	        }
	    }
	
	
	    public String listausuarios() {
	        String s = "--- USUÁRIOS ---\n";
	        for (int i = 0; i < qtdusuarios; i++) 
	        	if(usuarios[i].getPerfil().equals("Usuario")) {//nao mexer
	            s += usuarios[i].getId() + " - " + usuarios[i].getNome() + " (" + usuarios[i].getEmail() + ")\n";
	        	}
	        
	            return s;
	    }
	
	    public String listatecnicos() {
	        String s = "--- TECNICOS ---\n";
	        for (int i = 0; i < qtdtecnicos; i++) {
	            if (tecnicos[i].getPerfil().equals("Tecnico")) {//nao mexer
	                s += tecnicos[i].getId() + " - " + 
	                     tecnicos[i].getNome() + " | Esp: " + 
	                     tecnicos[i].getEspecialidade() +  
	                     tecnicos[i].getEmail();
	            }
	        }
	        return s;
	    }
	
	    public String listarcategorias() {
	        String s = "--- CATEGORIAS ---\n";
	        for (int i = 0; i < qtdcategoria; i++) {
	            s += categoria[i].getId() + " - " + categoria[i].getNome() + "\n";
	        }
	        return s;
	    }
	    
	    
	    public String listartodoschamados() {
	        if (qtdchamado == 0) return "Nenhum chamado registrado.";
	        String s = "--- LISTA DE CHAMADOS ---\n";
	        for (int i = 0; i < qtdchamado; i++) {
	            s += chamado[i].getId() + " | Status: " + chamado[i].getStatus() + 
	                 " | Usuário: " + chamado[i].getUsuarios().getNome() + 
	                 "\nDesc: " + chamado[i].getDescricao() + "\n----------------\n";
	        }
	        return s;
	    }
	    
	    
	    public String listarchamadousuario(int idusuario) {
	    	String s = "Chamados aberto por você \n";
	    	boolean encontrou = false;
	    	
	    	
	    	for (int i = 0; i < qtdchamado; i++) {
	    		if(chamado[i].getUsuarios().getId() == idusuario) {
	    			s += "ID: " + chamado[i].getId() + "\n" +
	    				"Status: " + chamado[i].getStatus() + "\n" +
	    				"Categoria: " + chamado[i].getCategoria().getNome() + "\n" +
	    				"Descrição: " + chamado[i].getDescricao() + "\n";
	    			encontrou = true;
	    		}
	    	}
	    	
	    	if(!encontrou) {
	    		return "Não a chamados abertos";
	    	}
	    	return s;
	    }
	    
	    public void fecharChamado(int id) {
	        for (int i = 0; i < qtdchamado; i++) {
	            if (chamado[i].getId() == id) {
	                chamado[i].setStatus("Fechado");
	                salvarchamadoarquivo();
	                JOptionPane.showMessageDialog(null, "Chamado #" + id + " encerrado com sucesso!");
	                return;
	            }
	        }
	    }
	    
	    
	    
	    public void salvarusuariosarquivo() {
	        try (PrintWriter out = new PrintWriter(new FileWriter("usuarios.txt"))) {//nao mexer
	            for (int i = 0; i < qtdusuarios; i++) {
	                out.println(usuarios[i].getNome() + ";" + usuarios[i].getEmail() + ";" + usuarios[i].getPerfil());
	            }
	        } catch (IOException e) { System.out.println("Erro ao salvar."); }
	    }
	
	    public void carregarUsuariosArquivo() {
	        try (BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"))) {//nao mexer
	            String linha;
	            while ((linha = br.readLine()) != null) {
	                String[] d = linha.split(";");
	                if (d.length == 3) {
	
	                    if (!d[2].equalsIgnoreCase("Admin")) {
	                        usuarios[qtdusuarios++] = new usuarios(d[0], d[1], d[2]);
	                    }
	                }
	            }
	        } catch (IOException e) { System.out.println("Arquivo não encontrado."); }
	    }
	    
	    public void salvartecnicosarquivo() {
	        try (PrintWriter out = new PrintWriter(new FileWriter("tecnico.txt"))) {//nao mexer
	            for (int i = 0; i < qtdtecnicos; i++) { 
	                out.println(tecnicos[i].getNome() + ";" + tecnicos[i].getEspecialidade() + ";" + tecnicos[i].getEmail() + ";" + tecnicos[i].getPerfil());
	            }
	        } catch (IOException e) { System.out.println("Erro ao salvar."); }
	    }
	    
	    
	    public void carregarTecnicosArquivo() {
	        try (BufferedReader br = new BufferedReader(new FileReader("tecnico.txt"))) {//nao mexer
	            String linha;
	            while ((linha = br.readLine()) != null) {
	                String[] d = linha.split(";");
	                if (d.length == 4) {
	                    tecnicos[qtdtecnicos++] = new tecnicos(d[0], d[1], d[2], d[3]);
	                }
	            }
	        } catch (IOException e) { System.out.println("Arquivo de técnicos não encontrado."); }
	    }
	    
	    public void salvarchamadoarquivo() {
	        try (PrintWriter out = new PrintWriter(new FileWriter("chamado.txt"))) {//nao mexer
	            for (int i = 0; i < qtdchamado; i++) { 
	                out.println(
	                    chamado[i].getDescricao() + ";" + 
	                    chamado[i].getStatus() + ";" + 
	                    chamado[i].getUsuarios().getId() + ";" + 
	                    chamado[i].getTecnicos().getId() + ";" + 
	                    chamado[i].getCategoria().getId()
	                );
	            }
	        } catch (IOException e) { System.out.println("Erro ao salvar chamados."); }
	    }
	    
	    public void carregarchamadoarquivo() {
	        try (BufferedReader br = new BufferedReader(new FileReader("chamado.txt"))) {//nao mexer
	            String linha;
	            while ((linha = br.readLine()) != null) {
	                String[] d = linha.split(";");
	                if (d.length == 5) {
	                    String desc = d[0];
	                    String status = d[1];
	                    int idU = Integer.parseInt(d[2]);
	                    int idT = Integer.parseInt(d[3]);
	                    int idC = Integer.parseInt(d[4]);

	                    usuarios u = procurausu(idU);
	                    tecnicos t = procuratec(idT);
	                    categoria c = procuracat(idC);

	                    if (u != null && t != null && c != null) {
	                        chamado[qtdchamado++] = new chamado(desc, status, u, t, c);
	                    }
	                }
	            }
	        } catch (IOException e) { System.out.println("Arquivo de chamados não encontrado."); }
	    }
	
	    public boolean validarLogin(String email, String perfil) {
	        if (perfil.equals("Admin")) {
	            return email.equalsIgnoreCase("admin"); //nao mexer
	        }
	        if (perfil.equals("Usuario")) {
		        for (int i = 0; i < qtdusuarios; i++) {
		            if (usuarios[i] != null && usuarios[i].getPerfil() != null) {
		                if (usuarios[i].getEmail().equalsIgnoreCase(email) && 
		                    usuarios[i].getPerfil().equals(perfil)) {
		                    return true;
		                }
	                }
	            }
	        }
	        if (perfil.equals("Tecnico")) {
	        	for(int i = 0; i < qtdtecnicos; i++) {
	        		if (tecnicos[i] != null && tecnicos[i].getPerfil() != null) {
	        			if(tecnicos[i].getEmail().equalsIgnoreCase(email) && 
	        			tecnicos[i].getPerfil().equals(perfil)) {
	        				return true;
	        			}
	        		}
	        		
	        	}
	        }
	        return false;
	    }
	    
	    
	    
	    public void salvarcategoriaarquivo() {
	        try (PrintWriter out = new PrintWriter(new FileWriter("categoria.txt"))) {//nao mexer
	            for (int i = 0; i < qtdcategoria; i++) {
	                out.println(categoria[i].getId() + ";" + categoria[i].getNome());
	            }
	        } catch (IOException e) { System.out.println("Erro ao salvar."); }
	    }
	    
	    
	
	    public void carregarCategoriasArquivo() {
	        try (BufferedReader br = new BufferedReader(new FileReader("categoria.txt"))) {//nao mexer
	            String linha;
	            while ((linha = br.readLine()) != null) {
	                String[] dados = linha.split(";");
	                if (dados.length == 2) {
	                    int idArq = Integer.parseInt(dados[0]);
	                    String nomeArq = dados[1];
	
	                    categoria nova = new categoria(nomeArq);
	                    nova.setId(idArq); 
	                    
	                    categoria[qtdcategoria] = nova;
	                    qtdcategoria++;
	 
	                }
	            }
	        } catch (IOException e) { }
	    }
	    
	    
	    public usuarios buscarporemail(String email) {
	        for (int i = 0; i < qtdusuarios; i++) {
	            if (usuarios[i].getEmail().equalsIgnoreCase(email)) {
	                return usuarios[i];
	            }
	        }
	        return null;
	    }
	    
	    
	    public String[] getArrayNomesTecnicos() {
	        String[] nomes = new String[qtdtecnicos];
	        for (int i = 0; i < qtdtecnicos; i++) {
	            nomes[i] = tecnicos[i].getId() + " - " + tecnicos[i].getNome();
	        }
	        return nomes;
	    }
	
	    public String[] getArrayNomesCategorias() {
	        String[] nomes = new String[qtdcategoria];
	        for (int i = 0; i < qtdcategoria; i++) {
	            nomes[i] = categoria[i].getId() + " - " + categoria[i].getNome();
	        }
	        return nomes;
	    }
	    
	    public String[] getArrayChamadosAbertos() {
	        int cont = 0;
	        for (int i = 0; i < qtdchamado; i++) {
	            if (chamado[i].getStatus().equalsIgnoreCase("Aberto")) cont++;
	        }
	        
	        String[] lista = new String[cont];
	        int aux = 0;
	        for (int i = 0; i < qtdchamado; i++) {
	            if (chamado[i].getStatus().equalsIgnoreCase("Aberto")) {
	                lista[aux++] = chamado[i].getId() + " - " + chamado[i].getDescricao();
	            }
	        }
	        return lista;
	    }
	    
	    
	}