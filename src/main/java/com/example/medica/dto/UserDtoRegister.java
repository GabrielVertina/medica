package com.example.medica.dto;

import java.util.UUID;

public class UserDtoRegister {
    private UUID id;
    private String name;
    private String password;
    private String email;
private Boolean verified;



public UUID getId() {
    return id;
}


public String getName() {
    return name;
}
public void setName(String name) {
    this.name = name;
}
public String getPassword() {
    return password;
}
public void setPassword(String password) {
    this.password = password;
}
public String getEmail() {
    return email;
}
public void setEmail(String email) {
    this.email = email;
}
public Boolean getVerified() {
    return verified;
}


    
}
