package com.example.medica.domain;

import jakarta.persistence.*;
import java.util.UUID;
@Entity
@Table(name = "users")

public class UserRoot {

@Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

@Column
private String name;

@Column(unique = true , nullable = false)
private String email;

@Column(nullable = false)
    private String password;

public UserRoot(){

}

    public UserRoot(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;

    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
