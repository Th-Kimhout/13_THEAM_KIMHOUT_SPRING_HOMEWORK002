package org.hrd._13_theam_kimhout_spring_homework002.service.impl;

import lombok.RequiredArgsConstructor;
import org.hrd._13_theam_kimhout_spring_homework002.exception.BadRequestException;
import org.hrd._13_theam_kimhout_spring_homework002.exception.CannotDeleteException;
import org.hrd._13_theam_kimhout_spring_homework002.exception.NotFoundException;
import org.hrd._13_theam_kimhout_spring_homework002.model.Course;
import org.hrd._13_theam_kimhout_spring_homework002.model.Instructor;
import org.hrd._13_theam_kimhout_spring_homework002.model.request.InstructorRequest;
import org.hrd._13_theam_kimhout_spring_homework002.repository.CourseRepository;
import org.hrd._13_theam_kimhout_spring_homework002.repository.InstructorRepository;
import org.hrd._13_theam_kimhout_spring_homework002.service.InstructorService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {
    private final InstructorRepository instructorRepository;
    private final CourseRepository courseRepository;

    @Override
    public List<Instructor> getAllInstructors(Integer page, Integer size) {
        return instructorRepository.findAllInstructors(size * (page - 1), size);
    }

    @Override
    public Instructor getInstructorById(int id) {
        if (id <= 0) {
            throw new BadRequestException("Instructor id must be greater than 0!");
        }
        Instructor instructor = instructorRepository.findInstructorById(id);
        if (instructor == null) {
            throw new NotFoundException("No instructor with id " + id + " is found!");
        }
        return instructor;
    }

    @Override
    public Instructor createInstructor(InstructorRequest instructorRequest) {

        Instructor instructor = instructorRepository.insertInstructor(instructorRequest);
        if (instructor == null) {
            throw new BadRequestException("Create instructor unsuccessfully!");
        }
        return instructor;
    }

    @Override
    public Instructor updateInstructorById(int id, InstructorRequest instructorRequest) {
        if (id <= 0) {
            throw new BadRequestException("Instructor id must be greater than 0!");
        }
        Instructor instructor = instructorRepository.updateInstructorById(id, instructorRequest);
        if (instructor == null) {
            throw new BadRequestException("Update instructor unsuccessfully!");
        }
        return instructor;
    }

    @Override
    public void deleteInstructorById(int id) {
        if (id <= 0) {
            throw new BadRequestException("Instructor id must be greater than 0!");
        }
        Instructor instructor = instructorRepository.findInstructorById(id);
        if (instructor == null) {
            throw new NotFoundException("No instructor with id " + id + " is found!");
        }
        List<Integer> courseIdList = new ArrayList<>();
        List<Course> courseList = courseRepository.findAllCourses();
        courseList.forEach(course -> {
            if (Objects.equals(course.getInstructor().getInstructorId(), instructor.getInstructorId())) {
                courseIdList.add(course.getCourseId());
            }
        });
        if (!courseIdList.isEmpty()) {
            throw new CannotDeleteException("Cannot delete instructor with id " + id + "! You have to delete course with id " + courseIdList + " first!");
        }
        instructorRepository.deleteInstructorById(id);
    }
}
