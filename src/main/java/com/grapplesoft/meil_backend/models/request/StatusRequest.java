package com.grapplesoft.meil_backend.models.request;

public record StatusRequest(
        Integer statusid,
        String status,
        Integer createuserid,
        Integer edituserid,
        boolean isdeleted
) {
}

