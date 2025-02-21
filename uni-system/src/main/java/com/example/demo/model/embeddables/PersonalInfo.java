package com.example.demo.model.embeddables;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalInfo {

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;   // First name of the person

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;    // Last name of the person

    @NotBlank(message = "Date of birth is required")
    @Pattern(regexp = "\\d{2}-\\d{2}-\\d{4}", message = "Enrollment date must be in the format dd-MM-yyyy")
    private String dateOfBirth;     // Date of birth in the format yyyy-MM-dd

    @NotBlank(message = "Gender is required")
    @Pattern(regexp = "^(Male|Female)$", message = "Gender must be one of the following: Male, Female")
    private String gender;  // Gender of the person (e.g., Male, Female)

}
