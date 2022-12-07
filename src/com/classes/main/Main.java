package com.classes.main;

import com.classes.BO.*;
import com.classes.DTO.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        //Console console = System.console();
        Scanner scan = new Scanner(System.in);
        //      //Nao funciona no console de IDE
//        char[] senha = console.readPassword();
//        StringBuilder sb_senha = new StringBuilder();
//        for (char letra: senha)
//            sb_senha.append(letra);
//        String s_senha = sb_senha.toString();
//        //

        boolean entrou = false;
        while (!entrou) {
            System.out.println("------------");
            System.out.println("0.Sair");
            System.out.println("1.Logar");
            System.out.println("2.Registrar");
            System.out.println("------------");
            System.out.print(">: ");
            int choice = scan.nextInt();
            String username = "";
            if (choice == 1) {
                System.out.println("---LOGAR---");
                scan.nextLine();
                System.out.print("Nome: ");
                username = scan.nextLine();
                System.out.print("Senha: ");
                String pass = scan.nextLine();
                UsuarioBO usuarioBO = new UsuarioBO();
                Usuario usuario_pesquisado = usuarioBO.procurarPorNome(username);
                String enc_pass = SenhaCrypt.encryptPassword(pass);
                if (usuario_pesquisado.getSenha().equals(enc_pass) && usuario_pesquisado.getNome().equals(username)) {
                    System.out.println("**Entrou**");
                    entrou = true;
                } else {
                    System.out.println("Usuario ou Senha Errados.");
                }

                //Depois de Logado.
                if (entrou && !usuario_pesquisado.isAdmin()) {
                    menu(username, scan);
                } else {
                    adminMenu(username, scan);
                }

            } else if (choice == 2) {
                System.out.println("---REGISTRAR---");
                scan.nextLine();
                System.out.print("Registrar 1.admin 2.usuario: ");
                int choice_register = scan.nextInt();
                scan.nextLine();
                System.out.print("Novo nome: ");
                username = scan.nextLine();
                System.out.print("Nova senha: ");
                String pass = SenhaCrypt.encryptPassword(scan.nextLine());
                UsuarioBO usuarioBo = new UsuarioBO();

                boolean is_admin = false;
                if (choice_register == 1) {
                    System.out.print("Digite senha server: ");
                    String pass_server_admin = scan.nextLine();
                    if (pass_server_admin.equals("89jklmui")) {
                        is_admin = true;
                    }
                }

                Usuario novo_usuario = new Usuario(username, pass, is_admin);
                if (!is_admin) {
                    if (usuarioBo.existe(novo_usuario)) {
                        System.out.println("Já Existe");
                    } else {
                        //CRIANDO USUARIO->PERFIL->CNH
                        usuarioBo.inserir(novo_usuario);
                        //PERFIL
                        criarPerfil(novo_usuario.getNome());
                        //CNH
                        System.out.println("CRIAR CNH --------");
                        int pontuacao = 0;
                        System.out.print("Tipo da carteira: ");
                        String tipo_carteira = scan.nextLine();
                        System.out.print("CPF: ");
                        String cpf = scan.nextLine();
                        System.out.println("Data 1 Habilitacao");
                        String sData = scan.nextLine().replace('/', '-');
                        Date data_1_habilitacao = transformar_data(sData);
                        System.out.println("Data da emissao");
                        sData = scan.nextLine().replace('/', '-');
                        Date emissao = transformar_data(sData);
                        System.out.println("Data da validade");
                        sData = scan.nextLine().replace('/', '-');
                        Date validade = transformar_data(sData);
                        System.out.print("Identidade: ");
                        String identidade = scan.nextLine();
                        System.out.println("Nacionalidade: ");
                        String nacionalidade = scan.nextLine();
                        criarCNH(novo_usuario.getNome(), pontuacao, tipo_carteira, cpf, data_1_habilitacao, emissao,
                                validade, identidade, nacionalidade);
                    }
                } else {
                    //Criando ADMIN user
                    usuarioBo.inserir(novo_usuario);
                }
            } else if (choice == 0) {
                System.out.println("**SAINDO**");
                break;
            }
        }

        scan.close();
    }

    public static void adminMenu(String username, Scanner scan) {
        int escolha = -1;
        UsuarioBO usuarioBO = new UsuarioBO();
        Usuario usuario_logado = usuarioBO.procurarPorNome(username);
        do {
            System.out.println("######################");
            System.out.println("ADMINISTRADOR: " + usuario_logado.getNome() + " |ID:" + usuario_logado.getId());
            System.out.println("######################");
            System.out.println("-----------------------------");
            System.out.println("0.Sair");
            System.out.println("1.Buscar Perfil");
            System.out.println("2.Pesquisar Multas");
            System.out.println("-----------------------------");
            escolha = scan.nextInt();
            scan.nextLine();
            switch (escolha) {
                case 1 -> buscarPerfilAdmin(usuario_logado, scan);
                case 2 -> escolhaCNH(usuario_logado, scan);
            }
        } while (escolha != 0);
    }

    public static void buscarPerfilAdmin(Usuario usuario_logado, Scanner scan) {
        PerfilBO perfilBO = new PerfilBO();
        UsuarioBO usuarioBO = new UsuarioBO();
        CNHBO cnhbo = new CNHBO();
        System.out.println("---BUSCAR PERFIL---");
        System.out.println("-----------------------------");
        System.out.println("1.Voltar menu principal");
        System.out.println("2.Buscar por ID");
        System.out.println("3.Buscar por nome");
        System.out.println("-----------------------------");
        System.out.print(">: ");
        int escolha = scan.nextInt();
        scan.nextLine();
        if (escolha == 2) {
            System.out.print("Usuario ID: ");
            int id_searched = scan.nextInt();
            scan.nextLine();
            Usuario usuario_searched = usuarioBO.procurarPorId(id_searched);
            Perfil perfil_searched = perfilBO.procurarPorUsuarioID(usuario_searched);
            CNH cnh_searched = cnhbo.procurarPorPerfilId(perfil_searched);
            System.out.println(usuario_searched);
            System.out.println(perfil_searched);
            System.out.println(cnh_searched);
            System.out.println("1.Voltar 2.Alterar Pontuacao");
            System.out.print(">: ");
            int escolha_2 = scan.nextInt();
            scan.nextLine();
            if (escolha_2 == 2) {
                alterarPontuacao(cnh_searched, scan);
            }
        } else if (escolha == 3) {
            System.out.print("Buscar por Nome: ");
            String username_searched = scan.nextLine();
            Usuario usuario_searched = usuarioBO.procurarPorNome(username_searched);
            Perfil perfil_searched = perfilBO.procurarPorUsuarioID(usuario_searched);
            CNH cnh_searched = cnhbo.procurarPorPerfilId(perfil_searched);
            System.out.println(usuario_searched);
            System.out.println(perfil_searched);
            System.out.println(cnh_searched);
            System.out.println("1.Voltar 2.Alterar Pontuacao");
            System.out.print(">: ");
            int escolha_2 = scan.nextInt();
            scan.nextLine();
            if (escolha_2 == 2) {
                alterarPontuacao(cnh_searched, scan);
            }
        }

    }

    public static void alterarPontuacao(CNH cnh_searched, Scanner scan) {
        CNHBO cnhbo = new CNHBO();
        System.out.println("---ALTERAR PONTUACAO---");
        System.out.println("-----------------------------");
        System.out.println("1.Voltar");
        System.out.println("2.Adicionar Pontos");
        System.out.println("3.Subtrair Pontos");
        System.out.println("-----------------------------");
        System.out.print(">: ");
        int escolha = scan.nextInt();
        scan.nextLine();
        if (escolha == 2) {
            System.out.print("Pontos para adicionar: ");
            int pontos = scan.nextInt();
            scan.nextLine();
            cnh_searched.setPontuacao((pontos + cnh_searched.getPontuacao()));
            cnhbo.alterarPontuacao(cnh_searched);
        } else if (escolha == 3) {
            System.out.print("Pontos para subtrair: ");
            int pontos = scan.nextInt();
            scan.nextLine();
            cnh_searched.setPontuacao((cnh_searched.getPontuacao() - pontos));
            cnhbo.alterarPontuacao(cnh_searched);
        }
    }

    public static void menu(String username, Scanner scan) {
        int escolha = -1;
        UsuarioBO usuarioBO = new UsuarioBO();
        Usuario usuario_logado = usuarioBO.procurarPorNome(username);
        do {
            System.out.println("######################");
            System.out.println("Bem vindo " + usuario_logado.getNome() + "! |ID:" + usuario_logado.getId());
            System.out.println("######################");
            System.out.println("-----------------------------");
            System.out.println("0.Sair");
            System.out.println("1.Visualisar Perfil");
            System.out.println("2.Visualisar CNH");
            System.out.println("3.Visualisar Veiculos");
            System.out.println("4.Pesquisar Multas");
            System.out.println("-----------------------------");
            System.out.print(">: ");
            escolha = scan.nextInt();
            switch (escolha) {
                case 1 -> escolhaPerfil(usuario_logado, scan);
                case 2 -> escolhaCNH(usuario_logado, scan);
                case 3 -> escolhaVeiculos(usuario_logado, scan);
                case 4 -> escolhaMultas(usuario_logado, scan);
            }
        } while (escolha != 0);
    }

    public static void criarPerfil(String username) {
        //PERFIL
        UsuarioBO usuarioBO = new UsuarioBO();
        Usuario usuario = usuarioBO.procurarPorNome(username);
        PerfilBO perfilBO = new PerfilBO();
        Perfil novo_perfil = new Perfil();
        novo_perfil.setUser_id(usuario.getId());
        perfilBO.inserir(novo_perfil);
    }

    public static void criarCNH(String username, int pontuacao, String tipo_carteira, String cpf, Date data_1_habilitacao,
                                Date emissao, Date validade, String identidade, String nacionalidade) {
        UsuarioBO usuarioBO = new UsuarioBO();
        Usuario usuario = usuarioBO.procurarPorNome(username);
        PerfilBO perfilBO = new PerfilBO();
        Perfil perfil = perfilBO.procurarPorUsuarioID(usuario);
        CNH cnh = new CNH(perfil.getId(), pontuacao, tipo_carteira, cpf, data_1_habilitacao, emissao,
                validade, identidade, nacionalidade);
        CNHBO cnhbo = new CNHBO();
        cnhbo.inserir(cnh);
    }

    public static void escolhaPerfil(Usuario usuario_logado, Scanner scan) {
        System.out.println("---Perfil---");
        PerfilBO perfilBO = new PerfilBO();
        Perfil perfil = perfilBO.procurarPorUsuarioID(usuario_logado);
        System.out.println(perfil);
    }

    public static void escolhaCNH(Usuario usuario_logado, Scanner scan) {
        System.out.println("---CNH---");
        PerfilBO perfilBO = new PerfilBO();
        Perfil perfil = perfilBO.procurarPorUsuarioID(usuario_logado);
        CNHBO cnhbo = new CNHBO();
        CNH cnh = cnhbo.procurarPorPerfilId(perfil);
        System.out.println("Usuario: " + usuario_logado.getNome());
        System.out.println("---------");
        System.out.println("Pontuacao: " + cnh.getPontuacao());
        System.out.println("---------");
        System.out.println("Identidade: " + cnh.getIdentidade());
        System.out.println("CPF: " + cnh.getCpf());
        System.out.println("Nacionalidade: " + cnh.getNacionalidade());
        System.out.println("Tipo da Carteira: " + cnh.getTipo_carteira());
        System.out.println("Data Primeira Habilitacao: " + cnh.getData_1_habilitacao());
        System.out.println("Emissao: " + cnh.getEmissao());
        System.out.println("Validade: " + cnh.getValidade());
        System.out.println("1.Voltar para o menu principal");
        System.out.println("2.Alterar CNH");
        System.out.print(">: ");
        int escolha = scan.nextInt();
        scan.nextLine();
        if (escolha == 2) {
            System.out.println("---ALTERAR CNH---");
            System.out.print("Tipo da carteira: ");
            String tipo_carteira = scan.nextLine();
            System.out.println("Data da emissao");
            String sData = scan.nextLine().replace('/', '-');
            Date emissao = transformar_data(sData);
            System.out.println("Data da validade");
            sData = scan.nextLine().replace('/', '-');
            Date validade = transformar_data(sData);
            System.out.println("Nacionalidade: ");
            String nacionalidade = scan.nextLine();
            CNH cnh_alterada = new CNH();
            cnh_alterada.setCnh_id(cnh.getCnh_id());
            cnh_alterada.setNacionalidade(nacionalidade);
            cnh_alterada.setValidade(validade);
            cnh_alterada.setEmissao(emissao);
            cnh_alterada.setTipo_carteira(tipo_carteira);
            cnhbo.alterar(cnh_alterada);
        }
    }

    public static void escolhaVeiculos(Usuario usuario_logado, Scanner scan) {
        PerfilBO perfilBO = new PerfilBO();
        Perfil perfil = perfilBO.procurarPorUsuarioID(usuario_logado);
        List<CRLV> veiculos = perfilBO.procurarVeiculos(perfil);
        CRLVBO crlvbo = new CRLVBO();
        System.out.println("---Veiculos---");
        for (CRLV veiculo : veiculos) {
            System.out.println("  ______\n" +
                    " /|_||_\\`.__\n" +
                    "(   _    _ _\\   Veiculo: " + veiculo.getModelo() + " |ID:" + veiculo.getCrlv_id() + "\n" +
                    "=`-(_)--(_)-'");
            System.out.println("Modelo: " + veiculo.getModelo());
            System.out.println("Data Documento: " + veiculo.getData_documento());
            System.out.println("Ano Fabricacao: " + veiculo.getAno_fabricacao());
            System.out.println("Ano Modificacao: " + veiculo.getAno_modificacao());
            System.out.println("Tipo: " + veiculo.getTipo());
            System.out.println("Cor: " + veiculo.getCor_predominante());
            System.out.println("Categoria: " + veiculo.getCategoria());
            System.out.println("--------------");
        }
        System.out.println("1.Voltar para o menu principal");
        System.out.println("2.Alterar Veiculo");
        System.out.println("3.Adicionar Novo Veiculo");
        System.out.println("4.Remover Veiculo");
        System.out.print(">: ");
        int escolha = scan.nextInt();
        scan.nextLine();
        if (escolha == 2) {
            System.out.println("---ALTERAR VEICULO---");
            System.out.print("ID do veiculo: ");
            int id_alterar = scan.nextInt();
            scan.nextLine();
            System.out.print("Cor predominante: ");
            String cor_predominante = scan.nextLine();
            System.out.print("Ano ultima modificacao: ");
            String sData = scan.nextLine().replace('/', '-');
            Date ano_modificacao = transformar_data(sData);
            CRLV crlv = new CRLV();
            crlv.setCor_predominante(cor_predominante);
            crlv.setAno_modificacao(ano_modificacao);
            crlv.setCrlv_id(id_alterar);
            crlvbo.alterar(crlv);
        } else if (escolha == 3) {
            System.out.println("---ADICIONAR VEICULO---");
            System.out.print("Tipo: ");
            String tipo = scan.nextLine();
            System.out.print("Cor predominante: ");
            String cor_predominante = scan.nextLine();
            System.out.print("Categoria: ");
            String categoria = scan.nextLine();
            System.out.print("Modelo: ");
            String modelo = scan.nextLine();
            System.out.print("Data Documento: ");
            String sData = scan.nextLine().replace('/', '-');
            Date data_documento = transformar_data(sData);
            System.out.print("Ano fabricacao: ");
            sData = scan.nextLine().replace('/', '-');
            Date ano_fabricacao = transformar_data(sData);
            System.out.print("Ano ultima modificacao: ");
            sData = scan.nextLine().replace('/', '-');
            Date ano_modificacao = transformar_data(sData);
            CRLV crlv = new CRLV(perfil.getId(), tipo, cor_predominante, categoria, modelo,
                    data_documento, ano_fabricacao, ano_modificacao);
            crlvbo.inserir(crlv);
        } else if (escolha == 4) {
            System.out.println("---REMOVER VEICULO---");
            System.out.print("Index Veiculo: ");
            int id_remover = scan.nextInt();
            scan.nextLine();
            CRLV veiculo_remover = new CRLV();
            veiculo_remover.setCrlv_id(id_remover);
            crlvbo.excluir(veiculo_remover);
        }
    }

    public static void escolhaMultas(Usuario usuario_logado, Scanner scan) {
        MultaBO multaBO = new MultaBO();
        System.out.println("---Multas---");
        System.out.println("1.Voltar para o menu principal");
        System.out.println("2.Pesquisar id Multa");
        System.out.println("3.Multas leve");
        System.out.println("4.Multas media");
        System.out.println("5.Multas grave");
        System.out.println("6.Multas gravissima");
        System.out.print(">: ");
        int escolha = scan.nextInt();
        if (escolha == 2) {
            System.out.println("---PESQUISAR MULTA---");
            Multa multa_to_search = new Multa();
            int id_multa_searched = scan.nextInt();
            scan.nextLine();
            multa_to_search.setMulta_id(id_multa_searched);
            Multa multa_searched = multaBO.procurarPorId(multa_to_search);
            System.out.println(multa_searched);
        } else if (escolha == 3) {
            System.out.println("---MULTAS LEVE---");
            List<Multa> multas_searched = multaBO.procurarMultasPorTipo(1);
            System.out.println(mostrarMultasPesquisadas(multas_searched));
        } else if (escolha == 4) {
            System.out.println("---MULTAS MEDIA---");
            List<Multa> multas_searched = multaBO.procurarMultasPorTipo(1);
            System.out.println(mostrarMultasPesquisadas(multas_searched));
        } else if (escolha == 5) {
            System.out.println("---MULTAS GRAVE---");
            List<Multa> multas_searched = multaBO.procurarMultasPorTipo(1);
            System.out.println(mostrarMultasPesquisadas(multas_searched));
        } else if (escolha == 6) {
            System.out.println("---MULTAS GRAVISSIMA---");
            List<Multa> multas_searched = multaBO.procurarMultasPorTipo(1);
            System.out.println(mostrarMultasPesquisadas(multas_searched));
        }
    }

    public static String mostrarMultasPesquisadas(List<Multa> multas_searched) {
        StringBuilder sb = new StringBuilder();
        for (Multa multa : multas_searched) {
            String tipo = "";
            if (multa.getTipo_multa() == 1){
                tipo = "Leve";
            } else if (multa.getTipo_multa() == 2){
                tipo = "Media";
            }   else if (multa.getTipo_multa() == 3){
                tipo = "Grave";
            }   else if (multa.getTipo_multa() == 4) {
                tipo = "Gravissima";
            }
            sb.append("ID: ").append(multa.getMulta_id()).append("\n");
            sb.append("CODIGO: ").append(multa.getCodigo()).append("\n");
            sb.append("DESCRICAO: ").append(multa.getDescricao()).append("\n");
            sb.append("GRAVIDADE: ").append(tipo).append("\n");
            sb.append("PONTOS: ").append(multa.getPontos()).append("\n");
            sb.append("VALOR: R$").append(multa.getValor()).append("\n");
            sb.append("BASE LEGAL: ").append(multa.getBase_legal());
            sb.append("--------------").append("\n");
        }
        return sb.toString();
    }

    public static void ClearConsole(){
        try{
            String operatingSystem = System.getProperty("os.name");//Check the current operating system

            if(operatingSystem.contains("Windows")){
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            } else {
                ProcessBuilder pb = new ProcessBuilder("clear");
                Process startProcess = pb.inheritIO().start();

                startProcess.waitFor();
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static Date transformar_data(String sData) {
        String regex_date = "^(0?[1-9]|[12][0-9]|3[01])-(0?[1-9]|1[012])-([12][0-9]{3})$";
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(false);
        if (sData.matches(regex_date)) {
            Date data_convert = null;
            try {
                data_convert = sdf.parse(sData);
                return data_convert;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}