package org.hrd._13_theam_kimhout_spring_homework002.model.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hrd._13_theam_kimhout_spring_homework002.model.Instructor;
@Data
@AllArgsConstructor
public class CourseRequest {
    @NotNull(message = "course name cannot be null!")
    @NotBlank(message = "course name cannot be empty!")
    @Length(max = 50, message = "course name cannot be over 50 characters!")
    private String courseName;
    private String description;
    @NotNull(message = "instruct id cannot be null!")
    @NotBlank(message = "instruct id cannot be empty!")
    @Pattern(regexp = "\\d+",message = "instruct id must be a number!")
    @Min(value = 1, message = "instruct id cannot be a negative number!")
    private Integer instructorId;
}
