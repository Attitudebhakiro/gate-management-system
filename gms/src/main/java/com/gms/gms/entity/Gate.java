package com.gms.gms.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "gates")
@Data
public class Gate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp="^[a-zA-Z_ ]*$",message="Name can only have letters")
    @NotEmpty(message = "Gate name is required")
    @Size(min = 5, max = 30, message = "Invalid name")
    private String name;

    private Double latitude;
    private Double longitude;

    @OneToMany(mappedBy = "gate")
    private List<Visit> visits;
}
