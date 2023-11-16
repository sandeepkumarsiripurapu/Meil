package com.grapplesoft.meil_backend.controllers;

import com.grapplesoft.meil_backend.builders.ApiResponseBuilder;
import com.grapplesoft.meil_backend.models.entities.Hsefunction;
import com.grapplesoft.meil_backend.models.request.HseFunctionRequest;
import com.grapplesoft.meil_backend.models.response.ApiResponse;
import com.grapplesoft.meil_backend.services.hsefunctionService.HsefunctionImp;
import com.grapplesoft.meil_backend.services.hsefunctionService.HsefunctionService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Hsefunction")
public class HseFunctionController {

    @Autowired
    public HsefunctionService hsefunctionService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody HseFunctionRequest hseFunctionRequest) {
        Hsefunction hse= hsefunctionService.insert(hseFunctionRequest);
        if (hse != null) {
            return ResponseEntity.ok(ApiResponseBuilder.success(null, "New hsefunction added successfully"));
        } else {
            return ResponseEntity.badRequest().body(ApiResponseBuilder.badRequest("some thing went wrong to add hsefunction"));
        }
    }

    @PutMapping
    public ResponseEntity<?> edit(@RequestBody HseFunctionRequest hseFunctionRequest) {
        Hsefunction hse= hsefunctionService.edit(hseFunctionRequest);
        if (hse != null) {
            return ResponseEntity.ok(ApiResponseBuilder.success(null, "hsefunction updated successfully"));
        } else {
            return ResponseEntity.badRequest().body(ApiResponseBuilder.badRequest("some thing went wrong to updating"));
        }
    }

    @GetMapping
    public  ResponseEntity<ApiResponse> getall(){

        return ResponseEntity.ok(ApiResponseBuilder.success(hsefunctionService.getall(),null));
    }

    @DeleteMapping
    public  ResponseEntity<?> delete(@RequestBody @NotNull Hsefunction hsefunction){
        boolean bl=  hsefunctionService.delete(hsefunction.getHsefunccode());
        if (bl) {
            return ResponseEntity.ok(ApiResponseBuilder.success(null, "hsefunction deleted successfully"));
        } else {
            return ResponseEntity.badRequest().body(ApiResponseBuilder.badRequest("some thing went wrong to delete hsefunction"));
        }
    }


}