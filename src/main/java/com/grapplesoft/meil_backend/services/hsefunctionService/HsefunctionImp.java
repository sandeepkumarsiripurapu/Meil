package com.grapplesoft.meil_backend.services.hsefunctionService;

import com.grapplesoft.meil_backend.models.entities.Hsefunction;
import com.grapplesoft.meil_backend.models.request.HseFunctionRequest;
import com.grapplesoft.meil_backend.repositories.EmployeeRepository;
import com.grapplesoft.meil_backend.repositories.HsefunctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HsefunctionImp implements  HsefunctionService {

    @Autowired
    public HsefunctionRepository hsefunctionRepository;

    @Autowired
    public EmployeeRepository employeeRepository;
    @Override
    public Hsefunction insert(HseFunctionRequest hsefunctionRequest) {

        Hsefunction hsefunction=new Hsefunction();

        hsefunction.setHsefunccode(hsefunctionRequest.hsefunccode());
        hsefunction.setHsefuncionName(hsefunctionRequest.hsefuncion_name());
        hsefunction.setCreatedate(LocalDate.now());
        if(hsefunctionRequest.createuserid()!=null){
            hsefunction.setCreateuserid(employeeRepository.findById(hsefunctionRequest.createuserid()).orElse(null));
        }
       return hsefunctionRepository.save(hsefunction);

    }

    @Override
    public Hsefunction edit(HseFunctionRequest hsefunctionRequest) {

        Hsefunction hsefunction = hsefunctionRepository.findById(hsefunctionRequest.hsefunccode()).orElse(null);
        if (hsefunction != null) {
            hsefunction.setHsefuncionName(hsefunctionRequest.hsefuncion_name());
            hsefunction.setEditdate(LocalDate.now());
            if (hsefunctionRequest.edituserid() != null) {
                hsefunction.setEdituserid(employeeRepository.findById
                        (hsefunctionRequest.edituserid()).orElse(null));
            }
           return hsefunctionRepository.save(hsefunction);

        } else {
         return null;
        }

    }

    @Override
    public List<Hsefunction> getall() {
       return hsefunctionRepository.findAll();

    }

    @Override
    public boolean delete(String id) {

        Hsefunction hsefunction=hsefunctionRepository.findById(id).orElse(null);
        if (hsefunction !=null){
            if (hsefunctionRepository.findByHsefunccodeAndIsdeleted(id,true)==null){
             hsefunction.setIsdeleted(true);
             hsefunction.setEditdate(LocalDate.now());
             hsefunctionRepository.save(hsefunction);
           return true;
            }else{
               return false;
            }
        }else {
           return false;
        }
    }
}
