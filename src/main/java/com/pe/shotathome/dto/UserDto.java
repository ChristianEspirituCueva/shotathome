package com.pe.shotathome.dto;

//import lombok.Builder;
//import lombok.Getter;
//import lombok.Setter;

public class UserDto {
    private String userName;
    private String email;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
