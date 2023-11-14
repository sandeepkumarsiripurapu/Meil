package com.grapplesoft.meil_backend.builders;

import com.grapplesoft.meil_backend.models.entities.Status;
import com.grapplesoft.meil_backend.models.response.StatusResponse;

public class StatusBuilder {
    public static StatusResponse statusResponse(Status status){
        return new StatusResponse(
                status.getId(),
                status.getStatus(),
                status.getCreatedate(),
                status.getCreateuserid()!=null? EmployeeBuilder.buildEmployeeWithoutCreds(status.getCreateuserid()):null,
                status.getEditdate(),
                status.getEditdate()!=null?EmployeeBuilder.buildEmployeeWithoutCreds(status.getEdituserid()):null,
                status.getIsdeleted()
        );
    }
}
