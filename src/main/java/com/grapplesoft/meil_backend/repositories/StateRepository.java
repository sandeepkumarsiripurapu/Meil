package com.grapplesoft.meil_backend.repositories;

import com.grapplesoft.meil_backend.models.entities.Projectsite;
import com.grapplesoft.meil_backend.models.entities.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface StateRepository extends JpaRepository<State, String> {

    @Query(value = "SELECT * FROM state WHERE `isdeleted` = 0", nativeQuery = true)
    List<State> findAllNotDeleted();
    State findByStatecodeAndIsdeleted(String stc,boolean sts);

    @Query(value = "SELECT * FROM state WHERE isdeleted is null",nativeQuery = true)
    List<State> findAll();

}
