package com.gms.gms.appsecurity.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TestRequest {
    @NotBlank(message = "City is required")
    @Size(min = 3, max = 30, message = "City must be between 3 and 30 characters")
    @Column(nullable = false)
    private String city;

    @NotBlank(message = "Country is required")
    @Size(min = 3, max = 30, message = "Country must be between 3 and 30 characters")
    @Column(nullable = false)
    private String country;

}
