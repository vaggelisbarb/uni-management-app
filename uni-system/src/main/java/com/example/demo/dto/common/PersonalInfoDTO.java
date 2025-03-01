package com.example.demo.dto.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "The personal information of the entity",
        example = "{" +
                " \"firstName\": \"John\"," +
                " \"lastName\": \"Doe\" " +
                "}")
public class PersonalInfoDTO {

    @Schema(description = "The first name of the entity", example = "John")
    private String firstName;

    @Schema(description = "The last name of the entity", example = "Doe")
    private String lastName;

}
