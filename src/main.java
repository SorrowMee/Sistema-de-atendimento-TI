import javax.swing.JOptionPane;

public class main {
    public static void main(String[] args) {
        sistemachamado sistema = new sistemachamado(); // começa chamando a classe sistema chamado assim podendo utilizar funções que foram criadas do sistema chamado.
        
        //aqui começa carregando os arquivos para serem lidos em funçoes futuras sendo guardadas em txt
        sistema.carregarUsuariosArquivo(); 
        sistema.carregarCategoriasArquivo();
        sistema.carregarTecnicosArquivo();
        sistema.carregarchamadoarquivo();

        //todos estao dentro de um while true individual, quando tentei usar apenas um while true ele chamava o String args tudo de novo assim fazendo o programa reabrir todas as vezes
        while (true) {
            String perfillogado = "";
            usuarios usuarioLogado = null;

            //os botoes que aparece na interface do usuario todos eles sendo conectados por "" para comparação utilizando-se o .equals
            while (true) {
                Object[] perfis = { "Admin", "Tecnico", "Usuario", "Sair" };
                int escolha = JOptionPane.showOptionDialog(null, "Selecione seu perfil:", "Login",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, perfis, perfis[0]);
            // aqui esta dizendo que caso a escolha seja -1 OU (||) seja igual a "sair" ele fecha o programa
                if (escolha == -1 || perfis[escolha].equals("Sair")) {
                    System.exit(0);
                }
                // aqui esta declarando perfillogado = a perfil escolhado representando uma string
                perfillogado = perfis[escolha].toString();
                Object[] opcoeslogin; // aqui ele esta definindo opçoeslogin como object para criar um array
                if (perfillogado.equals("Usuario")) { //apenas usuarios podem se cadastrar, essa função esta detectando se é o usuario para dar as opções de criar logar, criar conta ou sair
                    opcoeslogin = new Object[] { "Logar", "Cadastrar Novo", "Voltar" };
                } else { //caso nao for o usuario so aparece a opção de voltar ou logar.
                    opcoeslogin = new Object[] { "Logar", "Voltar" };
                }

                int acao = JOptionPane.showOptionDialog(null, "Perfil: " + perfillogado, "Acesso",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoeslogin, opcoeslogin[0]);
                //essas opçoes abaixo esta dando funcionalidade para os botoes
                if (acao == -1 || opcoeslogin[acao].equals("Voltar")) { 
                    continue;
                }

                if (opcoeslogin[acao].equals("Cadastrar Novo")) { 
                    String nome = JOptionPane.showInputDialog("Nome:");
                    String email = JOptionPane.showInputDialog("Email:");
                    if (nome != null && email != null && !nome.isEmpty() && !email.isEmpty()) {
                        sistema.cadastrarUsuario(nome, email);
                        JOptionPane.showMessageDialog(null, "Cadastrado com sucesso! Agora faça login.");
                    }
                    continue;
                }
                //aqui esta puxando as funçoes validarlogin e buscar por email, para a obvia validação de login do usuario
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

            //esse bolean foi criado para que caso o usuario aperte log out o programa sai do menu logado, mas continua dentro do loop
            boolean logado = true;
            while (logado) {
                String[] botoes; //aqui esta definindo quais botoes devem aparecer para cada usuario utilizando o if else
                if (perfillogado.equals("Admin")) { 
                    botoes = new String[] { "Cadastrar usuario", "Cadastrar tecnico", "Cadastrar categoria", "Abrir chamado",
                            "Alterar Status", "Listar usuários", "Listar chamados", "Listar categoria", "Listar tecnicos", "Log out", "Sair" };
                } else if (perfillogado.equals("Tecnico")) {
                    botoes = new String[] { "Alterar Status", "Listar chamados", "Log out", "Sair" };
                } else {
                    botoes = new String[] { "Abrir chamado", "Meus chamados", "Log out", "Sair" };
                }
                //seria a saudcao aonde aparece o nome do usuario que logou, com a string saudacao pegando o nome do usuario logado
                String saudacao = (usuarioLogado != null) ? usuarioLogado.getNome() : perfillogado;
                
                int clique = JOptionPane.showOptionDialog(null, "Olá " + saudacao, "Menu",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, botoes, botoes[0]);

                if (clique == -1 || botoes[clique].equals("Sair")) {
                    System.exit(0); 
                }

                String comando = botoes[clique];
                
                //quando o usuario loga ele pode selecionar a opção log out para voltar para o menu de login
                if (comando.equals("Log out")) {
                    int confirmar = JOptionPane.showConfirmDialog(null, "Deseja realmente sair?", "Log Out", JOptionPane.YES_NO_OPTION);
                    if (confirmar == JOptionPane.YES_OPTION) {
                        logado = false; 
                        continue;
                    } else {
                        continue;
                    }
                }
                //aqui esta dando funcionalidade dos botoes de quando o usuario loga
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
                    case "Abrir chamado": // essa e diferente, tive que criar um codigo novo na main direto pois estava dando conflito quando tentava criar em outra classe, prefiria reworkar mas isso iria tomar tempo
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
                        JOptionPane.showMessageDialog(null, sistema.getArrayChamadosAbertos());
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
                }
            }
        }
    }
}