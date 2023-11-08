package com.grapplesoft.meil_backend.controllers;

import com.grapplesoft.meil_backend.builders.ApiResponseBuilder;
import com.grapplesoft.meil_backend.models.Result;
import com.grapplesoft.meil_backend.models.entities.Address;
import com.grapplesoft.meil_backend.models.entities.Projectsite;
import com.grapplesoft.meil_backend.models.request.AddressRequest;
import com.grapplesoft.meil_backend.models.request.ProjectsiteRequest;
import com.grapplesoft.meil_backend.repositories.ProjectSiteRepository;
import com.grapplesoft.meil_backend.services.projectsiteService.ProjectsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Projectsite")
public class ProjectsiteController {

    @Autowired
    public ProjectsiteService projectsiteService;


    @PostMapping
    public ResponseEntity<?> add(@RequestBody ProjectsiteRequest projectsiteRequest){
        Result result=projectsiteService.insert(projectsiteRequest);
        if (result.isSuccess()){
            return  ResponseEntity.ok(ApiResponseBuilder.success(result.value(),"Projectsite Added Sucessfully"));
        }else {
            return ResponseEntity.badRequest().body(ApiResponseBuilder.badRequest(result.error().getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<?> edit(@RequestBody ProjectsiteRequest projectSiteRepository){
        if (projectsiteService.edit(projectSiteRepository)!=null){
            return ResponseEntity.ok(ApiResponseBuilder.success(null,"Projectsite Updated Sucessfully"));
        }else {
            return ResponseEntity.badRequest().body(ApiResponseBuilder.badRequest("Something Went Wrong"));
        }
    }

    @GetMapping
    public ResponseEntity<?> getall(){
        return ResponseEntity.ok(projectsiteService.getall());
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody Projectsite projectsite){
        if (projectsiteService.delete(projectsite.getId())){
            return  ResponseEntity.ok(ApiResponseBuilder.success(null,"Projectsite Deleted Sucessfully"));
        }else {
            return  ResponseEntity.badRequest().body(ApiResponseBuilder.badRequest("Something Went Wrong"));
        }
    }

}
