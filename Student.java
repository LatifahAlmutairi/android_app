package com.example.schoolmedicalobservation;

import java.io.Serializable;

public class Student implements Serializable {



    private long user_name;
    private String password ;


    private String key ;



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getUser_name() {
        return user_name;
    }

    public void setUser_name(long user_name) {
        this.user_name = user_name;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}



