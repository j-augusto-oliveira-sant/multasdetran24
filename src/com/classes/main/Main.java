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
        if (choice==1){
            System.out.println("LOGAR--");
            scan.nextLine();
            System.out.println("Nome");
            String username = scan.nextLine();
            System.out.println("Senha");
            String pass = scan.nextLine();
            UsuarioBO usuarioBO = new UsuarioBO();
            Usuario usuario_pesquisado = usuarioBO.procurarPorNome(username);
            String enc_pass = SenhaCrypt.encryptPassword(pass);
            if (usuario_pesquisado.getSenha().equals(enc_pass) && usuario_pesquisado.getNome().equals(username)){
                System.out.println("**Entrou**");
            } else {
                System.out.println("Usuario ou Senha Errados.");
            }
        } else if (choice==2) {
            System.out.println("REGISTRAR--");
            scan.nextLine();
            System.out.println("Novo nome:");
            String username = scan.nextLine();
            System.out.println("Nova senha:");
            String pass = SenhaCrypt.encryptPassword(scan.nextLine());
            UsuarioBO usuarioBo = new UsuarioBO();
            Usuario novo_usuario = new Usuario(username, pass, false);
            if (usuarioBo.existe(novo_usuario)){
                System.out.println("Já Existe");
            } else {
                usuarioBo.inserir(novo_usuario);
            }
        }



        scan.close();
    }
}