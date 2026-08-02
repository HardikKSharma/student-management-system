package com.project.studentManagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String studentCode;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String email;

    private LocalDate dateOfBirth;

    private String mobileNumber;

    private String fatherName;

    private String motherName;

    @OneToMany(mappedBy = "student",
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    private Set<Address> addresses = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "student_course",
    joinColumns = @JoinColumn(name = "student_id"),
    inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> courses = new HashSet<>();
}
