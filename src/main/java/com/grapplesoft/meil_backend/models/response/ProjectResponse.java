package com.grapplesoft.meil_backend.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.grapplesoft.meil_backend.models.EmployeeWithoutCreds;
import com.grapplesoft.meil_backend.models.entities.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public record ProjectResponse(
        @JsonProperty("projId")
        Long projId,

        @JsonProperty("projName")
        String projName,
        @JsonProperty("status")
        StatusResponse status,
        @JsonProperty("dupprojId")
        ProjectResponse dupprojId,
        @JsonProperty("sectorCode")
        SectorResponse sectorCode,
        @JsonProperty("projhodId")
        EmployeeWithoutCreds projhodId,
        @JsonProperty("pmrepauthId")
        EmployeeWithoutCreds pmrepauthId,
        @JsonProperty("projcoordId")
        EmployeeWithoutCreds projcoordId,
        @JsonProperty("hsecoordId")
        EmployeeWithoutCreds hsecoordId,
        @JsonProperty("hsemgrId")
        EmployeeWithoutCreds hsemgrId,
        @JsonProperty("projvalue")
        BigDecimal projvalue,
        @JsonProperty("isoandm")
        Boolean isoandm,
        @JsonProperty("remarks")
        String remarks,

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
