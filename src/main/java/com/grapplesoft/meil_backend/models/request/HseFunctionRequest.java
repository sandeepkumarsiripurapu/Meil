package com.grapplesoft.meil_backend.models.request;

public record HseFunctionRequest(
        String hsefunccode,
        String hsefuncion_name,
        Long createuserid,
        Long edituserid,
        Boolean isdeleted
) {
}
