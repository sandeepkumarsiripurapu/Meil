package com.grapplesoft.meil_backend.repositories;

import com.grapplesoft.meil_backend.models.entities.EmpStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpStatusRepository extends JpaRepository<EmpStatus, Integer> {
    EmpStatus findByIdAndIsdeleted(Integer integer,boolean empstsb);
}
