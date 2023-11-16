package com.grapplesoft.meil_backend.services.projectsiteService;

import com.grapplesoft.meil_backend.builders.ProjectsiteBuilder;
import com.grapplesoft.meil_backend.models.Result;
import com.grapplesoft.meil_backend.models.entities.Address;
import com.grapplesoft.meil_backend.models.entities.Employee;
import com.grapplesoft.meil_backend.models.entities.Project;
import com.grapplesoft.meil_backend.models.entities.Projectsite;
import com.grapplesoft.meil_backend.models.request.ProjectsiteRequest;
import com.grapplesoft.meil_backend.models.response.ProjectsiteResponse;
import com.grapplesoft.meil_backend.repositories.AddressRepository;
import com.grapplesoft.meil_backend.repositories.EmployeeRepository;
import com.grapplesoft.meil_backend.repositories.ProjectRepository;
import com.grapplesoft.meil_backend.repositories.ProjectSiteRepository;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.management.ObjectName;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProjectsiteServiceImpl implements ProjectsiteService{


    @Autowired
    public ProjectRepository projectRepository;

    @Autowired
    public EmployeeRepository employeeRepository;

    @Autowired
    public AddressRepository addressRepository;

    @Autowired
    public ProjectSiteRepository projectSiteRepository;
    @Override
    public Result<Projectsite> insert(ProjectsiteRequest projectsiteRequest) {
        Projectsite projectsite=new Projectsite();

        projectsite.setId(projectsiteRequest.id());
        projectsite.setSitename(projectsiteRequest.sitename());
        projectsite.setLatitude(projectsiteRequest.latitude());
        projectsite.setLongitude(projectsiteRequest.longitude());
        projectsite.setCourierpmobile(projectsiteRequest.courierpmobile());
        projectsite.setCreatedate(LocalDate.now());


        Project project=projectRepository.findById(projectsiteRequest.projid()).orElse(null);
        if (project==null){
            return Result.failure(new Throwable("Projid is not Found"));
        }
        projectsite.setProjid(project);

        Employee employee=employeeRepository.findById(projectsiteRequest.sitemanagerid()).orElse(null);
        if (employee==null){
            return  Result.failure(new Throwable("Site  Manager ID is not Found"));
        }
        projectsite.setSitemanagerid(employee);

        Employee employee1=employeeRepository.findById(projectsiteRequest.projcoordid()).orElse(null);
        if (employee1==null){
            return  Result.failure(new Throwable("projcoordid is not Found"));
        }
        projectsite.setProjcoordid(employee1);

        Employee employee2=employeeRepository.findById(projectsiteRequest.courierpcode()).orElse(null);
        if (employee2==null){
            return Result.failure(new Throwable("courierpcode is not Found"));
        }
        projectsite.setCourierpcode(employee2);




        Address address=addressRepository.findById(projectsiteRequest.addressid()).orElse(null);
        if (address==null){
            return  Result.failure(new Throwable("Address Id is not Found"));
        }
        projectsite.setAddressid(address);

        if (projectsiteRequest.createuserid()!=null) {
            Employee employee4 = employeeRepository.findById(projectsiteRequest.createuserid()).orElse(null);
            if (employee4 == null) {
                return Result.failure(new Throwable("UserID is not Found"));
            }
            projectsite.setCreateuserid(employee4);
        }
        Projectsite projectsite1=projectSiteRepository.save(projectsite);
        return Result.success(projectsite1);
    }

    @Override
    public Result<Projectsite> edit(ProjectsiteRequest projectsiteRequest) {
        Projectsite projectsite = projectSiteRepository.findById(projectsiteRequest.projid()).orElse(null);
        if (projectsite != null) {
            projectsite.setSitename(projectsiteRequest.sitename());
            projectsite.setLatitude(projectsiteRequest.latitude());
            projectsite.setLongitude(projectsiteRequest.longitude());
            projectsite.setCourierpmobile(projectsiteRequest.courierpmobile());
            projectsite.setEditdate(LocalDate.now());



            Project project = projectRepository.findById(projectsiteRequest.projid()).orElse(null);
            if (project == null) {
                return Result.failure(new Throwable("Projid is not Found"));
            }
            projectsite.setProjid(project);

            Employee employee = employeeRepository.findById(projectsiteRequest.sitemanagerid()).orElse(null);
            if (employee == null) {
                return Result.failure(new Throwable("Site  Manager ID is not Found"));
            }
            projectsite.setSitemanagerid(employee);

            Employee employee1 = employeeRepository.findById(projectsiteRequest.projcoordid()).orElse(null);
            if (employee1 == null) {
                return Result.failure(new Throwable("projcoordid is not Found"));
            }
            projectsite.setProjcoordid(employee1);

            Employee employee2 = employeeRepository.findById(projectsiteRequest.courierpcode()).orElse(null);
            if (employee2 == null) {
                return Result.failure(new Throwable("courierpcode is not Found"));
            }
            projectsite.setCourierpcode(employee2);


            Address address = addressRepository.findById(projectsiteRequest.addressid()).orElse(null);
            if (address == null) {
                return Result.failure(new Throwable("Address Id is not Found"));
            }
            projectsite.setAddressid(address);

            if (projectsiteRequest.edituserid()!=null) {
                Employee employee4 = employeeRepository.findById(projectsiteRequest.edituserid()).orElse(null);
                if (employee4 == null) {
                    return Result.failure(new Throwable("Edit UserID is not Found"));
                }
                projectsite.setEdituserid(employee4);
            }

           Projectsite projectsite1=projectSiteRepository.save(projectsite);
            return Result.success(projectsite1);
        }  else {
            return null;
        }
    }

    @Override
    public List<ProjectsiteResponse> getall() {
        List<Projectsite> projectsiteList=projectSiteRepository.findAll();
        List<ProjectsiteResponse> projectsiteResponses=new ArrayList<>();
        for (Projectsite projectsite:projectsiteList){
            projectsiteResponses.add(ProjectsiteBuilder.generateresp(projectsite));
        }
        return projectsiteResponses;
    }

    @Override
    public boolean delete(Long id) {
        Map<String, Object> stringObjectMap=new HashMap<>();
        Projectsite projectsite=projectSiteRepository.findById(id).orElse(null);
        if (projectsite !=null){
            if (projectSiteRepository.findByIdAndIsdeleted(id,true)==null){
                projectsite.setIsdeleted(true);
                projectsite.setEditdate(LocalDate.now());
                projectSiteRepository.save(projectsite);
                return  true;
            }else {
                return  false;
            }
        }else {
            return false;
        }
    }
}
