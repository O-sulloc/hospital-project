package com.dbEx.domain;

public class UserVO {
    private String id;
    private String name;
    private String password;


    public UserVO(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
