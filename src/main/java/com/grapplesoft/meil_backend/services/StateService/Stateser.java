package com.grapplesoft.meil_backend.services.StateService;

import com.grapplesoft.meil_backend.models.entities.State;
import com.grapplesoft.meil_backend.models.request.SectorRequest;
import com.grapplesoft.meil_backend.models.request.StateRequest;
import com.grapplesoft.meil_backend.models.response.StateResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface Stateser {
 // Method signature for inserting a State into the system
 State insert(StateRequest sts);

 // Method signature for retrieving all States
 List<StateResponse> getall();

 // Method signature for deleting a State by its ID
 boolean delete(String id);

 State edit(StateRequest stateRequest);
}
