package com.project.studentManagement.dto;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class StudentRequestDTO {

    @NotBlank
    private String name;
    @NotBlank
    private String studentCode;

    private LocalDate dateOfBirth;
    @NotBlank
    private String gender;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String mobileNumber;
    @NotBlank
    private String fatherName;
    @NotBlank
    private String motherName;

    private Set<AddressDTO> addresses;
}
