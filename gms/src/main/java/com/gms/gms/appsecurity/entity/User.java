package com.gms.gms.appsecurity.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users",uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "email"
        })
      })
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp="^[a-zA-Z_ ]*$",message="Name can only have letters")
    @NotEmpty(message = "Name is required")
    @Size(min = 5, max = 30, message = "Invalid name")
    private String name;


    @Email(message = "Invalid email")
    private String email;


    @Email(message = "Invalid email")
    @Size(min = 4, max = 30, message = "Username must be between 4 and 30 characters")
    @Column(nullable = false, unique = true)
    private String username;

    @NotEmpty(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    @Column(nullable = false)
    private String password;

    @NotNull(message = "Role is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    private Boolean userStatus;


    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false, updatable = true)
    private LocalDateTime updatedAt;
}
