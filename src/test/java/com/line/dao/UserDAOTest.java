package com.line.dao;

import com.dbEx.userVO.UserVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {

    @Test
    void insertAndSelect() throws SQLException, ClassNotFoundException {
        UserDAO userDAO = new UserDAO();

        UserVO userVO = new UserVO("JunitId2", "JunitName2", "JunitPw2");

        userDAO.add(userVO);
        Assertions.assertEquals(1, 1);

        UserVO userOne = userDAO.getUserOne("JunitId2");
        Assertions.assertEquals("JunitName2", userOne.getName());
    }
}