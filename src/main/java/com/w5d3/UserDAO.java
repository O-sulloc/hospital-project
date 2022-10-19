package com.w5d3;

import com.dbEx.userVO.UserVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDAO {

    public void add() throws SQLException, ClassNotFoundException {
        DBConnection dbConnection = new DBConnection();
        Connection conn = dbConnection.getConnection();

        PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO users VALUES (?,?,?)"
        );

        ps.setString(1, "idtest");
        ps.setString(2, "nametest");
        ps.setString(3, "pwtest");

        int status = ps.executeUpdate(); //상태 확인

        ps.close();
        conn.close();

    }

    public UserVO getUserOne(String id) throws SQLException, ClassNotFoundException {
        DBConnection dbConnection = new DBConnection();
        Connection conn = dbConnection.getConnection();

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM users where id=?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();
        UserVO user = new UserVO(rs.getString("id"), rs.getString("name"), rs.getString("pw"));

        rs.close();
        ps.close();
        conn.close();

        return user;
    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDAO userDAO = new UserDAO();
        //userDAO.add();

        userDAO.getUserOne("idtest");

    }
}
