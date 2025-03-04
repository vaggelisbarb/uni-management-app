package com.example.demo.dto.student;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Student's enrollment information")
public class EnrollmentInfoDTO {

    @Schema(description = "Student's enrollment date", example = "10-10-2016")
    private String enrollmentDate;

    @Schema(description = "Student's current status", example = "Active")
    private String currentStatus;
}
