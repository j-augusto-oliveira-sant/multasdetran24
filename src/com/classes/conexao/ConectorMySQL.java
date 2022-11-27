package com.classes.conexao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConectorMySQL {
    // **tem que adicionar o module no project-structure intellij
    final static String NOME_DO_BANCO = "multasdetrandb";
    public static Connection conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/" + NOME_DO_BANCO;
            return DriverManager.getConnection(url,"root","");
        } catch (Exception e) {
//            System.err.println("Erro: " + e.toString());
//            e.printStackTrace();
            return null;
        }
    }
}
