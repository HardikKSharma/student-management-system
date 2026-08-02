package com.project.studentManagement.service;

import com.project.studentManagement.dto.StudentRequestDTO;
import com.project.studentManagement.dto.StudentResponseDTO;
import com.project.studentManagement.dto.StudentValidationRequest;

import java.util.List;

public interface StudentService {

    StudentResponseDTO createStudent(StudentRequestDTO request);

    StudentResponseDTO updateStudent(Long id, StudentRequestDTO request);

    StudentResponseDTO getStudentById(Long id);

    List<StudentResponseDTO> getAllStudents();

    List<StudentResponseDTO> searchStudentsByName(String name);

    void deleteStudent(Long id);

    void assignCourse(Long studentId, Long courseId);

    void removeCourse(Long studentId, Long courseId);

    boolean validateStudent(StudentValidationRequest request);
}
