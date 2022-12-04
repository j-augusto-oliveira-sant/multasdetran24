package com.classes.main;

import com.classes.BO.CNHBO;
import com.classes.BO.PerfilBO;
import com.classes.DTO.CNH;
import com.classes.DTO.Perfil;
import com.classes.BO.UsuarioBO;
import com.classes.DTO.Usuario;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        //Console console = System.console();
        Scanner scan = new Scanner(System.in);
        System.out.println("------------");
        System.out.println("1.Logar");
        System.out.println("2.Registrar");
        System.out.println("------------");
        int choice = scan.nextInt();
        //      //Nao funciona no console de IDE
//        char[] senha = console.readPassword();
//        StringBuilder sb_senha = new StringBuilder();
//        for (char letra: senha)
//            sb_senha.append(letra);
//        String s_senha = sb_senha.toString();
//        //

        boolean entrou = false;
        String username = "";
        if (choice==1){
            System.out.println("LOGAR--");
            scan.nextLine();
            System.out.print("Nome: ");
            username = scan.nextLine();
            System.out.print("Senha: ");
            String pass = scan.nextLine();
            UsuarioBO usuarioBO = new UsuarioBO();
            Usuario usuario_pesquisado = usuarioBO.procurarPorNome(username);
            String enc_pass = SenhaCrypt.encryptPassword(pass);
            if (usuario_pesquisado.getSenha().equals(enc_pass) && usuario_pesquisado.getNome().equals(username)){
                System.out.println("**Entrou**");
                entrou = true;
            } else {
                System.out.println("Usuario ou Senha Errados.");
            }
        } else if (choice==2) {
            System.out.println("REGISTRAR--");
            scan.nextLine();
            System.out.print("Novo nome: ");
            username = scan.nextLine();
            System.out.print("Nova senha: ");
            String pass = SenhaCrypt.encryptPassword(scan.nextLine());
            UsuarioBO usuarioBo = new UsuarioBO();
            Usuario novo_usuario = new Usuario(username, pass, false);
            if (usuarioBo.existe(novo_usuario)){
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
                String sData = scan.nextLine().replace('/','-');
                Date data_1_habilitacao = transformar_data(sData);
                System.out.println("Data da emissao");
                sData = scan.nextLine().replace('/','-');
                Date emissao = transformar_data(sData);
                System.out.println("Data da validade");
                sData = scan.nextLine().replace('/','-');
                Date validade = transformar_data(sData);
                System.out.print("Identidade: ");
                String identidade = scan.nextLine();
                System.out.println("Nacionalidade: ");
                String nacionalidade = scan.nextLine();
                criarCNH(novo_usuario.getNome(),pontuacao,tipo_carteira,cpf,data_1_habilitacao,emissao,
                        validade,identidade,nacionalidade);
            }
        }

        //Depois de Logado.
        if (entrou){
            int escolha = -1;
            UsuarioBO usuarioBO = new UsuarioBO();
            Usuario usuario_logado = usuarioBO.procurarPorNome(username);
            do {
                System.out.println("  ______\n" +
                        " /|_||_\\`.__\n" +
                        "(   _    _ _\\   Bem vindo "+usuario_logado.getNome()+"! |ID:"+usuario_logado.getId()+"\n" +
                        "=`-(_)--(_)-'");
                System.out.println("-----------------------------");
                System.out.println("0.Sair");
                System.out.println("1.Visualisar Perfil");
                System.out.println("2.Visualisar CNH");
                System.out.println("3.Visualisar Veiculos");
                System.out.println("4.Pesquisar Multa");
                System.out.println("-----------------------------");
                System.out.print(">: ");
                escolha = scan.nextInt();
                switch (escolha) {
                    case 1 -> escolhaPerfil(usuario_logado,scan);
                    case 2 -> escolhaCNH(usuario_logado,scan);
                    case 3 -> escolhaVeiculos(usuario_logado,scan);
                    case 4 -> escolhaMultas(usuario_logado,scan);
                }
            }while(escolha!=0);
        }

        scan.close();
    }
    public static void criarPerfil(String username){
        //PERFIL
        UsuarioBO usuarioBO = new UsuarioBO();
        Usuario usuario = usuarioBO.procurarPorNome(username);
        PerfilBO perfilBO = new PerfilBO();
        Perfil novo_perfil = new Perfil();
        novo_perfil.setUser_id(usuario.getId());
        perfilBO.inserir(novo_perfil);
    }
    public static void criarCNH(String username,int pontuacao,String tipo_carteira,String cpf, Date data_1_habilitacao,
                                Date emissao, Date validade, String identidade, String nacionalidade){
        UsuarioBO usuarioBO = new UsuarioBO();
        Usuario usuario = usuarioBO.procurarPorNome(username);
        PerfilBO perfilBO = new PerfilBO();
        Perfil perfil = perfilBO.procurarPorUsuarioID(usuario);
        CNH cnh = new CNH(perfil.getId(),pontuacao,tipo_carteira,cpf,data_1_habilitacao,emissao,
                validade,identidade,nacionalidade);
        CNHBO cnhbo = new CNHBO();
        cnhbo.inserir(cnh);
    }
    public static void escolhaPerfil(Usuario usuario_logado,Scanner scan){
        System.out.println("---Perfil---");
        PerfilBO perfilBO = new PerfilBO();
        Perfil perfil = perfilBO.procurarPorUsuarioID(usuario_logado);
        System.out.println(perfil);
    }
    public static void escolhaCNH(Usuario usuario_logado,Scanner scan){
        System.out.println("---CNH---");
        PerfilBO perfilBO = new PerfilBO();
        Perfil perfil = perfilBO.procurarPorUsuarioID(usuario_logado);
        CNHBO cnhbo = new CNHBO();
        CNH cnh = cnhbo.procurarPorPerfilId(perfil);
        System.out.println("Usuario: "+usuario_logado.getNome());
        System.out.println("Identidade: "+cnh.getIdentidade());
        System.out.println("CPF: "+cnh.getCpf());
        System.out.println("Nacionalidade: "+cnh.getNacionalidade());
        System.out.println("Tipo da Carteira: "+cnh.getTipo_carteira());
        System.out.println("Pontuacao: "+cnh.getPontuacao());
        System.out.println("Data Primeira Habilitacao: "+cnh.getData_1_habilitacao());
        System.out.println("Emissao: "+cnh.getEmissao());
        System.out.println("Validade: "+cnh.getValidade());
        System.out.println("1.Voltar para o menu principal");
        System.out.println("2.Alterar CNH");
        System.out.print(">: ");
        int escolha = scan.nextInt();
        if (escolha==2){
            System.out.println("---ALTERAR CNH--");
        }
    }
    public static void escolhaVeiculos(Usuario usuario_logado,Scanner scan){
        System.out.println("---Veiculos---");
        System.out.println('.');
        System.out.println("1.Voltar para o menu principal");
        System.out.println("2.Alterar Veiculo");
        System.out.print(">: ");
        int escolha = scan.nextInt();
        if (escolha==2){
            System.out.println("---ALTERAR VEICULO--");
        }
    }
    public static void escolhaMultas(Usuario usuario_logado,Scanner scan){
        System.out.println("---Multas---");
        System.out.println('.');
        System.out.println("1.Voltar para o menu principal");
        System.out.println("2.Pesquisar Multa");
        System.out.print(">: ");
        int escolha = scan.nextInt();
        if (escolha==2){
            System.out.println("---PESQUISAR MULTA--");
        }
    }
    public static Date transformar_data(String sData){
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