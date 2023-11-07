package com.grapplesoft.meil_backend.services.StateService;

import com.grapplesoft.meil_backend.models.entities.Sector;
import com.grapplesoft.meil_backend.models.entities.State;
import com.grapplesoft.meil_backend.models.request.StateRequest;
import com.grapplesoft.meil_backend.repositories.EmployeeRepository;
import com.grapplesoft.meil_backend.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



// Service for managing State entities
@Service
public class Stateimpl implements Stateser {

    // Injecting State Repository and Employee Repository
    @Autowired
    public StateRepository staterepository;
    @Autowired
    public EmployeeRepository employeeRepository;

    /**
     * Method to insert a new state
     * @param stateRequest The StateRequest object containing details of the state to be added
     * @return ResponseEntity containing the status, message, and the added State data
     */
    @Override
    public State insert(StateRequest stateRequest) {
        // Creating a new State entity
        State state = new State();

        // Setting attributes from the StateRequest object
        state.setStatecode(stateRequest.statecode());
        state.setStatename(stateRequest.statename());
        state.setCreatedate(LocalDate.now());
        if (stateRequest.createuserid() != null) {
            state.setCreateuserid(employeeRepository.findById(stateRequest.createuserid()).orElse(null));
        }
        State result = staterepository.save(state);
        return result;
    }

    /**
     * Method to edit an existing state
     * @param stateRequest The StateRequest object containing updated details of the state
     * @return ResponseEntity containing the status, message, and the updated State data
     */
    @Override
    public State edit(StateRequest stateRequest) {
        // Finding the state by its code
        State state = staterepository.findById(stateRequest.statecode()).orElse(null);
        if (state != null) {
            // Updating state attributes
            state.setStatename(stateRequest.statename());
            state.setEditdate(LocalDate.now());
            if (stateRequest.edituserid() != null) {
                // Setting the editor (employee) id
                state.setEdituserid(employeeRepository.findById(stateRequest.edituserid()).orElse(null));
            }
            State result = staterepository.save(state);
           return result;
        } else {
            // If the state is not found, return an error message
           return null;
        }
    }

    /**
     * Method to retrieve all states
     * @return ResponseEntity containing the list of all State entities
     */
    @Override
    public List<State> getall() {
        // Retrieving all states from the repository
        List<State> res = staterepository.findAll();
        return (res);
    }

    /**
     * Method to logically delete a state by its ID
     * @param id The ID of the state to be deleted
     * @return ResponseEntity indicating the status of the deletion process
     */
    @Override
    public boolean delete(String id) {
        Map<String, Object> result = new HashMap<>();
        State state = staterepository.findById(id).orElse(null);
        if (state != null) {
            if (staterepository.findByStatecodeAndIsdeleted(id, true) == null) {
                state.setIsdeleted(true);
                state.setEditdate(LocalDate.now());
                staterepository.save(state);
               return true;
            } else {
                return false;
            }
        } else {
          return false;
        }

    }
}

