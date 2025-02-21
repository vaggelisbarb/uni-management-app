package com.example.demo.dto.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentInfoDTO {

    private String enrollmentDate;
    private String currentStatus;
}
