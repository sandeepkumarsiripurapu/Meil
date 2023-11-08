package com.grapplesoft.meil_backend.services.empstatusService;

import com.grapplesoft.meil_backend.models.Result;
import com.grapplesoft.meil_backend.models.entities.EmpStatus;
import com.grapplesoft.meil_backend.models.entities.Employee;
import com.grapplesoft.meil_backend.models.request.EmpStatusRequest;
import com.grapplesoft.meil_backend.repositories.EmpStatusRepository;
import com.grapplesoft.meil_backend.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmpStatusServiceImpl implements EmpStatusService{

    @Autowired
    public EmployeeRepository employeeRepository;

    @Autowired
    public EmpStatusRepository empStatusRepository;
    @Override
    public Result<EmpStatus> insert(EmpStatusRequest empStatusRequest) {
        EmpStatus empStatus=new EmpStatus();
        empStatus.setId(empStatusRequest.id());
        empStatus.setEmpstatus(empStatusRequest.empstatus());
        empStatus.setCreatedate(LocalDate.now());
        Employee employee=employeeRepository.findById(empStatusRequest.createuserid()).orElse(null);
        if (employee==null){
            return Result.failure(new Throwable("Create User ID is Not Found"));
        }
        EmpStatus empStatus1=empStatusRepository.save(empStatus);
        return Result.success(empStatus1);
    }

    @Override
    public Result<EmpStatus> edit(EmpStatusRequest empStatusRequest) {
        EmpStatus empStatus=empStatusRepository.findById(empStatusRequest.id()).orElse(null);
        if (empStatus !=null){
            empStatus.setEmpstatus(empStatusRequest.empstatus());
            empStatus.setEditdate(LocalDate.now());
            EmpStatus empStatus1=empStatusRepository.findById(empStatusRequest.edituserid()).orElse(null);
            if (empStatus==null){
                return Result.failure(new Throwable("Edit User ID is not Found"));
            }
            EmpStatus empStatus2=empStatusRepository.save(empStatus);
            return Result.success(empStatus1);
        }else {
            return null;
        }
    }

    @Override
    public List<EmpStatus> getall() {
        List<EmpStatus> empStatusList=empStatusRepository.findAll();
        return empStatusList;
    }

    @Override
    public boolean delete(int id) {
        Map<String, Object> stringObjectMap=new HashMap<>();
        EmpStatus empStatus=empStatusRepository.findById(id).orElse(null);
        if (empStatus !=null){
            if (empStatusRepository.findByIdAndIsdeleted(id,true)==null){
                empStatus.setIsdeleted(true);
                empStatus.setEditdate(LocalDate.now());
                empStatusRepository.save(empStatus);
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }

    }
}
