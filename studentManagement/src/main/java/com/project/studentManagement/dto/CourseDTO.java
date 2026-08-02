package com.project.studentManagement.dto;

import lombok.Data;

@Data
public class CourseDTO {

    private Long id;
    private String courseName;
    private String description;
    private String courseType;
    private Integer duration;
    private String topics;
}
