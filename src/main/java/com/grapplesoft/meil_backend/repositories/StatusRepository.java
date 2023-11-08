package com.grapplesoft.meil_backend.repositories;

import com.grapplesoft.meil_backend.models.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status,Integer> {
    Status findByIdAndIsdeleted(int integer,boolean Bsts);
}
