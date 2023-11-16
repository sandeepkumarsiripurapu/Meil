package com.grapplesoft.meil_backend.services.projectService;

import com.grapplesoft.meil_backend.builders.ProjectBuilder;
import com.grapplesoft.meil_backend.models.Result;
import com.grapplesoft.meil_backend.models.entities.*;
import com.grapplesoft.meil_backend.models.request.ProjectRequest;
import com.grapplesoft.meil_backend.models.response.ProjectResponse;
import com.grapplesoft.meil_backend.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.management.ManagementPermission;
import java.sql.ClientInfoStatus;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProjectServiceImpl implements  ProjectService{

    @Autowired
    public ProjectRepository projectRepository;

    @Autowired
    public StatusRepository statusRepository;

    @Autowired
    public SectorRepository sectorRepository;

    @Autowired
    public EmployeeRepository employeeRepository;

    @Autowired
    public StateRepository stateRepository;

    @Override
    public Result<Project> insert(ProjectRequest projectRequest) {

        Project project = new Project();
        project.setId(projectRequest.projid());
        project.setProjname(projectRequest.projname());

        if (projectRequest.dupprojid() != null) {
            Project project1 = projectRepository.findById(projectRequest.dupprojid()).orElse(null);

        if (project1 == null) {
            return Result.failure(new Throwable("dupprojid Not Found"));
        }
            project.setDupprojid(project1);
        }

        Status status=statusRepository.findById(projectRequest.status()).orElse(null);
        if (status==null){
            return Result.failure(new Throwable("status Not Found"));
        }
         project.setStatus(status);

        Sector sector=sectorRepository.findById(projectRequest.sectorcode()).orElse(null);
        if (sector==null){
            return Result.failure(new Throwable("sectorcode Not Found"));
        }
         project.setSectorcode(sector);

        Employee employee=employeeRepository.findById(projectRequest.projhodid()).orElse(null);
        if (employee==null){
            return Result.failure(new Throwable("projhodid Not Found"));
        }
        project.setProjhodid(employee);

        Employee employee1=employeeRepository.findById(projectRequest.pmrepauthid()).orElse(null);
        if (employee1==null){
            return Result.failure(new Throwable("pmrepauthid Not Found"));
        }
        project.setPmrepauthid(employee1);

        Employee employee2=employeeRepository.findById(projectRequest.projcoordid()).orElse(null);
        if (employee2==null){
            return Result.failure(new Throwable("projcoordid Not Found"));
        }
        project.setProjcoordid(employee2);

        Employee employee3=employeeRepository.findById(projectRequest.hsecoordid()).orElse(null);
        if (employee3==null){
            return Result.failure(new Throwable("hsecoordid Not Found"));
        }
        project.setHsecoordid(employee3);

        Employee employee4=employeeRepository.findById(projectRequest.hsemgrid()).orElse(null);
        if (employee4==null){
            return Result.failure(new Throwable("hsemgrid Not Found"));
        }
        project.setHsemgrid(employee4);

        State state=stateRepository.findById(projectRequest.statecode()).orElse(null);
        if (state==null){
            return Result.failure(new Throwable("statecode Not Found"));
        }
        project.setStatecode(state);


        project.setProjvalue(projectRequest.projvalue());
        project.setIsoandm(projectRequest.isoandm());
        project.setRemarks(projectRequest.remarks());
        project.setCreatedate(LocalDate.now());

if(projectRequest.createuserid()!=null) {
    Employee employee5 = employeeRepository.findById(projectRequest.createuserid()).orElse(null);
    if (employee5 == null) {
        return Result.failure(new Throwable("createuserid Not Found"));
    }
    project.setCreateuserid(employee5);
}
        Project project2=projectRepository.save(project);
        return Result.success(project2);

    }

    @Override
    public Result<Project> edit(ProjectRequest projectRequest) {
        Project project = projectRepository.findById(projectRequest.projid()).orElse(null);
        if (project != null) {
            project.setProjname(projectRequest.projname());
            if (projectRequest.dupprojid()!=null){
                Project project1 = projectRepository.findById(projectRequest.dupprojid()).orElse(null);
                if (project1 == null) {
                    return Result.failure(new Throwable("dupprojid Not Found"));
                }
                project.setDupprojid(project1);
            }




            Status status = statusRepository.findById(projectRequest.status()).orElse(null);
            if (status == null) {
                return Result.failure(new Throwable("status Not Found"));
            }
            project.setStatus(status);

            Sector sector = sectorRepository.findById(projectRequest.sectorcode()).orElse(null);
            if (sector == null) {
                return Result.failure(new Throwable("sectorcode Not Found"));
            }
            project.setSectorcode(sector);

            Employee employee = employeeRepository.findById(projectRequest.projhodid()).orElse(null);
            if (employee == null) {
                return Result.failure(new Throwable("projhodid Not Found"));
            }
            project.setProjhodid(employee);

            Employee employee1 = employeeRepository.findById(projectRequest.pmrepauthid()).orElse(null);
            if (employee1 == null) {
                return Result.failure(new Throwable("pmrepauthid Not Found"));
            }
            project.setPmrepauthid(employee1);


            Employee employee2 = employeeRepository.findById(projectRequest.projcoordid()).orElse(null);
            if (employee2 == null) {
                return Result.failure(new Throwable("projcoordid Not Found"));
            }
            project.setProjcoordid(employee2);

            Employee employee3 = employeeRepository.findById(projectRequest.hsecoordid()).orElse(null);
            if (employee3 == null) {
                return Result.failure(new Throwable("hsecoordid Not Found"));
            }
            project.setHsecoordid(employee3);


            Employee employee4 = employeeRepository.findById(projectRequest.hsemgrid()).orElse(null);
            if (employee4 == null) {
                return Result.failure(new Throwable("hsemgrid Not Found"));
            }
            project.setHsemgrid(employee4);

            State state = stateRepository.findById(projectRequest.statecode()).orElse(null);
            if (state == null) {
                return Result.failure(new Throwable("statecode Not Found"));
            }
            project.setStatecode(state);

            project.setProjvalue(projectRequest.projvalue());
            project.setIsoandm(projectRequest.isoandm());
            project.setRemarks(projectRequest.remarks());
            project.setEditdate(LocalDate.now());

            if (projectRequest.edituserid() != null) {
                Employee employee5 = employeeRepository.findById(projectRequest.edituserid()).orElse(null);
                if (employee5 == null) {
                    return Result.failure(new Throwable("edituserid Not Found"));
                }
                project.setEdituserid(employee5);
            }

            Project project2 = projectRepository.save(project);
            return Result.success(project2);
        }else{
            return null;
        }

    }

    @Override
    public List<ProjectResponse> getall() {
        List<Project> projectList=projectRepository.findAll();
        List<ProjectResponse> projectResponses=new ArrayList<>();
        for (Project project:projectList){
            projectResponses.add(ProjectBuilder.projectResponse(project));
        }
        return projectResponses;
    }

    @Override
    public boolean delete(Long id) {
        Map<String,Object> stringObjectMap=new HashMap<>();
        Project project=projectRepository.findById(id).orElse(null);
        if (project !=null) {
            if (projectRepository.findByIdAndIsdeleted(id, true) == null) {
                project.setIsdeleted(true);
                project.setEditdate(LocalDate.now());
                projectRepository.save(project);
                return true;
            } else {
                return false;
            }
        }else {
            return  false;
        }
    }
}

