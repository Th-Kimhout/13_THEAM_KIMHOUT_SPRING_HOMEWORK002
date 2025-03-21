package org.hrd._13_theam_kimhout_spring_homework002.service.impl;

import org.hrd._13_theam_kimhout_spring_homework002.exception.BadRequestException;
import org.hrd._13_theam_kimhout_spring_homework002.exception.CannotDeleteException;
import org.hrd._13_theam_kimhout_spring_homework002.exception.NotFoundException;
import org.hrd._13_theam_kimhout_spring_homework002.model.Course;
import org.hrd._13_theam_kimhout_spring_homework002.model.request.CourseRequest;
import org.hrd._13_theam_kimhout_spring_homework002.repository.CourseRepository;
import org.hrd._13_theam_kimhout_spring_homework002.service.CourseService;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;


    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getAllCourses(Integer page, Integer size) {
        List<Course> courseList = courseRepository.findAllCoursesPagination(size * (page - 1), size);
        if (courseList == null) {
            throw new NotFoundException("No courses found!");
        }
        return courseList;
    }

    @Override
    public Course getCourseById(Integer id) {
        if (id <= 0) {
            throw new BadRequestException("Course id must be greater than 0!");
        }
        Course course = courseRepository.findCourseById(id);
        if (course == null) {
            throw new NotFoundException("No course with id " + id + " found!");
        }
        return course;
    }

    @Override
    public Course createCourse(CourseRequest courseRequest) {
        Course course = courseRepository.insertCourse(courseRequest);
        if (course == null) {
            throw new BadRequestException("Course create unsuccessful!");
        }
        return course;
    }

    @Override
    public Course updateCourseById(Integer id, CourseRequest courseRequest) {
        if (id <= 0) {
            throw new BadRequestException("Course id must be greater than 0!");
        }
        Course course = courseRepository.updateCourseById(id, courseRequest);
        if (course == null) {
            throw new NotFoundException("No course with id " + id + " found!");
        }
        return course;
    }

    @Override
    public void deleteCourseById(Integer id) {
        if (id <= 0) {
            throw new BadRequestException("Course id must be greater than 0!");
        }

        Course course = courseRepository.findCourseById(id);
        if (course == null) {
            throw new NotFoundException("No course with id " + id + " found!");
        }

        List<Integer> studentIdByCourseId = courseRepository.getStudentIdByCourseId(id);
        if (studentIdByCourseId != null && !studentIdByCourseId.isEmpty()) {
            throw new CannotDeleteException("Cannot delete course. It is currently assigned to students with id: " + studentIdByCourseId);
        }

        courseRepository.deleteCourseById(id);
    }
}
