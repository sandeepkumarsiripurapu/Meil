package com.grapplesoft.meil_backend.builders;

import com.grapplesoft.meil_backend.models.entities.Projectsite;
import com.grapplesoft.meil_backend.models.response.ProjectsiteResponse;

public class ProjectsiteBuilder {
    public static ProjectsiteResponse generateresp(Projectsite projsite){
        return new ProjectsiteResponse(
                projsite.getId(),
                projsite.getProjid()!=null?ProjectBuilder.projectResponse(projsite.getProjid()):null,
                projsite.getSitename(),
                projsite.getSitemanagerid()!=null?EmployeeBuilder.buildEmployeeWithoutCreds(projsite.getSitemanagerid()):null,
                projsite.getProjcoordid()!=null?EmployeeBuilder.buildEmployeeWithoutCreds(projsite.getProjcoordid()):null,
                projsite.getCourierpcode()!=null?EmployeeBuilder.buildEmployeeWithoutCreds(projsite.getCourierpcode()):null,
       projsite.getCourierpmobile(),
                projsite.getAddressid()!=null?AddressBuilder.buildaddressResponse(projsite.getAddressid()):null,
                projsite.getLatitude(),
                projsite.getLongitude(),
                projsite.getCreatedate(),
                projsite.getCreateuserid()!=null?EmployeeBuilder.buildEmployeeWithoutCreds(projsite.getCreateuserid()):null,
                projsite.getEditdate(),
                projsite.getEdituserid()!=null?EmployeeBuilder.buildEmployeeWithoutCreds(projsite.getEdituserid()):null,
                projsite.getIsdeleted()

        );
    }
}

