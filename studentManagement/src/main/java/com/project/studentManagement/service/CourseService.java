package com.project.studentManagement.service;

import com.project.studentManagement.dto.CourseDTO;

import java.util.List;

public interface CourseService {

    CourseDTO createCourse(CourseDTO courseDTO);

    CourseDTO getCourseById(Long id);

    List<CourseDTO> getAllCourses();

    void deleteCourse(Long id);
}
