package com.khoa.play.models;

public class AuthorDTO {
    private Long id;
    private String firstName;
    private String lastName;

    public AuthorDTO(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static class Builder {

        private Long id;
        private String firstName;
        private String lastName;

        public Builder() {
        }

        public Builder id(Long val) {
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

    public AuthorDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
