package com.grapplesoft.meil_backend.builders;

import com.grapplesoft.meil_backend.models.entities.Hsefunction;
import com.grapplesoft.meil_backend.models.response.HseFunctionResponse;
import org.springframework.jdbc.core.metadata.HsqlTableMetaDataProvider;

public class HseFunctionBuilder {
    public static HseFunctionResponse hseFunctionResponse(Hsefunction hsefunction){
        return new HseFunctionResponse(
                hsefunction.getHsefunccode(),
                hsefunction.getHsefuncionName(),
                hsefunction.getCreatedate(),
                hsefunction.getCreateuserid()!=null?EmployeeBuilder.buildEmployeeWithoutCreds(hsefunction.getCreateuserid()):null,
                hsefunction.getEditdate(),
                hsefunction.getEdituserid()!=null ? EmployeeBuilder.buildEmployeeWithoutCreds(hsefunction.getEdituserid()):null,
                hsefunction.getIsdeleted()
        );
    }
}
