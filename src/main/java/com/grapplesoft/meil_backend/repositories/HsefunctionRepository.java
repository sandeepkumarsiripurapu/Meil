package com.grapplesoft.meil_backend.repositories;

import com.grapplesoft.meil_backend.models.entities.Hsefunction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HsefunctionRepository extends JpaRepository<Hsefunction,String> {

    Hsefunction findByHsefunccodeAndIsdeleted(String string,boolean hseboolean);
}
