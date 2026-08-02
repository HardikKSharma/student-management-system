package com.project.studentManagement.service;

import com.project.studentManagement.dto.CourseDTO;
import com.project.studentManagement.entity.Course;
import com.project.studentManagement.repo.CourseRepo;
import com.project.studentManagement.service.Impl.CourseServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourseServiceImplTest {

    @Mock
    private CourseRepo courseRepo;

    @InjectMocks
    private CourseServiceImpl courseService;

    @Test
    void createCourseTest() {

        CourseDTO dto = new CourseDTO();
        dto.setCourseName("Java");

        Course course = new Course();
        course.setId(1L);
        course.setCourseName("Java");

        when(courseRepo.save(any(Course.class))).thenReturn(course);

        CourseDTO response = courseService.createCourse(dto);

        assertEquals("Java", response.getCourseName());
        verify(courseRepo).save(any(Course.class));
    }

    @Test
    void getCourseByIdTest() {

        Course course = new Course();
        course.setId(1L);
        course.setCourseName("Java");

        when(courseRepo.findById(1L)).thenReturn(Optional.of(course));

        CourseDTO response = courseService.getCourseById(1L);

        assertEquals("Java", response.getCourseName());
    }

    @Test
    void getAllCoursesTest() {

        Course course = new Course();
        course.setId(1L);
        course.setCourseName("Java");

        when(courseRepo.findAll()).thenReturn(List.of(course));

        List<CourseDTO> response = courseService.getAllCourses();

        assertEquals(1, response.size());
    }

    @Test
    void deleteCourseTest() {

        when(courseRepo.existsById(1L)).thenReturn(true);

        courseService.deleteCourse(1L);

        verify(courseRepo).deleteById(1L);
    }
}