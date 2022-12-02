package com.classes.main;

import com.classes.BO.UsuarioBO;
import com.classes.DTO.Usuario;
import com.classes.conexao.ConectorMySQL;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
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
                usuarioBo.inserir(novo_usuario);
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
                escolha = scan.nextInt();
                switch (escolha) {
                    case 1 -> escolhaPerfil(usuario_logado);
                    case 2 -> escolhaCNH(usuario_logado);
                    case 3 -> escolhaVeiculos(usuario_logado);
                    case 4 -> escolhaMultas(usuario_logado);
                }
            }while(escolha!=0);
        }

        scan.close();
    }
    public static void escolhaPerfil(Usuario usuario_logado){
        System.out.println("---Perfil---");
        System.out.println("Entrando");
    }
    public static void escolhaCNH(Usuario usuario_logado){
        System.out.println("---CNH---");
        System.out.println('.');
    }
    public static void escolhaVeiculos(Usuario usuario_logado){
        System.out.println("---Veiculos---");
        System.out.println('.');
    }
    public static void escolhaMultas(Usuario usuario_logado){
        System.out.println("---Multas---");
        System.out.println('.');
    }
}