package org.hrd._13_theam_kimhout_spring_homework002.service;

import org.hrd._13_theam_kimhout_spring_homework002.model.Instructor;
import org.hrd._13_theam_kimhout_spring_homework002.model.request.InstructorRequest;

import java.util.List;


public interface InstructorService {
     List<Instructor> getAllInstructors(Integer page, Integer size);
     Instructor getInstructorById(int id);
     Instructor createInstructor( InstructorRequest instructorRequest);
     Instructor updateInstructorById(int id,InstructorRequest instructorRequest);
     void deleteInstructorById(int id);
}
