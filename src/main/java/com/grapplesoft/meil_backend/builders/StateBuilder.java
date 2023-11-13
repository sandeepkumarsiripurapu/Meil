package com.grapplesoft.meil_backend.builders;

import com.grapplesoft.meil_backend.models.entities.State;
import com.grapplesoft.meil_backend.models.response.StateResponse;

public class StateBuilder {

    public static StateResponse buildStateResponse(State state) {
        return new StateResponse(
                state.getStatecode(),
                state.getStatename(),
                state.getCreatedate(),
                state.getCreateuserid() != null ? EmployeeBuilder.buildEmployeeWithoutCreds(state.getCreateuserid()) : null,
                state.getEditdate(),
                state.getEdituserid() != null ? EmployeeBuilder.buildEmployeeWithoutCreds(state.getEdituserid()) : null,
                state.getIsdeleted()
        );
    }
}
