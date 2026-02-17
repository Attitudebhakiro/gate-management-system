package com.gms.gms.appsecurity.dto;


import com.gms.gms.appsecurity.entity.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class SignupRequest {

    @Pattern(regexp="^[a-zA-Z_ ]*$",message="Name can only have letters")
    @NotEmpty(message = "Name is required")
    @Size(min = 5, max = 30, message = "Invalid name")
    private String name;



    @NotEmpty(message = "Email is required")
    @Email(message = "Invalid email")
    private String email;


    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    @Column(nullable = false)
    private String password;

    @NotNull(message = "Role is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
}
