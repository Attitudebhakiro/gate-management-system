package com.gms.gms.repository;

import com.gms.gms.entity.Gate;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GateRepository extends JpaRepository<Gate, Long> {
    boolean existsByName(@Pattern(regexp="^[a-zA-Z_ ]*$",message="Name can only have letters") @NotEmpty(message = "Gate name is required") @Size(min = 5, max = 30, message = "Invalid name") String name);
}
