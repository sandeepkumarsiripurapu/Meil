package com.grapplesoft.meil_backend.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.grapplesoft.meil_backend.models.EmployeeWithoutCreds;
import com.grapplesoft.meil_backend.models.entities.Address;
import com.grapplesoft.meil_backend.models.entities.Employee;
import com.grapplesoft.meil_backend.models.entities.Project;

import java.math.BigDecimal;
import java.time.LocalDate;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public record ProjectsiteResponse(
        @JsonProperty("projsiteId")
        Long Id,
        @JsonProperty("projId")
        ProjectResponse projId,
        @JsonProperty("siteName")
        String siteName,

        @JsonProperty("sitemanagerId")
        EmployeeWithoutCreds sitemanagerId,
        @JsonProperty("projcoordId")
        EmployeeWithoutCreds projcoordid,
        @JsonProperty("courierpCode")
        EmployeeWithoutCreds courierpCode,
        @JsonProperty("courierpMobile")
        String courierpMobile,
        @JsonProperty("addressId")
        AddressResponse addressId,
        @JsonProperty("latitude")
        BigDecimal latitude,
        @JsonProperty("longitude")
        BigDecimal longitude,

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
