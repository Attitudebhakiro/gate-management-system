package com.gms.gms.service;

import com.gms.gms.appsecurity.entity.User;
import com.gms.gms.appsecurity.repository.UserRepository;
import com.gms.gms.constants.CurrentUser;
import com.gms.gms.constants.ReferenceNumber;
import com.gms.gms.entity.ActivityType;
import com.gms.gms.entity.Gate;
import com.gms.gms.entity.Visit;
import com.gms.gms.entity.Visitor;
import com.gms.gms.repository.GateRepository;
import com.gms.gms.repository.VisitRepository;
import com.gms.gms.repository.VisitorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public record VisitService(
        VisitRepository visitRepository,
        VisitorRepository visitorRepository,
        UserRepository userRepository,
        GateRepository gateRepository
) {
    public ResponseEntity<?> createVisit(Visitor visitor, long gate_id, ActivityType activityType) {
        try{
            Map<String,Object> response = new HashMap<>();
            String passCode = ReferenceNumber.generateUniqueReference(8);
            Optional<User> user = userRepository.findByUsername(CurrentUser.currentUsername());
            Optional<Gate> gate = gateRepository.findById(gate_id);

            Visit visit = new Visit();
            visit.setVisitor(visitor);
            visit.setGate(gate.get());
            visit.setPass_code(passCode);
            visit.setCreated_by(user.get());
            Visit visitSaved = visitRepository.save(visit);
            response.put("code", "200");
            response.put("message", "Visit created");
            log.info("Visit created", visitSaved);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        }catch (Exception ex){
            Map<String,Object> e = new HashMap<>();
            e.put("code", "400");
            e.put("", null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }
}
