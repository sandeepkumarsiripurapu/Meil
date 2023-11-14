package com.grapplesoft.meil_backend.builders;

import com.grapplesoft.meil_backend.models.entities.Sector;
import com.grapplesoft.meil_backend.models.response.SectorResponse;

public class SectorBuilder {
    //convert sector data into sector response
    public static SectorResponse generatesector(Sector sector){
        return new SectorResponse(
                sector.getSectorcode(),
                sector.getSectorname(),
                sector.getCreatedate(),
                sector.getCreateuserid() != null ? EmployeeBuilder.buildEmployeeWithoutCreds(sector.getCreateuserid()) : null,
                sector.getEditdate(),
                sector.getEdituserid() != null ? EmployeeBuilder.buildEmployeeWithoutCreds(sector.getEdituserid()) : null,
                sector.getIsdeleted()
        );
    }
}
