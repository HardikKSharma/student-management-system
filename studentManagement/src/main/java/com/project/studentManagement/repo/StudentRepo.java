package com.project.studentManagement.repo;

import com.project.studentManagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {

    Optional<Student> findByStudentCode(String studentCode);

    List<Student> findByNameContainingIgnoreCase(String name);

    Optional<Student> findByStudentCodeAndDateOfBirth(
            String studentCode,
            LocalDate dateOfBirth);
}
