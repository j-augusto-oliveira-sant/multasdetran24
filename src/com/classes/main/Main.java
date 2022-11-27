package com.classes.main;

import com.classes.conexao.ConectorMySQL;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        //Console console = System.console();
        Scanner scan = new Scanner(System.in);
        System.out.println("------------");
        System.out.println("1.Logar");
        System.out.println("2.Registrar");
        System.out.println("------------");

        //      //Nao funciona no console de IDE
//        char[] senha = console.readPassword();
//        StringBuilder sb_senha = new StringBuilder();
//        for (char letra: senha)
//            sb_senha.append(letra);
//        String s_senha = sb_senha.toString();
//        //




        scan.close();
    }
}