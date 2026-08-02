package com.project.studentManagement.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Table(name = "admins")
@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String username;

    @Column(nullable = false)
    private String password;
}
