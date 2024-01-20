package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name="users")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override

    public String toString() {

        return "User [id=" + id + ", username=" + username + ", password=" + password + "]";

    }
}
