package com.project.studentManagement.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class StudentResponseDTO {

    private Long id;
    private String name;
    private String studentCode;
    private LocalDate dateOfBirth;
    private String gender;
    private String email;
    private String mobileNumber;
    private String fatherName;
    private String motherName;

    private Set<AddressDTO> addresses;
    private Set<CourseDTO> courses;

}
