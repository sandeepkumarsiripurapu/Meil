package com.grapplesoft.meil_backend.repositories;

import com.grapplesoft.meil_backend.models.entities.EmpStatus;
import com.grapplesoft.meil_backend.models.entities.Hsefunction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HsefunctionRepository extends JpaRepository<Hsefunction,String> {

    Hsefunction findByHsefunccodeAndIsdeleted(String string,boolean hseboolean);


    @Query(value = "SELECT * FROM hsefunction WHERE isdeleted is null",nativeQuery = true)
    List<Hsefunction> findAll();

}
