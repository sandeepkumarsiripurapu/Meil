package com.grapplesoft.meil_backend.models.request;

import java.math.BigDecimal;

public record ProjectRequest(
        Long projid,
        String projname,
        Long dupprojid,
        Integer status,
        String sectorcode,
        Long projhodid,
        Long pmrepauthid,
        Long projcoordid,
        Long hsecoordid,
        Long hsemgrid,
        String statecode,
        BigDecimal projvalue,
        boolean isoandm,
         String remarks,
        Long createuserid,
        Long edituserid,
        boolean isdeleted

) {
}
