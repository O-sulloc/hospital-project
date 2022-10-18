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

        int i=5;
        String id = "JunitId"+i;
        UserVO userVO = new UserVO(id, "JunitName"+i, "JunitPw"+i);

        userDAO.add(userVO);
        Assertions.assertEquals(1, 1);

        UserVO userOne = userDAO.getUserOne(id);
        Assertions.assertEquals("JunitName"+i, userOne.getName());
    }
}