package com.project.studentManagement.service;

import com.project.studentManagement.dto.StudentRequestDTO;
import com.project.studentManagement.dto.StudentResponseDTO;
import com.project.studentManagement.dto.StudentValidationRequest;
import com.project.studentManagement.entity.Course;
import com.project.studentManagement.entity.Student;
import com.project.studentManagement.repo.CourseRepo;
import com.project.studentManagement.repo.StudentRepo;
import com.project.studentManagement.service.Impl.StudentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @Mock
    private StudentRepo studentRepo;

    @Mock
    private CourseRepo courseRepo;

    @InjectMocks
    private StudentServiceImpl studentService;

    @Test
    void createStudentTest() {

        StudentRequestDTO request = new StudentRequestDTO();
        request.setName("John");
        request.setStudentCode("ST001");
        request.setDateOfBirth(LocalDate.of(2000,1,1));
        request.setGender("Male");
        request.setEmail("john@test.com");
        request.setMobileNumber("9999999999");
        request.setFatherName("Father");
        request.setMotherName("Mother");

        Student student = Student.builder()
                .id(1L)
                .name("John")
                .studentCode("ST001")
                .dateOfBirth(LocalDate.of(2000,1,1))
                .gender("Male")
                .email("john@test.com")
                .mobileNumber("9999999999")
                .fatherName("Father")
                .motherName("Mother")
                .build();

        when(studentRepo.save(any(Student.class))).thenReturn(student);

        StudentResponseDTO response = studentService.createStudent(request);

        assertEquals("John", response.getName());
        verify(studentRepo).save(any(Student.class));
    }

    @Test
    void getStudentByIdTest() {

        Student student = Student.builder()
                .id(1L)
                .name("John")
                .courses(Set.of())
                .build();

        when(studentRepo.findById(1L)).thenReturn(Optional.of(student));

        StudentResponseDTO response = studentService.getStudentById(1L);

        assertEquals("John", response.getName());
    }

    @Test
    void getAllStudentsTest() {

        Student student = Student.builder()
                .id(1L)
                .name("John")
                .courses(Set.of())
                .build();

        when(studentRepo.findAll()).thenReturn(List.of(student));

        List<StudentResponseDTO> response = studentService.getAllStudents();

        assertEquals(1, response.size());
    }

    @Test
    void searchStudentsByNameTest() {

        Student student = Student.builder()
                .id(1L)
                .name("John")
                .courses(Set.of())
                .build();

        when(studentRepo.findByNameContainingIgnoreCase("John"))
                .thenReturn(List.of(student));

        List<StudentResponseDTO> response =
                studentService.searchStudentsByName("John");

        assertEquals(1, response.size());
    }

    @Test
    void updateStudentTest() {

        Student student = Student.builder()
                .id(1L)
                .courses(Set.of())
                .build();

        StudentRequestDTO request = new StudentRequestDTO();
        request.setName("Updated");

        when(studentRepo.findById(1L)).thenReturn(Optional.of(student));
        when(studentRepo.save(any(Student.class))).thenReturn(student);

        StudentResponseDTO response =
                studentService.updateStudent(1L, request);

        verify(studentRepo).save(student);
    }

    @Test
    void deleteStudentTest() {

        when(studentRepo.existsById(1L)).thenReturn(true);

        studentService.deleteStudent(1L);

        verify(studentRepo).deleteById(1L);
    }

    @Test
    void assignCourseTest() {

        Student student = new Student();
        student.setCourses(new HashSet<>());

        Course course = new Course();
        course.setStudents(new HashSet<>());

        when(studentRepo.findById(1L)).thenReturn(Optional.of(student));
        when(courseRepo.findById(1L)).thenReturn(Optional.of(course));

        studentService.assignCourse(1L, 1L);

        assertEquals(1, student.getCourses().size());
        verify(studentRepo).save(student);
    }

    @Test
    void removeCourseTest() {

        Student student = new Student();
        Course course = new Course();

        student.setCourses(new java.util.HashSet<>(Set.of(course)));

        when(studentRepo.findById(1L)).thenReturn(Optional.of(student));
        when(courseRepo.findById(1L)).thenReturn(Optional.of(course));

        studentService.removeCourse(1L,1L);

        assertFalse(student.getCourses().contains(course));
        verify(studentRepo).save(student);
    }

    @Test
    void validateStudentTest() {

        StudentValidationRequest request = new StudentValidationRequest();
        request.setStudentCode("ST001");
        request.setDateOfBirth(LocalDate.of(2000,1,1));

        when(studentRepo.findByStudentCodeAndDateOfBirth(
                "ST001",
                LocalDate.of(2000,1,1)))
                .thenReturn(Optional.of(new Student()));

        assertTrue(studentService.validateStudent(request));
    }
}