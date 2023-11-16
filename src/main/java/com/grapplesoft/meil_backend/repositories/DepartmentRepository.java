package com.grapplesoft.meil_backend.repositories;

import com.grapplesoft.meil_backend.models.entities.Address;
import com.grapplesoft.meil_backend.models.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, String> {
    Department findByDeptcodeAndIsdeleted(String departmentCode, boolean isDeleted);

    @Query(value = "SELECT * FROM department WHERE isdeleted is null",nativeQuery = true)
    List<Department> findAll();

}