package com.grapplesoft.meil_backend.controllers;

import com.grapplesoft.meil_backend.builders.ApiResponseBuilder;
import com.grapplesoft.meil_backend.models.entities.Sector;
import com.grapplesoft.meil_backend.models.request.SectorRequest;
import com.grapplesoft.meil_backend.models.response.ApiResponse;
import com.grapplesoft.meil_backend.services.sectorService.SectorService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Sector")
public class SectorController {
    @Autowired
    public SectorService sectorService;

    // Method to handle HTTP POST requests for adding a sector
    @PostMapping
    public ResponseEntity<?> add(@RequestBody SectorRequest sector) {
        Sector sec= sectorService.insert(sector);
        if (sec != null) {
            return ResponseEntity.ok(ApiResponseBuilder.success(null, "Sector added successfully"));
        } else {
            return ResponseEntity.badRequest().body(ApiResponseBuilder.badRequest("some thing went wrong to add sector"));
        }
    }

    // Method to handle HTTP PUT requests for editing a sector
    @PutMapping
    public ResponseEntity<?> edit(@RequestBody SectorRequest sector) {
        Sector sec= sectorService.edit(sector);
        if (sec != null) {
            return ResponseEntity.ok(ApiResponseBuilder.success(null, "Sector updated successfully"));
        } else {
            return ResponseEntity.badRequest().body(ApiResponseBuilder.badRequest("some thing went wrong to updating"));
        }
    }

    // Method to handle HTTP GET requests for retrieving all sectors
    @GetMapping
    public ResponseEntity<ApiResponse> getall() {

        return ResponseEntity.ok(ApiResponseBuilder.success(sectorService.getall(),null));
    }
    // Method to handle HTTP DELETE requests for deleting a sector
    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody @NotNull Sector sector) {
        boolean res= sectorService.delete(sector.getSectorcode());
        if (res) {
            return ResponseEntity.ok(ApiResponseBuilder.success(null, "sector deleted successfully"));
        } else {
            return ResponseEntity.badRequest().body(ApiResponseBuilder.badRequest("some thing went wrong to delete sector"));
        }
    }
}
