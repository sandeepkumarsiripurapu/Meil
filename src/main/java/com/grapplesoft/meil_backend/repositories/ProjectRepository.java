package com.grapplesoft.meil_backend.repositories;


import com.grapplesoft.meil_backend.models.entities.Hsefunction;
import com.grapplesoft.meil_backend.models.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project findByIdAndIsdeleted(Long integer, boolean prob);

    @Query(value = "SELECT * FROM project WHERE isdeleted is null",nativeQuery = true)
    List<Project> findAll();

}
