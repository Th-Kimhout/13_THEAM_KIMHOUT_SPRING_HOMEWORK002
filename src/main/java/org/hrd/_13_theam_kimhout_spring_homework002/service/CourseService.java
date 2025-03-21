package org.hrd._13_theam_kimhout_spring_homework002.service;

import org.hrd._13_theam_kimhout_spring_homework002.model.Course;
import org.hrd._13_theam_kimhout_spring_homework002.model.request.CourseRequest;

import java.util.List;

public interface CourseService {
     List<Course> getAllCourses(Integer page, Integer size);
     Course getCourseById(Integer id);
     Course createCourse( CourseRequest courseRequest);
     Course updateCourseById(Integer id,CourseRequest courseRequest);
     void deleteCourseById(Integer id);
}
