package com.grapplesoft.meil_backend.services.hsefunctionService;


import com.grapplesoft.meil_backend.models.entities.Hsefunction;
import com.grapplesoft.meil_backend.models.request.HseFunctionRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface HsefunctionService {
   Hsefunction insert(HseFunctionRequest hsefunctionRequest);

    Hsefunction edit(HseFunctionRequest hsefunctionRequest);

    List<Hsefunction> getall();

    boolean delete(String id);
}
