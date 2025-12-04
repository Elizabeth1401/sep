package com.findmyclub.model;

import jakarta.persistence.*;

@Entity @Table(name = "client") // we named the table as client not as user because user is reserved name in postrges
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)private Integer id;
   @Column(nullable = false, length = 30) private String username;
   @Column(nullable = false) private String email;

   //We store the HASH, not the plain password
   @Column(nullable = false, length = 100) private String passwordHash;

    public User() {
        // JPA needs a no-arg constructor
    }

    public User(String username,String email, String passwordHash) {
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    // no setter for id; JPA will set it
    public void setPassword(String passwordHash) {
        this.passwordHash = passwordHash;
    }
    public void setUsername(String username) {
       this.username = username;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }
    public String getPasswordHash() {
        return passwordHash;
    }
    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    };
}
