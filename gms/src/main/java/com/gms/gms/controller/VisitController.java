package com.gms.gms.controller;

import com.gms.gms.entity.ActivityType;
import com.gms.gms.entity.Visitor;
import com.gms.gms.service.VisitService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public record VisitController(
        VisitService visitService
) {
    @PostMapping("/guard/createVisitor")
    public ResponseEntity<?> createV(@Valid @RequestBody Visitor visitor, long gate_id, ActivityType activityType) {
        return visitService.createVisit(visitor, gate_id, activityType);
    }
}
