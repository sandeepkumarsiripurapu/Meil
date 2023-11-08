package com.grapplesoft.meil_backend.services.projectsiteService;

import com.grapplesoft.meil_backend.models.Result;
import com.grapplesoft.meil_backend.models.entities.Projectsite;
import com.grapplesoft.meil_backend.models.request.ProjectsiteRequest;

import java.util.List;

public interface ProjectsiteService {
    Result<Projectsite> insert(ProjectsiteRequest projectsiteRequest);

    Result<Projectsite> edit(ProjectsiteRequest projectsiteRequest);

    List<Projectsite> getall();

    boolean delete(Long id);
}
