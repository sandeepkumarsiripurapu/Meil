package com.grapplesoft.meil_backend.builders;

import com.grapplesoft.meil_backend.models.entities.Project;
import com.grapplesoft.meil_backend.models.response.ProjectResponse;

public class ProjectBuilder {
    public static ProjectResponse projectResponse(Project project) {
        return new ProjectResponse(
                project.getId(),
                project.getProjname(),
                project.getStatus() != null ? StatusBuilder.statusResponse(project.getStatus()) : null,
                project.getDupprojid() != null ? ProjectBuilder.projectResponse(project.getDupprojid()) : null,
                project.getSectorcode() != null ? SectorBuilder.generatesector(project.getSectorcode()) : null,
                project.getProjhodid() != null ? EmployeeBuilder.buildEmployeeWithoutCreds(project.getProjhodid()) : null,
                project.getPmrepauthid() != null ? EmployeeBuilder.buildEmployeeWithoutCreds(project.getPmrepauthid()) : null,
                project.getProjcoordid()!=null?EmployeeBuilder.buildEmployeeWithoutCreds(project.getProjcoordid()):null,
                project.getHsecoordid()!=null?EmployeeBuilder.buildEmployeeWithoutCreds(project.getHsecoordid()):null,
                project.getHsemgrid()!=null?EmployeeBuilder.buildEmployeeWithoutCreds(project.getHsemgrid()):null,
                project.getProjvalue(),
               project.getIsoandm(),
                project.getRemarks(),
                project.getCreatedate(),
                project.getCreateuserid() != null ? EmployeeBuilder.buildEmployeeWithoutCreds(project.getCreateuserid()) : null,
                project.getEditdate(),
                project.getEdituserid() != null ? EmployeeBuilder.buildEmployeeWithoutCreds(project.getEdituserid()) : null,
                project.getIsdeleted()
                );
    }
}
