package com.khoa.play.models;

/**
 * @author Khoa
 * @created 7/28/2019
 */
public class JwtResponse {
    private String token;

    public JwtResponse() {
    }

    public JwtResponse(String token) {
        this.token = token;
    }

    public static JwtResponse of(String token) {
        return new JwtResponse(token);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
