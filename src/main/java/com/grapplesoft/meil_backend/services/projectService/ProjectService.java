package com.grapplesoft.meil_backend.services.projectService;

import com.grapplesoft.meil_backend.models.Result;
import com.grapplesoft.meil_backend.models.entities.Address;
import com.grapplesoft.meil_backend.models.entities.Project;
import com.grapplesoft.meil_backend.models.request.AddressRequest;
import com.grapplesoft.meil_backend.models.request.ProjectRequest;
import com.grapplesoft.meil_backend.models.response.ProjectResponse;

import java.util.List;

public interface ProjectService {
    Result<Project> insert(ProjectRequest projectRequest);

    Result<Project> edit(ProjectRequest projectRequest);

    List<ProjectResponse> getall();

    boolean delete(Long id);
}
