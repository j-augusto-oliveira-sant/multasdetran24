import com.multasdetran24.database.ConectorMySQL;

import java.util.Scanner;
import java.io.Console;

public class Main {
    public static void main(String[] args) throws Exception {
        //Console console = System.console();

        //?? UMA SENHA GERAL PARA TODOS USUARIOS?

        ConectorMySQL database = new ConectorMySQL();
        Scanner scan = new Scanner(System.in);
        System.out.println("------------");
        System.out.println("1.Logar");
        System.out.println("2.Registrar");
        System.out.println("------------");
        int escolha_login = scan.nextInt();
        if (escolha_login==1) {
            System.out.println("Criando novo perfil");
            System.out.print("Novo Usuario: ");
            scan.nextLine();
            String nome = scan.nextLine();
            System.out.print("\nSenha: ");
            String s_senha = scan.nextLine();
//      //Nao funciona no console de IDE
//        char[] senha = console.readPassword();
//        StringBuilder sb_senha = new StringBuilder();
//        for (char letra: senha)
//            sb_senha.append(letra);
//        String s_senha = sb_senha.toString();
//        //
            SenhaCrypto cryptosenha = new SenhaCrypto(s_senha);
            String secure_senha = (cryptosenha.getSenha_secure());
            Perfil profile = new Perfil(database, cryptosenha.getSecretKey(), nome, false, secure_senha);
            System.out.println(profile);
        } else if (escolha_login==2){
            System.out.println("---Login---");
            System.out.print("Usuario: ");
            scan.nextLine();
            String nome = scan.nextLine();
            System.out.print("\nSenha: ");
            String s_senha = scan.nextLine();
        }
        int escolha = 2;
        while (escolha!=0) {
            System.out.println("1.mudar senha,2.ver perfil,0.sair");
            escolha = scan.nextInt();
            if (escolha == 1) {
                scan.nextLine();
                String nova_senha = scan.nextLine();
                profile.setSenha(nova_senha);
            } else if (escolha == 2) {
                System.out.println(profile);
            }
        }
        scan.close();
    }
}