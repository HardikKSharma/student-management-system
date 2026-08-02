package com.project.studentManagement.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Table(name = "courses")
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String courseName;

    private String description;

    private String courseType;

    private Integer duration;

    private String topics;

    @ManyToMany(mappedBy = "courses")
    private Set<Student> students = new HashSet<>();
}
