package com.findmyclub.model;

import jakarta.persistence.*;

@Entity @Table (name = "client") // we named the table as client not as user because user is reserved name in postrges
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)private Integer id;
   @Column(nullable = false) private String username;
   @Column(nullable = false) private String password;

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void setUsername(String username) {
       this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
