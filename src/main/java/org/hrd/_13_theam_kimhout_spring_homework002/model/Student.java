package org.hrd._13_theam_kimhout_spring_homework002.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private Integer studentId;
    private String studentName;
    private String email;
    private String phoneNumber;
    private List<Course> courseList;
}
