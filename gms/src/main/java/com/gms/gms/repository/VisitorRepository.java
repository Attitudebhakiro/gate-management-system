package com.gms.gms.repository;

import com.gms.gms.entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface VisitorRepository extends JpaRepository<Visitor, Long> {
}
