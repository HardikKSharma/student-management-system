package com.project.studentManagement.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Table(name ="addresses")
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    private String street;

    private String city;

    private String country;

    private String pinCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

}
