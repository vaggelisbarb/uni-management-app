package com.example.demo.dto.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Contact information",
        example = "{ \"email\": \"test@example.com\" }")
public class ContactInfoDTO {

    @Schema(description = "The email address", example = "test@example.com")
    private String email;

}
