package com.grapplesoft.meil_backend.services.empstatusService;

import com.grapplesoft.meil_backend.models.Result;
import com.grapplesoft.meil_backend.models.entities.EmpStatus;
import com.grapplesoft.meil_backend.models.request.EmpStatusRequest;

import java.util.List;

public interface EmpStatusService {
    Result<EmpStatus> insert(EmpStatusRequest empStatusRequest);

    Result<EmpStatus> edit(EmpStatusRequest empStatusRequest);

    List<EmpStatus> getall();

    boolean delete(int id);
}
