package com.khoa.play.models;

/**
 * @author Khoa
 * @created 7/28/2019
 */
public class UserDTO {
    private Long id;
    private String email;
    private String password;
    private String status;

    public UserDTO() {
    }

    public UserDTO(Long id, String email, String password, String status) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
