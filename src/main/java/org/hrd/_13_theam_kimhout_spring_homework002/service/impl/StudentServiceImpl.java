package org.hrd._13_theam_kimhout_spring_homework002.service.impl;

import lombok.RequiredArgsConstructor;
import org.hrd._13_theam_kimhout_spring_homework002.exception.BadRequestException;
import org.hrd._13_theam_kimhout_spring_homework002.exception.NotFoundException;
import org.hrd._13_theam_kimhout_spring_homework002.model.Course;
import org.hrd._13_theam_kimhout_spring_homework002.model.Student;
import org.hrd._13_theam_kimhout_spring_homework002.model.request.StudentRequest;
import org.hrd._13_theam_kimhout_spring_homework002.repository.CourseRepository;
import org.hrd._13_theam_kimhout_spring_homework002.repository.StudentRepository;
import org.hrd._13_theam_kimhout_spring_homework002.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Override
    public List<Student> getAllStudents(Integer page, Integer size) {
        return studentRepository.findAllStudents((size * (page - 1)), size);
    }

    @Override
    public Student getStudentById(Integer id) {
        if (id <= 0) {
            throw new BadRequestException("Student id must be greater than 0!");
        }
        Student student = studentRepository.findStudentById(id);
        if (student == null) {
            throw new NotFoundException("No student with id " + id + " is found!");
        }
        return student;
    }

    @Override
    public Student createStudent(StudentRequest studentRequest) {
        if (studentRequest.getCourseIds().isEmpty()) {
            return studentRepository.insertStudent(studentRequest);
        }
        for (Integer courseId : studentRequest.getCourseIds()) {
            if (courseRepository.findCourseById(courseId) == null) {
                throw new NotFoundException("No course with id " + courseId + " is found!");
            }
        }
        Student student = studentRepository.insertStudent(studentRequest);
        for (Integer courseId : studentRequest.getCourseIds()) {
            if (student == null) {
                throw new BadRequestException("Create student unsuccessfully!");
            }
            studentRepository.insertStudentCourse(student.getStudentId(), courseId);

        }
        return studentRepository.findStudentById(student.getStudentId());
    }

    @Override
    public Student updateStudentById(Integer id, StudentRequest studentRequest) {
        if (id <= 0) {
            throw new BadRequestException("Student id must be greater than 0!");
        }
        Student student = studentRepository.findStudentById(id);
        if (student == null) {
            throw new NotFoundException("No student with id" + id + " found!");
        }
        List<Course> courseList = courseRepository.findAllCourses();
        Set<Integer> existingCourseId = courseList.stream().map(Course::getCourseId).collect(Collectors.toSet());
        for (Integer courseId : studentRequest.getCourseIds()) {
            if (!existingCourseId.contains(courseId)) {
                throw new NotFoundException("No course with id " + courseId + " is found!");
            }
        }
        studentRepository.deleteAllStudentCourses(student.getStudentId());
        for (Integer courseId : studentRequest.getCourseIds()) {
            studentRepository.insertStudentCourse(student.getStudentId(), courseId);
        }
        return studentRepository.updateStudentById(id, studentRequest);
    }

    @Override
    public void deleteStudentById(Integer id) {
        if (id <= 0) {
            throw new BadRequestException("Student id must be greater than 0!");
        }
        Student student = studentRepository.findStudentById(id);
        if (student == null) {
            throw new NotFoundException("No student with id " + id + " is found!");
        }
        studentRepository.deleteStudentById(id);
    }
}
