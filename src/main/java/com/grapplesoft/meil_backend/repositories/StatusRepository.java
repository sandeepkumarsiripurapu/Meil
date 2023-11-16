package com.grapplesoft.meil_backend.repositories;

import com.grapplesoft.meil_backend.models.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StatusRepository extends JpaRepository<Status,Integer> {
    Status findByIdAndIsdeleted(int integer,boolean Bsts);
    @Query(value = "SELECT * FROM status WHERE isdeleted is null",nativeQuery = true)
    List<Status> findAll();
}
