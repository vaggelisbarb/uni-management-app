package com.example.demo.model.embeddables;

import jakarta.persistence.Embeddable;
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
public class Address {

    @NotBlank(message = "Street is required")
    @Size(max = 100, message = "Street name cannot be longer than 100 characters")
    private String street; // Street name or address line

    @NotBlank(message = "City is required")
    @Size(max = 50, message = "City name cannot be longer than 50 characters")
    private String city; // City

    @NotBlank(message = "State is required")
    @Size(max = 50, message = "State name cannot be longer than 50 characters")
    private String state; // State/Province

    @NotBlank(message = "Postal code is required")
    @Size(min = 5, max = 10, message = "Postal code must be between 5 and 10 characters")
    @Pattern(regexp = "^[0-9]{5}$", message = "Postal code must be exactly 5 digits")
    private String postalCode; // Postal code

    @NotBlank(message = "Country is required")
    @Size(max = 50, message = "Country name cannot be longer than 50 characters")
    private String country; // Country
    
}
