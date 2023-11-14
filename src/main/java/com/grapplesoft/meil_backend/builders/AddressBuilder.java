package com.grapplesoft.meil_backend.builders;

import com.grapplesoft.meil_backend.models.entities.Address;
import com.grapplesoft.meil_backend.models.response.AddressResponse;

public class AddressBuilder {
    public static AddressResponse buildaddressResponse(Address address){
        return  new AddressResponse(
                address.getId(),
                address.getAddressline1(),
                address.getAddressline2(),
                address.getPlace(),
                address.getDistrict(),
                address.getStatecode()!=null?StateBuilder.buildStateResponse(address.getStatecode()):null,
                address.getStatename(),
                address.getPincode(),
                address.getCountryname(),
                address.getCreatedate(),
                address.getCreateuserid()!=null?EmployeeBuilder.buildEmployeeWithoutCreds(address.getCreateuserid()):null,
                address.getEditdate(),
                address.getEdituserid()!=null?EmployeeBuilder.buildEmployeeWithoutCreds(address.getEdituserid()):null,
                address.getIsdeleted()
        );
    }
}