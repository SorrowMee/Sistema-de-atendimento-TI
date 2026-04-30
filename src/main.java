import javax.swing.JOptionPane;

public class main {
    public static void main(String[] args) {
        sistemachamado sistema = new sistemachamado();
        sistema.carregarUsuariosArquivo(); 
        sistema.carregarCategoriasArquivo();
        sistema.carregarTecnicosArquivo();

        Object[] perfis = { "Admin", "Tecnico", "Usuario" };
        int escolha = JOptionPane.showOptionDialog(null, "Selecione seu perfil:", "Login", 
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, perfis, perfis[0]);
        if (escolha == -1) return;
        String perfillogado = perfis[escolha].toString();

        Object[] opcoesLogin;
        if (perfillogado.equals("Usuario")) {
            opcoesLogin = new Object[]{ "Logar", "Cadastrar Novo", "Sair" };
        } else {
            opcoesLogin = new Object[]{ "Logar", "Sair" };
        }
        
        int acao = JOptionPane.showOptionDialog(null, "Perfil: " + perfillogado, "Acesso", 
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesLogin, opcoesLogin[0]);

        if (acao != -1 && opcoesLogin[acao].equals("Cadastrar Novo")) {
            sistema.cadastrarUsuario(JOptionPane.showInputDialog("Nome:"), JOptionPane.showInputDialog("Email:"));
            JOptionPane.showMessageDialog(null, "Cadastrado! Agora faça login.");
        } else if (acao == -1 || opcoesLogin[acao].equals("Sair")) return;

        String emailLogin = JOptionPane.showInputDialog("Email de " + perfillogado + ":");
        if (!sistema.validarLogin(emailLogin, perfillogado)) {
            JOptionPane.showMessageDialog(null, "Acesso negado!");
            return;
        }
        
        
        usuarios usuarioLogado = sistema.buscarporemail(emailLogin);

        while (true) {
            String[] botoes;
            if (perfillogado.equals("Admin")) {
                botoes = new String[]{"Cadastrar usuario", "Cadastrar tecnico", "Cadastrar categoria","Abrir chamado","Alterar Status","Listar usuários","Listar chamados","Listar categoria", "Listar tecnicos", "Sair"};
            } else if (perfillogado.equals("Tecnico")) {
                botoes = new String[]{"Alterar Status", "Listar chamados", "Sair"};
            } else {
                botoes = new String[]{"Abrir chamado", "Meus chamados", "Sair"};
            }

            int clique = JOptionPane.showOptionDialog(null, "Olá " + perfillogado, "Menu", 
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, botoes, botoes[0]);

            if (clique == -1 || botoes[clique].equals("Sair")) break;

            String comando = botoes[clique];
            switch (comando) {
                case "Cadastrar usuario":
                    sistema.cadastrarUsuario(JOptionPane.showInputDialog("Nome:"), JOptionPane.showInputDialog("Email:"));
                    break;
                case "Cadastrar tecnico":
                    sistema.cadastrartecnicos(JOptionPane.showInputDialog("Nome:"), JOptionPane.showInputDialog("Especialidade:"), JOptionPane.showInputDialog("Email"));
                    break;
                case "Cadastrar categoria":
                    sistema.cadastrarcategoria(JOptionPane.showInputDialog("Nome da Categoria:"));
                    break;
                case "Abrir chamado":
                    try {
                        String desc = JOptionPane.showInputDialog("Descreva o problema:");
                        if (desc == null || desc.isEmpty()) break;

                        String tecSel = (String) JOptionPane.showInputDialog(null, "Técnico:", "Seleção", 3, null, sistema.getArrayNomesTecnicos(), null);
                        String catSel = (String) JOptionPane.showInputDialog(null, "Categoria:", "Seleção", 3, null, sistema.getArrayNomesCategorias(), null);

                        if (tecSel != null && catSel != null) {
                            int idT = Integer.parseInt(tecSel.split(" - ")[0]);
                            int idC = Integer.parseInt(catSel.split(" - ")[0]);
                            
                            // Se for Admin abrindo chamado, usuarioLogado é null, então usamos um ID padrão ou pedimos ID
                            int idU = (usuarioLogado != null) ? usuarioLogado.getId() : 0; 
                            sistema.cadastrarchamado(desc, "Aberto", idU, idT, idC);
                        }
                    } catch (Exception ex) { JOptionPane.showMessageDialog(null, "Erro ao abrir."); }
                    break;
                	
                case "Listar usuários":
                	JOptionPane.showMessageDialog(null, sistema.listausuarios());
                	break;
                case "Listar categoria":
                	JOptionPane.showMessageDialog(null, sistema.listarcategorias());
                	break;
                case "Listar tecnicos":
                	JOptionPane.showMessageDialog(null, sistema.listatecnicos());
                    break; // Faltava este break!
                case "Listar chamados":
                	JOptionPane.showMessageDialog(null, sistema.listarTodosChamados());
                    break;
                case "Alterar Status":
                	String[] abertos = sistema.getArrayChamadosAbertos();
                	if (abertos.length == 0) {
                        JOptionPane.showMessageDialog(null, "Não há chamados abertos.");
                        break;
                    }
                	String sel = (String) JOptionPane.showInputDialog(null, "Fechar chamado:", "Encerrar", 3, null, abertos, abertos[0]);
                	if(sel != null) {
                		sistema.fecharChamado(Integer.parseInt(sel.split(" - ")[0]));
                	}
                	break;
            }
        }
    }
}