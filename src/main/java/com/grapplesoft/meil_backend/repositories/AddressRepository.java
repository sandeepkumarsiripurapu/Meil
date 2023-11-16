package com.grapplesoft.meil_backend.repositories;

import com.grapplesoft.meil_backend.models.entities.Address;
import com.grapplesoft.meil_backend.models.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    Address findByIdAndIsdeleted(Integer integer,boolean adrs);

    @Query(value = "SELECT * FROM address WHERE isdeleted is null",nativeQuery = true)
    List<Address> findAll();
}
