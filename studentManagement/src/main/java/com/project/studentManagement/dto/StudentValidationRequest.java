package com.project.studentManagement.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentValidationRequest {

    private String studentCode;
    private LocalDate dateOfBirth;
}
