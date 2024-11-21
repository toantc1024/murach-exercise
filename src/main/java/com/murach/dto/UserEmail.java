package com.murach.dto;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="user")
public class UserEmail implements Serializable {

    @Id
    @Column(name="user_id", nullable=false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private String firstName;
    private String lastName;
    private String email;

}
