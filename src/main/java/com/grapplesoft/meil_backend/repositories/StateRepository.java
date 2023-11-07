package com.grapplesoft.meil_backend.repositories;

import com.grapplesoft.meil_backend.models.entities.State;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StateRepository extends JpaRepository<State, String> {
    State findByStatecodeAndIsdeleted(String stc,boolean sts);
    // This interface extends JpaRepository, providing several built-in methods for CRUD operations.

    // The JpaRepository is a part of Spring Data and extends PagingAndSortingRepository, which, in turn, extends CrudRepository.
    // It includes methods like save(), findById(), findAll(), deleteById(), and others out-of-the-box.

    // The purpose of this repository interface is to manage database operations related to the State entity.

    // The generic types indicate that this repository is dealing with the 'State' entity and uses a 'String' type for its primary key.

    // Spring Data JPA automatically generates implementations for these methods at runtime based on the method signatures defined in the interface.

    // save() method is used to persist or update entities into the database.
    // findById() is used to retrieve an entity by its ID.
    // findAll() is used to retrieve all entities of the State type from the database.
    // deleteById() is used to delete an entity by its ID.

    // JpaRepository simplifies the data access layer and provides the necessary CRUD functionalities,
    // allowing the service layer to interact with the database without explicitly implementing these methods.

    // The provided interface acts as a contract for managing State entities and leverages Spring Data JPA's features for database operations.
}
