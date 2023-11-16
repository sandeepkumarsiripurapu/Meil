package com.grapplesoft.meil_backend.repositories;

import com.grapplesoft.meil_backend.models.entities.Department;
import com.grapplesoft.meil_backend.models.entities.EmpStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmpStatusRepository extends JpaRepository<EmpStatus, Integer> {
    EmpStatus findByIdAndIsdeleted(Integer integer,boolean empstsb);

    @Query(value = "SELECT * FROM empstatus WHERE isdeleted is null",nativeQuery = true)
    List<EmpStatus> findAll();
}
