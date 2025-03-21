package org.hrd._13_theam_kimhout_spring_homework002.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hrd._13_theam_kimhout_spring_homework002.model.Course;
import org.hrd._13_theam_kimhout_spring_homework002.model.request.CourseRequest;
import org.hrd._13_theam_kimhout_spring_homework002.model.response.ApiResponse;
import org.hrd._13_theam_kimhout_spring_homework002.repository.CourseRepository;
import org.hrd._13_theam_kimhout_spring_homework002.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    //Get all
    @GetMapping("")

    public ResponseEntity<ApiResponse<List<Course>>> getAllCourses(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "3") Integer size) {

        List<Course> courseList = courseService.getAllCourses(page, size);
        ApiResponse<List<Course>> response = ApiResponse.<List<Course>>builder()
                .message("Retrieved all Courses Successfully!")
                .payload(courseList)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(response);
    }

    //    Create One
    @PostMapping("")
    public ResponseEntity<ApiResponse<Course>> createCourse(@RequestBody @Valid CourseRequest courseRequest) {

        Course course = courseService.createCourse(courseRequest);
        ApiResponse<Course> response = ApiResponse.<Course>builder()
                .message("Course created Successfully!")
                .payload(course)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(response);
    }

    //    Get By ID
    @GetMapping("/{course-id}")
    public ResponseEntity<ApiResponse<Course>> getCourseById(@PathVariable(name = "course-id") Integer courseId) {
        Course course = courseService.getCourseById(courseId);
        ApiResponse<Course> response = ApiResponse.<Course>builder()
                .message("Course retrieved Successfully!")
                .payload(course)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(response);
    }

    // Update One
    @PutMapping("/{course-id}")
    public ResponseEntity<ApiResponse<Course>> updateCourseById(@PathVariable(name = "course-id") Integer courseId, @RequestBody @Valid CourseRequest courseRequest) {
        Course course = courseService.updateCourseById(courseId, courseRequest);

        ApiResponse<Course> response = ApiResponse.<Course>builder()
                .message("Course updated Successfully!")
                .payload(course)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(response);
    }

    //    Delete
    @DeleteMapping("/{course-id}")
    public ResponseEntity<ApiResponse<Course>> deleteCourseById(@PathVariable(name = "course-id") Integer courseId) {
       courseService.deleteCourseById(courseId);

        ApiResponse<Course> response = ApiResponse.<Course>builder()
                .message("Deleted course with id " + courseId + " successfully!")
                .payload(null)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
