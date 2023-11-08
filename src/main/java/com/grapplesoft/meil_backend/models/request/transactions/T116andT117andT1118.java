package com.grapplesoft.meil_backend.models.request.transactions;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record T116andT117andT1118(
        @JsonProperty("projectId")
        Long Actiontype,
        @JsonProperty("actiondate")
        LocalDate actiondate,
        @JsonProperty("employeeid")
        Long employeeid,
        @JsonProperty("fromProjectId")
        Long fromprojectid,
        @JsonProperty("date2")
        LocalDate date2,
        @JsonProperty("date1")
        LocalDate date1
) {
}
