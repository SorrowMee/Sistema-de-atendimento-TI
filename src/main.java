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
            String n = JOptionPane.showInputDialog("Nome:");
            String e = JOptionPane.showInputDialog("Email:");
            sistema.cadastrarUsuario(n, e);
            JOptionPane.showMessageDialog(null, "Cadastrado! Agora faça login.");
        } else if (acao == -1 || opcoesLogin[acao].equals("Sair")) return;

        String emailLogin = JOptionPane.showInputDialog("Email de " + perfillogado + ":");
        if (!sistema.validarLogin(emailLogin, perfillogado)) {
            JOptionPane.showMessageDialog(null, "Acesso negado!");
            return;
        }


        int opcao = -1;
        while (opcao != 0) {
            String[] botoes;
            if (perfillogado.equals("Admin")) {
                botoes = new String[]{"Cadastrar usuario", "Cadastrar tecnico", "Cadastrar categoria","Abrir Chamado","Alterar Status","Listar usuários","Listar chamados","Listar categoria", "Listar tecnicos", "Sair"};
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
                    sistema.cadastrartecnicos(JOptionPane.showInputDialog("Nome:"), JOptionPane.showInputDialog("Especialidade:"));
                    break;
                case "Cadastrar categoria":
                    sistema.cadastrarcategoria(JOptionPane.showInputDialog("Nome da Categoria:"));
                    break;
                case "Abrir Chamado":
                	
                	try {
                    String desc = JOptionPane.showInputDialog("Descrição:");
                    int idU = Integer.parseInt(JOptionPane.showInputDialog(sistema.listausuarios() + "ID Usuário:"));
                    int idT = Integer.parseInt(JOptionPane.showInputDialog(sistema.listatecnicos() + "ID Técnico:"));
                    int idC = Integer.parseInt(JOptionPane.showInputDialog(sistema.listarcategorias() + "ID Categoria:"));
                    sistema.cadastrarchamado(desc, "Aberto", idU, idT, idC);
                    break;
                    } catch (Exception ex) {
                    	JOptionPane.showMessageDialog(null, "Erro:Digite apenas numero");
                    	break;
                    }
                	
                case "Listar usuários":
                	JOptionPane.showMessageDialog(null, sistema.listausuarios());
                	break;
                case "Listar categoria":
                	JOptionPane.showMessageDialog(null, sistema.listarcategorias());
                	break;
                case "Listar tecnicos":
                	JOptionPane.showMessageDialog(null, sistema.listatecnicos());
            }
        }
    }
}