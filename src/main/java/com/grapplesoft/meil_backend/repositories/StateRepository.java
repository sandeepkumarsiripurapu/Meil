package com.grapplesoft.meil_backend.repositories;

import com.grapplesoft.meil_backend.models.entities.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface StateRepository extends JpaRepository<State, String> {

    State findByStatecodeAndIsdeleted(String stc,boolean sts);

}
