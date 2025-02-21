package com.example.demo.model.embeddables;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactInfo {

    @Email(message = "Invalid email address")
    @NotBlank(message = "Email address is required")
    private String email;   //Official email address

    @NotBlank(message = "Phone number is required")
    @Size(min = 10, max = 15, message = "Phone number must be between 10 and 15 characters")
    @Pattern(regexp = "^\\+?[0-9]{1,4}?[-.\\s]?[0-9]{1,4}[-.\\s]?[0-9]{1,4}[-.\\s]?[0-9]{1,9}$", message = "Invalid phone number format")
    // Contact phone number. The pattern allows the phone number to have an optional "+" at the beginning,
    // followed by a sequence of digits and optional spaces, dashes, or periods between the number segments.
    private String phoneNumber;

}
