package com.grapplesoft.meil_backend.services.hsefunctionService;


import com.grapplesoft.meil_backend.models.entities.Hsefunction;
import com.grapplesoft.meil_backend.models.request.HseFunctionRequest;
import com.grapplesoft.meil_backend.models.response.HseFunctionResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface HsefunctionService {
   Hsefunction insert(HseFunctionRequest hsefunctionRequest);

    Hsefunction edit(HseFunctionRequest hsefunctionRequest);

    List<HseFunctionResponse> getall();

    boolean delete(String id);
}
