package com.grapplesoft.meil_backend.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record SectorRequest(

         String sectorcode,
         String sectorname,

         Long hohsemgrid,
         Long createuserid,
         Long edituserid,

         Boolean isdeleted
) {
}
