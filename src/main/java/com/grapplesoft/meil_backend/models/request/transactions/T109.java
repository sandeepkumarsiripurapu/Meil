package com.grapplesoft.meil_backend.models.request.transactions;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record T109(
        @JsonProperty("actionType")
        Long actionType,
        @JsonProperty("actionDate")
        LocalDate actionDate,
        @JsonProperty("employeeId")
        Long employeeId,
        @JsonProperty("fromProjectId")
        Long fromProjectId
) {
}
