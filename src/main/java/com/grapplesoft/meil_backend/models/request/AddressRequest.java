package com.grapplesoft.meil_backend.models.request;

public record AddressRequest(
        Integer addressid,
        String addressline1,
        String addressline2,
        String place,
        String district,
        String statecode,
        String statename,
        Integer pincode,
        String countryname,
        Long createuserid,
        Long edituserid,
        boolean isdeleted


) {
}
