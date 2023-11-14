package com.grapplesoft.meil_backend.builders;

import com.grapplesoft.meil_backend.models.entities.Department;
import com.grapplesoft.meil_backend.models.response.DepartmentResponse;

public class DepartmentBuilder {

    public static DepartmentResponse builddepartmentResponse(Department department){
        return  new DepartmentResponse(
                department.getDeptcode(),
                department.getDeptname(),
                department.getCreatedate(),
                department.getCreateuserid() !=null ? EmployeeBuilder.buildEmployeeWithoutCreds(department.getCreateuserid()):null,
                department.getEditdate(),
                department.getEdituserid() !=null ? EmployeeBuilder.buildEmployeeWithoutCreds(department.getEdituserid()):null,
                department.getIsdeleted()
        );
    }
}
