package com.line.dao;

import com.dbEx.userVO.UserVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class UserDAOAbstract {
    public abstract Connection makeConnection() throws ClassNotFoundException, SQLException;

    public void add(UserVO userVO) throws SQLException, ClassNotFoundException {

        Connection conn = makeConnection();

        PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO users(id, name, password) VALUES (?,?,?)"
        );

        ps.setString(1, userVO.getId());
        ps.setString(2, userVO.getName());
        ps.setString(3, userVO.getPassword());

        int status = ps.executeUpdate(); //상태 확인

        ps.close();
        conn.close();

    }

    public UserVO getUserOne(String id) throws SQLException, ClassNotFoundException {
        Connection conn = makeConnection();

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM users where id=?");
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
        //DBConnection db = new DBConnection();
        //PreparedStatement ps = db.makeConnection().prepareStatement("SELECT * FROM users");
        // 클래스로 분리한거 불러와서 써본거

        Connection conn = makeConnection(); //추상클래스에서 불러온 것
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM users");

        ResultSet rs = ps.executeQuery();

        List<UserVO> userList = new ArrayList<>();
        while (rs.next()) {
            UserVO user = new UserVO(rs.getString("id"), rs.getString("name"), rs.getString("password"));
            userList.add(user);
        }

        return userList;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //UserDAOAbstract userDAO = new UserDAOAbstract();
        //userDAO.add(new UserVO("bujjaf","kjh","12341234"));

        //userDAO.getUserOne("idtest");
        //UserVO user = userDAO.getUserOne("idtest");
        //System.out.println(user.getName());

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
