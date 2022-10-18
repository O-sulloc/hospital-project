package com.dbEx;

import com.dbEx.userVO.UserVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDAO {

    public void add() throws SQLException, ClassNotFoundException {
        Map<String, String> env = System.getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");

        Class.forName("com.mysql.cj.jdbc.Driver");
        //드라이버를 메모리에 로딩하는 과정. 생략 가능

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

    public UserVO getUserOne(String id) throws SQLException, ClassNotFoundException {
        Map<String, String> env = System.getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(dbHost, dbUser, dbPassword); //mysql db와 연결

        PreparedStatement ps = conn.prepareStatement("SELECT id, name, password FROM users where id=?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();
        UserVO user = new UserVO(rs.getString("id"), rs.getString("name"), rs.getString("password"));

        rs.close();
        ps.close();
        conn.close();

        return user;
    }

    public List<UserVO> getUserAll() throws SQLException, ClassNotFoundException {
        DBConnection db = new DBConnection();

        PreparedStatement ps = db.makeConnection().prepareStatement("SELECT * FROM users");
        ResultSet rs = ps.executeQuery();

        List<UserVO> userList = new ArrayList<>();
        while (rs.next()) {
            UserVO user = new UserVO(rs.getString("id"), rs.getString("name"), rs.getString("password"));
            userList.add(user);
        }

        return userList;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDAO userDAO = new UserDAO();
        //userDAO.add();

        //userDAO.getUserOne("idtest");
        UserVO user = userDAO.getUserOne("idtest");
        System.out.println(user.getName());

        /*
        List<UserVO> userList = new ArrayList<>();
        userList = userDAO.getUserAll();

        for (UserVO userVO : userList) {
            System.out.println(userVO.getName());
            System.out.println(userVO.getId());
            System.out.println(userVO.getPassword());
        }
         */
    }
}
