package com.grapplesoft.meil_backend.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.grapplesoft.meil_backend.models.EmployeeWithoutPassword;
import com.grapplesoft.meil_backend.models.entities.Employee;

import java.time.LocalDate;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public record StateResponse(
        @JsonProperty("stateCode")
        String stateCode,

        @JsonProperty("stateName")
        String stateName,

        @JsonProperty("createDate")
        LocalDate createDate,

        @JsonProperty("createUserId")
        EmployeeWithoutPassword createUserId,

        @JsonProperty("editDate")
        LocalDate editDate,

        @JsonProperty("editUserId")
        EmployeeWithoutPassword editUserId,

        @JsonProperty("isDeleted")
        Boolean isDeleted
) {
}
