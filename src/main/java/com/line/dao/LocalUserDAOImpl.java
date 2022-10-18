package com.line.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class LocalUserDAOImpl extends UserDAOAbstract{


    @Override
    public Connection makeConnection() throws ClassNotFoundException, SQLException {

        Map<String, String> env = System.getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");

        Class.forName("com.mysql.cj.jdbc.Driver");
        //드라이버를 메모리에 로딩하는 과정. 생략 가능

        Connection conn = DriverManager.getConnection(dbHost, dbUser, dbPassword);

        return conn;
    }
}
