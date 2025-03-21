package org.hrd._13_theam_kimhout_spring_homework002.service;

import org.hrd._13_theam_kimhout_spring_homework002.model.Student;
import org.hrd._13_theam_kimhout_spring_homework002.model.request.StudentRequest;

import java.util.List;


public interface StudentService {
     List<Student> getAllStudents(Integer page, Integer size);
     Student getStudentById(Integer id);
     Student createStudent(StudentRequest studentRequest);
     Student updateStudentById(Integer id,StudentRequest studentRequest);
     void deleteStudentById(Integer id);
}
