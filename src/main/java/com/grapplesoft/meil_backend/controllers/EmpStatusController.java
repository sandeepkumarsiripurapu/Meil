package com.grapplesoft.meil_backend.controllers;

import com.grapplesoft.meil_backend.builders.ApiResponseBuilder;
import com.grapplesoft.meil_backend.models.Result;
import com.grapplesoft.meil_backend.models.entities.EmpStatus;
import com.grapplesoft.meil_backend.models.request.EmpStatusRequest;
import com.grapplesoft.meil_backend.models.response.ApiResponse;
import com.grapplesoft.meil_backend.services.empstatusService.EmpStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("EmpStatus")
public class EmpStatusController {

    @Autowired
    public EmpStatusService empStatusService;

    @PostMapping

    public ResponseEntity<?> add(@RequestBody EmpStatusRequest empStatusRequest) {
        Result result = empStatusService.insert(empStatusRequest);
        if (result.isSuccess()) {
            return ResponseEntity.ok(ApiResponseBuilder.success(result.value(), "EmpStatus Added Sucessfully"));
        } else {
            return ResponseEntity.badRequest().body(ApiResponseBuilder.badRequest(result.error().getMessage()));
        }
    }

    @PutMapping

    public ResponseEntity<?> edit(@RequestBody EmpStatusRequest empStatusRequest) {
        if (empStatusService.edit(empStatusRequest) != null) {
            return ResponseEntity.ok(ApiResponseBuilder.success(null, "EmpStatus Updated Sucessfully"));
        } else {
            return ResponseEntity.badRequest().body(ApiResponseBuilder.badRequest("Somthing Went Wrong"));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getall() {

        return ResponseEntity.ok(ApiResponseBuilder.success(empStatusService.getall(),null));
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody EmpStatus empStatus) {
        if (empStatusService.delete(empStatus.getId())) {
            return ResponseEntity.ok(ApiResponseBuilder.success(null, "EmpStatus Deleted Sucessfully"));
        } else {
            return ResponseEntity.badRequest().body(ApiResponseBuilder.badRequest("something Went Wrong"));
        }
    }
}

