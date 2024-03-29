package com.grapplesoft.meil_backend.services.statusService;

import com.grapplesoft.meil_backend.models.Result;
import com.grapplesoft.meil_backend.models.entities.Status;
import com.grapplesoft.meil_backend.models.request.StatusRequest;
import com.grapplesoft.meil_backend.models.response.StatusResponse;
import jdk.dynalink.linker.LinkerServices;

import java.util.List;

public interface StatusService {
    Result <Status> insert(StatusRequest statusRequest);

    Result <Status> edit(StatusRequest statusRequest);

    List<StatusResponse> getall();

    boolean delete(int id);
}
