package com.gms.gms.controller;

import com.gms.gms.entity.Gate;
import com.gms.gms.service.GateService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public record GateController(
        GateService gateService
) {
    @PostMapping("/admin/createGate")
    public Gate create(@Valid @RequestBody Gate gate){
        return gateService.createGate(gate);
    }
}
