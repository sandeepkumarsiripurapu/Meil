package com.grapplesoft.meil_backend.controllers;

import com.grapplesoft.meil_backend.builders.ApiResponseBuilder;
import com.grapplesoft.meil_backend.models.Result;
import com.grapplesoft.meil_backend.models.request.ProjectRequest;
import com.grapplesoft.meil_backend.services.projectService.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Project")
public class ProjectController {


    @Autowired
    public ProjectService projectService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody ProjectRequest projectRequest){
        Result result=projectService.insert(projectRequest);
        if (((Result<?>) result).isSuccess()){
            return  ResponseEntity.ok(ApiResponseBuilder.success(result.value(),"Project Added Sucessfully"));
        }else {
            return ResponseEntity.badRequest().body(ApiResponseBuilder.badRequest(result.error().getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<?> edit(@RequestBody ProjectRequest projectRequest){
        if (projectService.edit(projectRequest)!=null){
            return ResponseEntity.ok(ApiResponseBuilder.success(null,"Project Updated Sucessfully"));
        }else {
            return ResponseEntity.badRequest().body(ApiResponseBuilder.badRequest("Something Went Wrong"));
        }
    }

    @GetMapping
    public ResponseEntity<?> getall(){
        return ResponseEntity.ok(projectService.getall());
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody  ProjectRequest projectRequest){
        if (projectService.delete(projectRequest.projid())){
            return  ResponseEntity.ok(ApiResponseBuilder.success(null,"Project Deleted Sucessfully"));
        }else {
            return  ResponseEntity.badRequest().body(ApiResponseBuilder.badRequest("Something Went Wrong"));
        }
    }

}

