package com.grapplesoft.meil_backend.services.statusService;

import com.grapplesoft.meil_backend.builders.StatusBuilder;
import com.grapplesoft.meil_backend.models.Result;
import com.grapplesoft.meil_backend.models.entities.Employee;
import com.grapplesoft.meil_backend.models.entities.Status;
import com.grapplesoft.meil_backend.models.request.StatusRequest;
import com.grapplesoft.meil_backend.models.response.StatusResponse;
import com.grapplesoft.meil_backend.repositories.EmployeeRepository;
import com.grapplesoft.meil_backend.repositories.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    public EmployeeRepository employeeRepository;

    @Autowired
    public StatusRepository statusRepository;

    @Override
    public Result<Status> insert(StatusRequest statusRequest) {
        Status status = new Status();
       // status.setId(statusRequest.statusid());
        status.setStatus(statusRequest.status());
        status.setCreatedate(LocalDate.now());
        if(statusRequest.createuserid()!=null) {
            Employee status1 = employeeRepository.findById(statusRequest.createuserid()).orElse(null);
            if (status1 == null) {
                return Result.failure(new Throwable("No User ID is found "));
            }
            status.setCreateuserid(status1);
        }
       Status status2 = statusRepository.save(status);
            return Result.success(status2);

    }

    @Override
    public Result<Status> edit(StatusRequest statusRequest) {
        Status status = statusRepository.findById(statusRequest.statusid()).orElse(null);
        if (status != null) {
            status.setStatus(statusRequest.status());
            status.setEditdate(LocalDate.now());
            if (statusRequest.edituserid() != null) {
                Status status1 = statusRepository.findById(statusRequest.edituserid()).orElse(null);
                if (status1 != null) {
                    return Result.failure((new Throwable("No ID Found")));
                }
            }
                Status status2 = statusRepository.save(status);
                return Result.success(status2);
            }

        return  null;
    }

    @Override
    public List<StatusResponse> getall() {
       List<Status> statusList=statusRepository.findAll();
       List<StatusResponse> statusResponses=new ArrayList<>();
       for (Status status:statusList){
           statusResponses.add(StatusBuilder.statusResponse(status));
       }
       return statusResponses;
    }

    @Override
    public boolean delete(int id) {
        Map<String,Object> stringObjectMap=new HashMap<>();
        Status status=statusRepository.findById(id).orElse(null);
        if (status !=null){
            if (statusRepository.findByIdAndIsdeleted(id,true)==null){
                status.setIsdeleted(true);
                status.setEditdate(LocalDate.now());
                statusRepository.save(status);
                return true;
            } else {
                return false;
            }
        }else {
            return false;
        }
    }
}
