package com.example.demo.model.embeddables;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Embeddable
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

    public Address() {
    }

    public Address(String street, String country, String postalCode, String state, String city) {
        this.street = street;
        this.country = country;
        this.postalCode = postalCode;
        this.state = state;
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
