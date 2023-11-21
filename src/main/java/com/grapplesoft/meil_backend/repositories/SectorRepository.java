package com.grapplesoft.meil_backend.repositories;

import com.grapplesoft.meil_backend.models.entities.Sector;
import com.grapplesoft.meil_backend.models.entities.State;
import com.grapplesoft.meil_backend.models.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SectorRepository extends JpaRepository<Sector, String> {
    @Query(value = "SELECT * FROM sector WHERE isdeleted is null",nativeQuery = true)
    List<Sector> findAll();
    Sector findBySectorcodeAndIsdeleted(String stc, boolean sts);

    }
