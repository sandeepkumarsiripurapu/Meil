package com.grapplesoft.meil_backend.controllers;

import com.grapplesoft.meil_backend.builders.ApiResponseBuilder;
import com.grapplesoft.meil_backend.builders.StateBuilder;
import com.grapplesoft.meil_backend.models.entities.State;
import com.grapplesoft.meil_backend.models.request.StateRequest;
import com.grapplesoft.meil_backend.services.StateService.Stateser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("State")
public class StateController {
    @Autowired
    public Stateser stateservice;

    // Method to handle HTTP POST requests for adding a state
    @PostMapping
    public ResponseEntity<?> add(@RequestBody StateRequest st) {

        State sts = stateservice.insert(st);
        if (sts != null) {
            return ResponseEntity.ok(ApiResponseBuilder.success(null, "State added successfully"));
        } else {
            return ResponseEntity.badRequest().body(ApiResponseBuilder.badRequest("some thing went wrong to add state"));
        }

    }

    @PutMapping
    public ResponseEntity<?> edit(@RequestBody StateRequest sector) {
        State str = stateservice.edit(sector);
        if (str != null) {
            return ResponseEntity.ok(ApiResponseBuilder.success(null, "State updated successfully"));
        } else {
            return ResponseEntity.badRequest().body(ApiResponseBuilder.badRequest("something went wrong to update state"));
        }
    }

    // Method to handle HTTP GET requests for retrieving all states
    @GetMapping
    public ResponseEntity<?> getall() {
        var res = stateservice.getall();
        return ResponseEntity.ok(res);
    }

    // Method to handle HTTP DELETE requests for deleting a state
    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody StateRequest sts) {

        boolean result = stateservice.delete(sts.statecode());
        if (result) {
            return ResponseEntity.ok(ApiResponseBuilder.success(null, "State deleted successfully"));
        } else {
            return ResponseEntity.badRequest().body(ApiResponseBuilder.badRequest("error occures in deleting state"));
        }
    }
}
