package com.grapplesoft.meil_backend.models.request;

public record EmpStatusRequest(

        Integer id,
        String empstatus,
        Long createuserid,
        Long edituserid,
        boolean isdeleted
) {
}
