package com.grapplesoft.meil_backend.models.request.transactions;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record T111(
        @JsonProperty("actiontype")
        Long Actiontype,
        @JsonProperty("actiondate")
        LocalDate actiondate,
        @JsonProperty("fromProjectId")
        Long fromprojectid,
        @JsonProperty("newstatus")
        int newstatus,
        @JsonProperty("createdby")
        Long createdby
) {
}
