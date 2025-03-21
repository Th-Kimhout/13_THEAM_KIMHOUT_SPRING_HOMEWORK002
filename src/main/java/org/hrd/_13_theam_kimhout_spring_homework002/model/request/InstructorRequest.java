package org.hrd._13_theam_kimhout_spring_homework002.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
public class InstructorRequest {
    @NotNull(message = "Instructor name cannot be null!")
    @NotBlank(message = "Instructor name cannot be empty!")
    @Length(max = 50, message = "Instructor name cannot be over 50 characters!")
    private String instructorName;
    @Email(message = "Invalid email format!")
    private String email;
}
