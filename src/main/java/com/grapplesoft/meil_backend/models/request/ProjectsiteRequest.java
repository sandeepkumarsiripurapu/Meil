package com.grapplesoft.meil_backend.models.request;

import java.math.BigDecimal;

public record ProjectsiteRequest(

        Long id,
        Long projid,
        String sitename,
        Long sitemanagerid,
        Long projcoordid,
        Long courierpcode,
        String courierpmobile,
        Integer addressid,
        BigDecimal latitude,
        BigDecimal longitude,
        Long createuserid,
        Long edituserid,
        boolean isdeleted

) {
}
