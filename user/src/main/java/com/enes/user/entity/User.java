package com.enes.user.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false,unique = true)
    private String email;
}
