package org.hrd._13_theam_kimhout_spring_homework002.controller;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.hrd._13_theam_kimhout_spring_homework002.model.Student;
import org.hrd._13_theam_kimhout_spring_homework002.model.request.StudentRequest;
import org.hrd._13_theam_kimhout_spring_homework002.model.response.ApiResponse;
import org.hrd._13_theam_kimhout_spring_homework002.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/students")
public class StudentController {
    private final StudentService studentService;

    //Get all
    @GetMapping("")
    public ResponseEntity<ApiResponse<List<Student>>> getAllStudents(@RequestParam(defaultValue = "1") @Positive(message = "page must be greater than 1!") Integer page, @RequestParam(defaultValue = "3") @Positive(message = "size must be a positive number") Integer size) {
        List<Student> studentList = studentService.getAllStudents(page, size);

        ApiResponse<List<Student>> response = ApiResponse.<List<Student>>builder()
                .message("Retrieved all Students Successfully !")
                .payload(studentList)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(response);
    }

    //    Create One
    @PostMapping("")
    public ResponseEntity<ApiResponse<Student>> createStudent(@RequestBody StudentRequest studentRequest) {
        Student student = studentService.createStudent(studentRequest);
        ApiResponse<Student> response = ApiResponse.<Student>builder()
                .message("Student created Successfully !")
                .payload(student)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(response);
    }

    //    Get By ID
    @GetMapping("/{student-id}")
    public ResponseEntity<ApiResponse<Student>> getStudentById(@PathVariable(name = "student-id") Integer studentId) {
        Student student = studentService.getStudentById(studentId);

        ApiResponse<Student> response = ApiResponse.<Student>builder()
                .message("Student retrieved Successfully !")
                .payload(student)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(response);
    }

    // Update One
    @PutMapping("/{student-id}")
    public ResponseEntity<ApiResponse<Student>> updateStudentById(@PathVariable(name = "student-id") Integer studentId, @RequestBody StudentRequest studentRequest) {
        Student student = studentService.updateStudentById(studentId, studentRequest);

        ApiResponse<Student> response = ApiResponse.<Student>builder()
                .message("Student updated Successfully !")
                .payload(student)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(response);
    }

    //    Delete
    @DeleteMapping("/{student-id}")
    public ResponseEntity<ApiResponse<Student>> deleteStudentById(@PathVariable(name = "student-id") Integer studentId) {
      studentService.deleteStudentById(studentId);
            ApiResponse<Student> response = ApiResponse.<Student>builder()
                    .message("Deleted student with id " + studentId + " successfully!")
                    .payload(null)
                    .status(HttpStatus.OK)
                    .build();
            return ResponseEntity.ok(response);

    }

}
