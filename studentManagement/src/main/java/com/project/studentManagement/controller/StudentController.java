package com.project.studentManagement.controller;

import com.project.studentManagement.dto.StudentRequestDTO;
import com.project.studentManagement.dto.StudentResponseDTO;
import com.project.studentManagement.dto.StudentValidationRequest;
import com.project.studentManagement.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentResponseDTO> createStudent(@Valid @RequestBody StudentRequestDTO requestDTO) {
        return new ResponseEntity<>(studentService.createStudent(requestDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> updateStudent(@PathVariable Long id, @RequestBody StudentRequestDTO requestDTO) {
        return ResponseEntity.ok(studentService.updateStudent(id,requestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getStudent(@PathVariable Long id){
        return  ResponseEntity.ok(studentService.getStudentById(id));
    }

    @GetMapping
    public ResponseEntity<List<StudentResponseDTO>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/search")
    public ResponseEntity<List<StudentResponseDTO>> searchStudents(@RequestParam String name) {
        return ResponseEntity.ok(studentService.searchStudentsByName(name));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{studentId}/courses/{courseId}")
    public ResponseEntity<Void> assignCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        studentService.assignCourse(studentId,courseId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{studentId}/courses/{courseId}")
    public ResponseEntity<Void> removeCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        studentService.removeCourse(studentId, courseId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/validate")
    public ResponseEntity<String> validateStudent(@RequestBody StudentValidationRequest request) {
        boolean valid = studentService.validateStudent(request);

        if(valid) {
            return ResponseEntity.ok("Student Verified");
        }

        return ResponseEntity.badRequest().body("Invalid Student Details");
    }
}
