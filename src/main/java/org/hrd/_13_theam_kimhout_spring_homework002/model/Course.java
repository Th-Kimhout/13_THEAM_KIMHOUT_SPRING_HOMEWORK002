package org.hrd._13_theam_kimhout_spring_homework002.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    private Integer courseId;
    private String courseName;
    private String description;
    private Instructor instructor;
}
