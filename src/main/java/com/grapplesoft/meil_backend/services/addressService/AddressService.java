package com.grapplesoft.meil_backend.services.addressService;

import com.grapplesoft.meil_backend.models.Result;
import com.grapplesoft.meil_backend.models.entities.Address;
import com.grapplesoft.meil_backend.models.request.AddressRequest;
import com.grapplesoft.meil_backend.models.response.AddressResponse;

import javax.swing.*;
import java.util.List;

public interface AddressService {
    Result <Address> insert(AddressRequest addressRequest);

    Result<Address> edit(AddressRequest addressRequest);

    List<AddressResponse> getall();

    boolean delete(int id);
}
