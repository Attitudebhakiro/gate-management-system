package com.gms.gms.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "visitors")
@Data
public class Visitor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp="^[a-zA-Z_ ]*$",message="Name can only have letters")
    @NotEmpty(message = "Gate name is required")
    @Size(min = 5, max = 30, message = "Invalid name")
    private String full_name;

    @Pattern(regexp = "^[0-9]{9,12}$",
            message = "Phone number must be between 9 and 12 digits")
    @NotEmpty(message = "Phone number is required")
    @Size(min = 9, max = 12, message = "Invalid phone number")
    private String phone;

    private String national_id;

    private String company;
}
