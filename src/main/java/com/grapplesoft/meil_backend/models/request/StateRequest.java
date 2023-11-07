package com.grapplesoft.meil_backend.models.request;

public record StateRequest(
        String statecode ,
        String statename,

        Long createuserid,
        Long edituserid,
        Boolean isdeleted
) {
}
