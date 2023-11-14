package com.grapplesoft.meil_backend.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.grapplesoft.meil_backend.models.EmployeeWithoutCreds;
import com.grapplesoft.meil_backend.models.entities.State;

import java.time.LocalDate;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public record AddressResponse(
        @JsonProperty("addressId")
         Integer Id,

        @JsonProperty("addressLine1")
        String addressLine1,
        @JsonProperty("addressLine2")
        String addressLine2,
        @JsonProperty("place")
        String place,
        @JsonProperty("district")
        String district,
        @JsonProperty("stateCode")
        StateResponse stateCode,
        @JsonProperty("stateName")
        String stateName,
        @JsonProperty("pinCode")
        Integer pinCode,
        @JsonProperty("countryName")
        String countryName,


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
