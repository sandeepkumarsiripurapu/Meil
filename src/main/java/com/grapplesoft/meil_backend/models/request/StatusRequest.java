package com.grapplesoft.meil_backend.models.request;

public record StatusRequest(
        Integer statusid,
        String status,
        Long createuserid,
        Integer edituserid,
        boolean isdeleted
) {
}

