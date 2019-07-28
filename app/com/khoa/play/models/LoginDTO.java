package com.khoa.play.models;

/**
 * @author Khoa
 * @created 7/28/2019
 */
public class LoginDTO {
    private String email;
    private String password;

    public LoginDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
