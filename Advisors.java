package com.example.schoolmedicalobservation;

import java.io.Serializable;

public class Advisors implements Serializable {



    private long user_name;
    private String password ;



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


}

