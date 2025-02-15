package com.example.demo.model.embeddables;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


@Embeddable
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

    public ContactInfo() {}

    public ContactInfo(String email, String phoneNumber) {
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    @Override
    public String toString() {
        return "ContactInfo{" +
                "email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
