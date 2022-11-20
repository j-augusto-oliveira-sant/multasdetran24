package com.multasdetran24.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class ConectorMySQL {

    private final Connection connection;

    public ConectorMySQL() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/multasdetrandb";
        String username = "root";
        String password = "";
        this.connection = DriverManager.getConnection(url, username, password);
    }

    public ResultSet select_query(String query) throws Exception {
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }
    public void add_query(String query) throws Exception {
        Statement statement = connection.createStatement();
        int i = statement.executeUpdate(query);
        if (i < 0)
            System.out.println("ROW NOT INSERTED");
    }
}
