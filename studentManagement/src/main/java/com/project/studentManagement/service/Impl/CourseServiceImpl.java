package com.project.studentManagement.service.Impl;

import com.project.studentManagement.dto.CourseDTO;
import com.project.studentManagement.entity.Course;
import com.project.studentManagement.repo.CourseRepo;
import com.project.studentManagement.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepo courseRepo;

    @Override
    public CourseDTO createCourse(CourseDTO courseDTO) {
        Course course = new Course();

        course.setCourseName(courseDTO.getCourseName());
        course.setDescription(courseDTO.getDescription());
        course.setCourseType(courseDTO.getCourseType());
        course.setDuration(courseDTO.getDuration());
        course.setTopics(courseDTO.getTopics());

        return mapToDTO(courseRepo.save(course));
    }

    private CourseDTO mapToDTO(Course course) {
        CourseDTO dto = new CourseDTO();

        dto.setId(course.getId());
        dto.setCourseName(course.getCourseName());
        dto.setDescription(course.getDescription());
        dto.setDuration(course.getDuration());
        dto.setCourseType(course.getCourseType());
        dto.setTopics(course.getTopics());

        return dto;
    }

    @Override
    public CourseDTO getCourseById(Long id) {
        Course course = courseRepo.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));
        return mapToDTO(course);
    }

    @Override
    public List<CourseDTO> getAllCourses() {
        return courseRepo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteCourse(Long id) {
        if(!courseRepo.existsById(id)) {
            throw new RuntimeException("Course not found");
        }

        courseRepo.deleteById(id);
    }
}
