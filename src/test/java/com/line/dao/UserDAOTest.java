package com.line.dao;

import com.dbEx.userVO.UserVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {

    @Test
    void insertAndSelect() throws SQLException, ClassNotFoundException {
        UserDAOAbstract userDAO = new AWSUserDAOImpl();

        UserVO userVO = new UserVO("JunitId3", "JunitName3", "JunitPw3");

        userDAO.add(userVO);
        Assertions.assertEquals(1, 1);

        UserVO userOne = userDAO.getUserOne("JunitId3");
        Assertions.assertEquals("JunitName3", userOne.getName());
    }
}