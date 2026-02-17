package com.gms.gms.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gms.gms.appsecurity.entity.User;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "visits")
@Data
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "visitor_id")
//    private Visitor visitor;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "visitor_id")
    private Visitor visitor;


    @ManyToOne(optional = false)
    @JoinColumn(name = "gate_id", nullable = false)
    private Gate gate;


    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false, updatable = false)
    private LocalDateTime checked_in_at;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column( updatable = false)
    private LocalDateTime checked_out_at;

    private String pass_code;

    @ManyToOne
    @JoinColumn(name = "created_by_id")
    private User created_by;


}
