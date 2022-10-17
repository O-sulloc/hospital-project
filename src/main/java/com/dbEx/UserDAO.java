package com.dbEx;

import com.dbEx.domain.UserVO;
import com.mysql.cj.protocol.Resultset;

import java.sql.*;
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

        ps.close();
        conn.close();

    }

    public UserVO getUser(String id) throws SQLException, ClassNotFoundException {
        Map<String, String> env = System.getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(dbHost, dbUser, dbPassword); //mysql db와 연결

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM users where id=?");
        ps.setString(1,id);

        ResultSet rs = ps.executeQuery();
        rs.next();
        UserVO user = new UserVO(rs.getString("id"), rs.getString("name"), rs.getString("password"));

        rs.close();
        ps.close();
        conn.close();

        return user;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDAO userDAO = new UserDAO();
        //userDAO.add();

        userDAO.getUser("idtest");
        UserVO user = userDAO.getUser("idtest");
        System.out.println(user.getName());
    }
}
