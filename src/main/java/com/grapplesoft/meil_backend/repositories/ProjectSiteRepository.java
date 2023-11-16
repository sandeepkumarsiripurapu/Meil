package com.grapplesoft.meil_backend.repositories;

import com.grapplesoft.meil_backend.models.entities.Project;
import com.grapplesoft.meil_backend.models.entities.Projectsite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectSiteRepository extends JpaRepository<Projectsite, Long> {
    Projectsite findByIdAndIsdeleted(Long integer,boolean projsiteb);

    @Query(value = "SELECT * FROM projectsite WHERE isdeleted is null",nativeQuery = true)
    List<Projectsite> findAll();

}
