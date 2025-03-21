package org.hrd._13_theam_kimhout_spring_homework002.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.hrd._13_theam_kimhout_spring_homework002.model.Instructor;
import org.hrd._13_theam_kimhout_spring_homework002.model.request.InstructorRequest;
import org.hrd._13_theam_kimhout_spring_homework002.model.response.ApiResponse;
import org.hrd._13_theam_kimhout_spring_homework002.service.InstructorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/instructors")
public class InstructorController {

    private final InstructorService instructorService;

    //Get all
    @GetMapping("")
    public ResponseEntity<ApiResponse<List<Instructor>>> getAllInstructors(@RequestParam(defaultValue = "1") @Positive( message = "page must be a positive number") Integer page, @RequestParam(defaultValue = "3") @Positive(message = "size must be a positive number") Integer size) {
        List<Instructor> instructorList = instructorService.getAllInstructors(page, size);
        ApiResponse<List<Instructor>> response = ApiResponse.<List<Instructor>>builder()
                .message("Retrieved all Instructor Successfully!")
                .payload(instructorList)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(response);
    }

    //    Create One
    @PostMapping("")
    public ResponseEntity<ApiResponse<Instructor>> createInstructor(@RequestBody @Valid InstructorRequest instructorRequest) {

        Instructor instructor = instructorService.createInstructor(instructorRequest);
        ApiResponse<Instructor> response = ApiResponse.<Instructor>builder()
                .message("Create Instructor Successfully!")
                .payload(instructor)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(response);
    }

    //    Get By ID
    @GetMapping("/{instructor-id}")
    public ResponseEntity<ApiResponse<Instructor>> getInstructorById(@PathVariable(name = "instructor-id") Integer instructorId) {
        Instructor instructor = instructorService.getInstructorById(instructorId);
        ApiResponse<Instructor> response = ApiResponse.<Instructor>builder()
                .message("Instructor retrieved Successfully!")
                .payload(instructor)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(response);
    }

    // Update One
    @PutMapping("/{instructor-id}")
    public ResponseEntity<ApiResponse<Instructor>> updateInstructorById(@PathVariable(name = "instructor-id") Integer instructorId, @RequestBody @Valid InstructorRequest instructorRequest) {
        Instructor instructor = instructorService.updateInstructorById(instructorId, instructorRequest);
        ApiResponse<Instructor> response = ApiResponse.<Instructor>builder()
                .message("Instructor updated Successfully!")
                .payload(instructor)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(response);
    }

    //    Delete
    @DeleteMapping("/{instructor-id}")
    public ResponseEntity<ApiResponse<Instructor>> deleteInstructorById(@PathVariable(name = "instructor-id") Integer instructorId) {
     instructorService.deleteInstructorById(instructorId);

        ApiResponse<Instructor> response = ApiResponse.<Instructor>builder()
                .message("Deleted instructor with id " + instructorId + " successfully!")
                .payload(null)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
