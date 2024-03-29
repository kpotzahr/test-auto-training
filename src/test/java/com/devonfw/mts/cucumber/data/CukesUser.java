package com.devonfw.mts.cucumber.data;

import com.devonfw.mts.shared.TestDataPoolContent;

public class CukesUser {
    public static CukesUser validUser() {
        return new CukesUser(TestDataPoolContent.instance().getDefaultUsername(),
                TestDataPoolContent.instance().getDefaultPassword());
    }

    private String username;
    private String password;


    public CukesUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public CukesUser() {}

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    @Override
    public String toString() {
        return "Usern with username: " + this.username + " and password: " + this.password;
    }
}
