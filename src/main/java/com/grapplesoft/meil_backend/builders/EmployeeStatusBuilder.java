package com.grapplesoft.meil_backend.builders;

import com.grapplesoft.meil_backend.models.entities.EmpStatus;
import com.grapplesoft.meil_backend.models.response.EmpStatusResponse;

public class EmployeeStatusBuilder {
    public static EmpStatusResponse genareteresp(EmpStatus empsts){
        return new EmpStatusResponse(

                empsts.getId(),
                empsts.getEmpstatus(),
                empsts.getCreatedate(),
                empsts.getCreateuserid() != null ? EmployeeBuilder.buildEmployeeWithoutCreds(empsts.getCreateuserid()) : null,
                empsts.getEditdate(),
                empsts.getEdituserid() != null ? EmployeeBuilder.buildEmployeeWithoutCreds(empsts.getEdituserid()) : null,
                empsts.getIsdeleted()

        );
    }
}
