package com.gms.gms.service;

import com.gms.gms.entity.Gate;

import com.gms.gms.repository.GateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Service
@Slf4j
public record GateService(
        GateRepository gateRepository
) {
    public Gate createGate(Gate gate) {
        if (gateRepository.existsByName(gate.getName())) {
            throw new IllegalArgumentException("Gate with this name already exists");
        }

        return gateRepository.save(gate);
    }

}
