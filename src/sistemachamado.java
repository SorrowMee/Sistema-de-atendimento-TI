	import javax.swing.JOptionPane;
	import java.io.*;
	//aqui ele esta declarando as arrays
	public class sistemachamado {
	    usuarios[] 	usuarios = new usuarios[100];
	    tecnicos[] tecnicos = new tecnicos[50];
	    categoria[] categoria = new categoria[20];
	    chamado[] chamado = new chamado[200];
	    //declarando valores
	    int qtdusuarios = 0, qtdtecnicos = 0, qtdcategoria = 0, qtdchamado = 0;
	
	    public usuarios procurausu(int id) { //um loop nao onde ele passa por cada usuario retornando-os 
	        for (int i = 0; i < qtdusuarios; i++) {
	            if (usuarios[i].getId() == id) return usuarios[i];
	        }
	        return null;
	    }
	
	    public tecnicos procuratec(int id) {
	        for (int i = 0; i < qtdtecnicos; i++) {
	            if (tecnicos[i].getId() == id) return tecnicos[i]; //outro loop
	        }
	        return null;
	    }
	
	    public categoria procuracat(int id) {
	        for (int i = 0; i < qtdcategoria; i++) {
	            if (categoria[i].getId() == id) return categoria[i]; //outro loop
	        }
	        return null;
	    }
	    //essa função esta cadastrando os usuarios, o if fazendo o trabalho de verificação vendo se a array nao esta cheia
	    public void cadastrarUsuario(String nome, String email) {
	        if (qtdusuarios < usuarios.length) {
	            usuarios[qtdusuarios++] = new usuarios(nome, email, "Usuario");//nao mexer
	            salvarusuariosarquivo();
	        }
	    }
	    //mesma função
	    public void cadastrartecnicos(String nome, String especialidade, String email) {
	        if (qtdtecnicos < tecnicos.length) {
	            tecnicos[qtdtecnicos++] = new tecnicos(nome, especialidade, email, "Tecnico");//nao mexer
	            salvartecnicosarquivo();
	        }
	    }
	    //mesma função
	    public void cadastrarcategoria(String nome) {
	        if (qtdcategoria < categoria.length) {
	            categoria[qtdcategoria] = new categoria(nome);
	            qtdcategoria++;
	            salvarcategoriaarquivo();
	        }
	    }
	    //essa função e um teste foi um teste mas logo percebi que era complicada demais, ela esta em teste apenas no admin, a função de abrir chamada foi codada diretamente no main
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
	
	    //essa função retorna os dados do usuario como uma lista facilitando a visualização no option panel
	    public String listausuarios() {
	        String s = "----USUÁRIOS----\n";
	        for (int i = 0; i < qtdusuarios; i++) 
	        	if(usuarios[i].getPerfil().equals("Usuario")) {//nao mexer
	            s += usuarios[i].getId() + " - " + usuarios[i].getNome() + " (" + usuarios[i].getEmail() + ")\n";
	        	}
	        
	            return s;
	    }
	    //mesma lista
	    public String listatecnicos() {
	        String s = "----TECNICOS----\n";
	        for (int i = 0; i < qtdtecnicos; i++) {
	            if (tecnicos[i].getPerfil().equals("Tecnico")) {//nao mexer
	                s += tecnicos[i].getId() + " - " + 
	                     tecnicos[i].getNome() + "  Esp: " + 
	                     tecnicos[i].getEspecialidade() +  " Email: " +
	                     tecnicos[i].getEmail();
	            }
	        }
	        return s;
	    }
	  //mesma lista
	    public String listarcategorias() {
	        String s = "----CATEGORIAS----\n";
	        for (int i = 0; i < qtdcategoria; i++) {
	            s += categoria[i].getId() + " - " + categoria[i].getNome() + "\n";
	        }
	        return s;
	    }
	    
	  //mesma lista
	    public String listartodoschamados() {
	        if (qtdchamado == 0) return "Nenhum chamado registrado.";
	        String s = "----LISTA DE CHAMADOS----\n";
	        for (int i = 0; i < qtdchamado; i++) {
	            s += "ID: " + chamado[i].getId() + "\n" + 
	            	"Status: " + chamado[i].getStatus() + "\n" +
	                 "Usuário: " + chamado[i].getUsuarios().getNome() + "\n" +
	                 "Desc: " + chamado[i].getDescricao() +  "\n" +
	                 "Email: " + chamado[i].getUsuarios().getEmail() + "\n" + 
	                 "Categoria: " +  chamado[i].getCategoria().getNome() + "\n" + "\n----------------\n";
	        }
	        return s;
	    }
	    
	  //mesma lista, mas essa função e usada quando um usuario loga, fazendo ele ver apenas os seus chamados
	    public String listarchamadousuario(int idusuario) {
	    	String s = "Chamados aberto por você \n";
	    	boolean encontrou = false;
	    	
	    	
	    	for (int i = 0; i < qtdchamado; i++) {
	    		if(chamado[i].getUsuarios().getId() == idusuario) {
	    			s += "ID: " + chamado[i].getId() + "\n" +
	    				"Status: " + chamado[i].getStatus() + "\n" +
	    				"Categoria: " + chamado[i].getCategoria().getNome() + "\n" +
	    				"Descrição: " + chamado[i].getDescricao() + "\n" + "\n----------------------\n";
	    			encontrou = true;
	    		}
	    	}
	    	
	    	if(!encontrou) {
	    		return "Não a chamados abertos";
	    	}
	    	return s;
	    }
	    //essa função e do tecnico, dando a habilidade de fechar chamados
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
	    
	    
	    //essa função criar arquivos em txt,criando ; entre os dados para ajudar a ser lido no carregamento, dando uma opção de ser guardado permanentemente mesmo se o usuario fecha e re-abre o programa
	    public void salvarusuariosarquivo() {
	        try (PrintWriter out = new PrintWriter(new FileWriter("usuarios.txt"))) {//nao mexer
	            for (int i = 0; i < qtdusuarios; i++) {
	                out.println(usuarios[i].getNome() + ";" + 
	                			usuarios[i].getEmail() + ";" + 
	                			usuarios[i].getPerfil());
	            }
	        } catch (IOException e) { System.out.println("Erro ao salvar."); }
	    }
	//essa função esta lendo o que esta no arquivo txt,usando o ; para dividir a atribuições de dados
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
	    //mesma ideia do salvarusuario
	    public void salvartecnicosarquivo() {
	        try (PrintWriter out = new PrintWriter(new FileWriter("tecnico.txt"))) {//nao mexer
	            for (int i = 0; i < qtdtecnicos; i++) { 
	                out.println(tecnicos[i].getNome() + ";" + 
	                			tecnicos[i].getEspecialidade() + ";" + 
	                			tecnicos[i].getEmail() + ";" + 
	                			tecnicos[i].getPerfil());
	            }
	        } catch (IOException e) { System.out.println("Erro ao salvar."); }
	    }
	    
	    //mesma ideia do carregarusuario
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
	  //mesma ideia do salvarusuario
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
	  //mesma ideia do carregarusuario
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
	  //mesma ideia do salvarusuario
	    public void salvarcategoriaarquivo() {
	        try (PrintWriter out = new PrintWriter(new FileWriter("categoria.txt"))) {//nao mexer
	            for (int i = 0; i < qtdcategoria; i++) {
	                out.println(categoria[i].getId() + ";" + categoria[i].getNome());
	            }
	        } catch (IOException e) { System.out.println("Erro ao salvar."); }
	    }
	  //mesma ideia do carregarusuario
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
	    
	    //a validação de login e uma booleana que utiliza um loop, que procura pelo email do usuario ou do tecnico, se ele existir, esta logado, se nao existir, retorna como invalido
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
	    
	    //enquanto o de cima esta validando dados, essa daqui esta buscando e retornando dados
	    public usuarios buscarporemail(String email) {
	        for (int i = 0; i < qtdusuarios; i++) {
	            if (usuarios[i].getEmail().equalsIgnoreCase(email)) {
	                return usuarios[i];
	            }
	        }
	        return null;
	    }
	    
	    //esse metodo esta criando uma versao legivel dos arrays para ser usada no JOptionPane, tive problemas para o JOP carregar displays e essa foi o "fix" que eu encontrei
	    public String[] getArrayNomesTecnicos() {
	        String[] nomes = new String[qtdtecnicos];
	        for (int i = 0; i < qtdtecnicos; i++) {
	            nomes[i] = tecnicos[i].getId() + " - " + tecnicos[i].getNome();
	        }
	        return nomes;
	    }
	//mesma ideia
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