package org.hrd._13_theam_kimhout_spring_homework002.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.List;
@Data
@AllArgsConstructor
public class StudentRequest {
    @NotNull(message = "Student name cannot be null!")
    @NotBlank(message = "Student name cannot be empty!")
    @Length(max = 50, message = "Student name cannot be over 50 characters!")
    private String studentName;
    @Email(message = "Invalid email format!")
    private String email;
    @Pattern(regexp = "\\d+", message = "Phone number cannot contain letter!")
    @Length(max=15, message = "Phone number shouldn't be over 15 digits")
    private String phoneNumber;
    @Pattern(regexp = "\\d+", message = "Course id cannot contain letter!")
    private List<Integer> courseIds;
}
