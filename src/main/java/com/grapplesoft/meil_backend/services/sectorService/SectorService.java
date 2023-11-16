package com.grapplesoft.meil_backend.services.sectorService;

import com.grapplesoft.meil_backend.models.Result;
import com.grapplesoft.meil_backend.models.entities.Sector;
import com.grapplesoft.meil_backend.models.request.SectorRequest;
import com.grapplesoft.meil_backend.models.response.SectorResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SectorService {
    // Method signature for inserting a sector using data from SectorRequest
   Sector insert(SectorRequest sector);

    // Method signature for retrieving all sectors
    List<SectorResponse> getall();

    // Method signature for deleting a sector by its ID
    boolean delete(String id);

    // Method signature for editing an existing sector using data from SectorRequest
    Sector edit(SectorRequest sectorRequest);
}
