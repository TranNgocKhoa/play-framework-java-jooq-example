package com.khoa.play.models;

public class AuthorDTO {
    private Integer id;
    private String  firstName;
    private String  lastName;


    public static class Builder {

        private Integer id;
        private String firstName;
        private String lastName;

        public Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder firstName(String val) {
            firstName = val;
            return this;
        }

        public Builder lastName(String val) {
            lastName = val;
            return this;
        }

        public AuthorDTO build() {
            return new AuthorDTO(this);
        }
    }

    public AuthorDTO(Builder builder) {
       this.id = builder.id;
       this.firstName = builder.firstName;
       this.lastName = builder.lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
