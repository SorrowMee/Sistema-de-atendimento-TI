import javax.swing.JOptionPane;

public class main {
    public static void main(String[] args) {
        sistemachamado sistema = new sistemachamado();
        sistema.carregarUsuariosArquivo();
        sistema.carregarCategoriasArquivo();
        sistema.carregarTecnicosArquivo();
        sistema.carregarchamadoarquivo();

        String perfillogado = "";
        usuarios usuarioLogado = null;


        while (true) {
            Object[] perfis = { "Admin", "Tecnico", "Usuario", "Sair" };
            int escolha = JOptionPane.showOptionDialog(null, "Selecione seu perfil:", "Login",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, perfis, perfis[0]);


            if (escolha == -1 || perfis[escolha].equals("Sair")) {
                System.exit(0);
            }
            
            perfillogado = perfis[escolha].toString();

            Object[] opcoesLogin;
            if (perfillogado.equals("Usuario")) {
                opcoesLogin = new Object[] { "Logar", "Cadastrar Novo", "Voltar" };
            } else {
                opcoesLogin = new Object[] { "Logar", "Voltar" };
            }

            int acao = JOptionPane.showOptionDialog(null, "Perfil: " + perfillogado, "Acesso",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesLogin, opcoesLogin[0]);


            if (acao == -1 || opcoesLogin[acao].equals("Voltar")) {
                continue;
            }


            if (opcoesLogin[acao].equals("Cadastrar Novo")) {
                String nome = JOptionPane.showInputDialog("Nome:");
                String email = JOptionPane.showInputDialog("Email:");
                if (nome != null && email != null && !nome.isEmpty() && !email.isEmpty()) {
                    sistema.cadastrarUsuario(nome, email);
                    JOptionPane.showMessageDialog(null, "Cadastrado com sucesso! Agora faça login.");
                }
                continue;
            }


            String emailLogin = JOptionPane.showInputDialog("Email de " + perfillogado + ":");
            

            if (emailLogin == null) continue;

            if (sistema.validarLogin(emailLogin, perfillogado)) {
                usuarioLogado = sistema.buscarporemail(emailLogin);
                JOptionPane.showMessageDialog(null, "Login realizado com sucesso!");
                break; 
            } else {
                JOptionPane.showMessageDialog(null, "Acesso negado! Email ou Perfil incorretos.", "Erro", JOptionPane.ERROR_MESSAGE);
             
            }
        }

        
        while (true) {
            String[] botoes;
            if (perfillogado.equals("Admin")) {
                botoes = new String[] { "Cadastrar usuario", "Cadastrar tecnico", "Cadastrar categoria", "Abrir chamado",
                        "Alterar Status", "Listar usuários", "Listar chamados", "Listar categoria", "Listar tecnicos", "Sair", "Log out" };
            } else if (perfillogado.equals("Tecnico")) {
                botoes = new String[] { "Alterar Status", "Listar chamados", "Sair", "Log out" };
            } else {
                botoes = new String[] { "Abrir chamado", "Meus chamados", "Sair", "Log out" };
            }

            int clique = JOptionPane.showOptionDialog(null, "Olá " + perfillogado, "Menu",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, botoes, botoes[0]);

            if (clique == -1 || botoes[clique].equals("Sair")) {
            
                break; 
            }

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
                            int idU = (usuarioLogado != null) ? usuarioLogado.getId() : 0;
                            sistema.cadastrarchamado(desc, "Aberto", idU, idT, idC);
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao abrir chamado.");
                    }
                    break;
                case "Listar usuários":
                    JOptionPane.showMessageDialog(null, sistema.listausuarios());
                    break;
                case "Listar categoria":
                    JOptionPane.showMessageDialog(null, sistema.listarcategorias());
                    break;
                case "Listar tecnicos":
                    JOptionPane.showMessageDialog(null, sistema.listatecnicos());
                    break;
                case "Listar chamados":
                    JOptionPane.showMessageDialog(null, sistema.listartodoschamados());
                    break;
                case "Alterar Status":
                    String[] abertos = sistema.getArrayChamadosAbertos();
                    if (abertos.length == 0) {
                        JOptionPane.showMessageDialog(null, "Não há chamados abertos.");
                        break;
                    }
                    String sel = (String) JOptionPane.showInputDialog(null, "Fechar chamado:", "Encerrar", 3, null, abertos, abertos[0]);
                    if (sel != null) {
                        sistema.fecharChamado(Integer.parseInt(sel.split(" - ")[0]));
                    }
                    break;
                case "Meus chamados":
                    if (usuarioLogado != null) {
                        JOptionPane.showMessageDialog(null, sistema.listarchamadousuario(usuarioLogado.getId()));
                    }
                    break;
                case "Log out":
                    int confirmar = JOptionPane.showConfirmDialog(null, "Deseja realmente sair da conta?", "Log Out", JOptionPane.YES_NO_OPTION);
                    if (confirmar == JOptionPane.YES_OPTION) {
                        main(args); 
                        return; 
                    }
                    break;
            }
        }
    }
}