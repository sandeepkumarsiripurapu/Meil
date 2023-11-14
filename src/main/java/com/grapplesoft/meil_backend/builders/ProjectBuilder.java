package com.grapplesoft.meil_backend.builders;

import com.grapplesoft.meil_backend.models.entities.Project;
import com.grapplesoft.meil_backend.models.response.ProjectResponse;

public class ProjectBuilder {
    public static ProjectResponse projectResponse(Project project){
        return new ProjectResponse(
                project.getId(),
                project.getProjname(),
                project.getStatus() !=null ? StatusBuilder.statusResponse(project.getStatus()):null,
                project.getDupprojid() !=null ? ProjectBuilder.projectResponse(project.getDupprojid()):null,
                project.getSectorcode() !=null ? SectorBuilder.SectorResponse(project.getSectorcode()):null,
        );
    }
}
