package com.grapplesoft.meil_backend.models.request.transactions;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record Employeejoinsite
        (
                              @JsonProperty("projectId")
                               Long Actiontype,
                               @JsonProperty("actiondate")
                               LocalDate actiondate,
                               @JsonProperty("employeeid")
                               Long employeeid,
                               @JsonProperty("fromProjectId")
                               Long fromprojectid,
                               @JsonProperty("projectsiteid")
                               Long projectsiteid,
                               @JsonProperty("date1")
                               LocalDate date1
){ }
