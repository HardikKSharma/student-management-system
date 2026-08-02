package com.project.studentManagement.service.Impl;

import com.project.studentManagement.dto.CourseDTO;
import com.project.studentManagement.dto.StudentRequestDTO;
import com.project.studentManagement.dto.StudentResponseDTO;
import com.project.studentManagement.dto.StudentValidationRequest;
import com.project.studentManagement.entity.Course;
import com.project.studentManagement.entity.Student;
import com.project.studentManagement.repo.CourseRepo;
import com.project.studentManagement.repo.StudentRepo;
import com.project.studentManagement.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepo studentRepo;
    private final CourseRepo courseRepo;

    @Override
    public StudentResponseDTO createStudent(StudentRequestDTO request) {
        Student student = Student.builder()
                .name(request.getName())
                .studentCode(request.getStudentCode())
                .gender(request.getGender())
                .email(request.getEmail())
                .mobileNumber(request.getMobileNumber())
                .fatherName(request.getFatherName())
                .motherName(request.getMotherName())
                .dateOfBirth(request.getDateOfBirth())
                .build();

        Student savedStudent = studentRepo.save(student);

        StudentResponseDTO response = createResponse(savedStudent);

        return response;
    }

    private static @NonNull StudentResponseDTO createResponse(Student savedStudent) {
        StudentResponseDTO response = new StudentResponseDTO();
        response.setId(savedStudent.getId());

        response.setName(savedStudent.getName());
        response.setStudentCode(savedStudent.getStudentCode());
        response.setDateOfBirth(savedStudent.getDateOfBirth());
        response.setGender(savedStudent.getGender());
        response.setEmail(savedStudent.getEmail());
        response.setMobileNumber(savedStudent.getMobileNumber());
        response.setFatherName(savedStudent.getFatherName());
        response.setMotherName(savedStudent.getMotherName());
        return response;
    }

    @Override
    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO request) {
        Student student = studentRepo.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));

        student.setName(request.getName());
        student.setStudentCode(request.getStudentCode());
        student.setDateOfBirth(request.getDateOfBirth());
        student.setGender(request.getGender());
        student.setEmail(request.getEmail());
        student.setMobileNumber(request.getMobileNumber());
        student.setFatherName(request.getFatherName());
        student.setMotherName(request.getMotherName());

        Student updatedStudent = studentRepo.save(student);

        return mapToResponse(updatedStudent);
    }

    @Override
    public StudentResponseDTO getStudentById(Long id) {
        Student student = studentRepo.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        return mapToResponse(student);
    }

    @Override
    public List<StudentResponseDTO> getAllStudents() {
        return studentRepo.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    public List<StudentResponseDTO> searchStudentsByName(String name) {
        return studentRepo.findByNameContainingIgnoreCase(name).stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    public void deleteStudent(Long id) {

        if (!studentRepo.existsById(id)) {
            throw new RuntimeException("Student not found");
        }
        studentRepo.deleteById(id);

    }

    @Override
    public void assignCourse(Long studentId, Long courseId) {

        Student student = studentRepo.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepo.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));

        student.getCourses().add(course);
        course.getStudents().add(student);
        studentRepo.save(student);

    }

    @Override
    public void removeCourse(Long studentId, Long courseId) {

        Student student = studentRepo.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepo.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));

        student.getCourses().remove(course);
        course.getStudents().remove(student);

        studentRepo.save(student);

    }

    @Override
    public boolean validateStudent(StudentValidationRequest request) {
        return studentRepo.findByStudentCodeAndDateOfBirth(
                request.getStudentCode(),
                request.getDateOfBirth()
        ).isPresent();
    }

    private StudentResponseDTO mapToResponse(Student student) {
        StudentResponseDTO responseDTO = new StudentResponseDTO();

        responseDTO.setId(student.getId());
        responseDTO.setName(student.getName());
        responseDTO.setStudentCode(student.getStudentCode());
        responseDTO.setDateOfBirth(student.getDateOfBirth());
        responseDTO.setGender(student.getGender());
        responseDTO.setEmail(student.getEmail());
        responseDTO.setMobileNumber(student.getMobileNumber());
        responseDTO.setFatherName(student.getFatherName());
        responseDTO.setMotherName(student.getMotherName());

        responseDTO.setCourses(student.getCourses()
                .stream()
                .map(course -> {
                    CourseDTO dto = new CourseDTO();
                    dto.setId(course.getId());
                    dto.setCourseName(course.getCourseName());
                    dto.setDuration(course.getDuration());
                    dto.setDescription(course.getDescription());
                    dto.setCourseType(course.getCourseType());
                    dto.setTopics(course.getTopics());
                    return dto;
                })
                .collect(Collectors.toSet()));

        return responseDTO;
    }


}
