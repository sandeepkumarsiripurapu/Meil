package com.grapplesoft.meil_backend.controllers;

import com.grapplesoft.meil_backend.builders.ApiResponseBuilder;
import com.grapplesoft.meil_backend.models.Result;
import com.grapplesoft.meil_backend.models.entities.Status;
import com.grapplesoft.meil_backend.models.request.StatusRequest;
import com.grapplesoft.meil_backend.models.response.ApiResponse;
import com.grapplesoft.meil_backend.services.statusService.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Status")
public class StatusController {

    @Autowired
    public StatusService statusService;

    @PostMapping

    public ResponseEntity<?> add(@RequestBody StatusRequest statusRequest){
        Result result=statusService.insert(statusRequest);
        if (result.isSuccess()){
            return  ResponseEntity.ok(ApiResponseBuilder.success(result.value(),"Status Added Sucessfully"));
        }else {
            return  ResponseEntity.badRequest().body(ApiResponseBuilder.badRequest(result.error().getMessage()));
        }
    }

    @PutMapping

    public  ResponseEntity<?> edit(@RequestBody StatusRequest statusRequest){
        Result res=statusService.edit(statusRequest);
        if (res.isSuccess()){
            return ResponseEntity.ok(ApiResponseBuilder.success(res.value(),"Status Updated Sucessfully"));
        }else {
            return ResponseEntity.badRequest().body(res.error().getMessage());
        }
    }
    @GetMapping
    public ResponseEntity<ApiResponse>getall(){
        return ResponseEntity.ok(ApiResponseBuilder.success(statusService.getall(),null));
    }

    @DeleteMapping

    public ResponseEntity<?> delete(@RequestBody Status status){
        if (statusService.delete(status.getId())){
            return ResponseEntity.ok(ApiResponseBuilder.success(null,"Status Deleted Sucessfully"));
        }else {
            return ResponseEntity.badRequest().body(ApiResponseBuilder.badRequest("Something Went Wrong"));
        }
    }







}
