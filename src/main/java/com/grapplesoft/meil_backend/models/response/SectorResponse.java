package com.grapplesoft.meil_backend.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.grapplesoft.meil_backend.models.EmployeeWithoutCreds;

import java.time.LocalDate;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public record SectorResponse(
        @JsonProperty("sectorCode")
        String sectorCode,

        @JsonProperty("sectorName")
        String sectorName,

        @JsonProperty("createDate")
        LocalDate createDate,

        @JsonProperty("createUserId")
        EmployeeWithoutCreds createUserId,

        @JsonProperty("editDate")
        LocalDate editDate,

        @JsonProperty("editUserId")
        EmployeeWithoutCreds editUserId,

        @JsonProperty("isDeleted")
        Boolean isDeleted
) {
}
