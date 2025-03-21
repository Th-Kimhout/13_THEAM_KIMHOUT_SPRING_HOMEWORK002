package org.hrd._13_theam_kimhout_spring_homework002.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Instructor {
        private Integer instructorId;
        private String instructorName;
        private String email;
}
