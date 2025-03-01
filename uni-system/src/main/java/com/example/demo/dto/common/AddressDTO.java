package com.example.demo.dto.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Address information",
        example = "{" +
                " \"city\": \"Athens\"," +
                " \"country\": \"Greece\"" +
                "}")
public class AddressDTO {

    @Schema(description = "The city of the address", example = "Athens")
    private String city;

    @Schema(description = "The country for the address", example = "Greece")
    private String country;

}
