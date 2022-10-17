package com.dbEx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class UserDAO {
    public void add() throws SQLException, ClassNotFoundException {
        Map<String, String> env = System.getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(dbHost, dbUser, dbPassword); //mysql db와 연결
        PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO users(id, name, password) VALUES (?,?,?)"
        );
        ps.setString(1, "idtest");
        ps.setString(2, "nametest");
        ps.setString(3, "pwtest");

        int status = ps.executeUpdate(); //상태 확인
        System.out.println(status);
        ps.close();
        conn.close();

    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDAO userDAO = new UserDAO();
        userDAO.add();
    }
}
