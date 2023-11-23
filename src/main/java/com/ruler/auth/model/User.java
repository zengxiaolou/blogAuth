package com.ruler.auth.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    private Long id;
    private String username;
    private String password;
}