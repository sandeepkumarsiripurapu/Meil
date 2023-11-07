package com.grapplesoft.meil_backend.services.sectorService;

import com.grapplesoft.meil_backend.models.entities.Sector;
import com.grapplesoft.meil_backend.models.request.SectorRequest;
import com.grapplesoft.meil_backend.repositories.EmployeeRepository;
import com.grapplesoft.meil_backend.repositories.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SectorServiceImpl implements  SectorService{

    // Autowiring the repositories
    @Autowired
    public SectorRepository sectorRepository;

    @Autowired
    public EmployeeRepository emprepo;

    // Method to insert a new sector
    @Override
    public Sector insert(SectorRequest sector) {
        // Creating a new Sector entity
        Sector sector1 =new Sector();
        // Setting attributes from the SectorRequest object
        sector1.setSectorcode(sector.sectorcode());
        sector1.setSectorname(sector.sectorname());
        sector1.setCreatedate(LocalDate.now());
        if(sector.createuserid()!=null) {
            // Retrieving an employee by ID from the EmployeeRepository and setting it as creator
            sector1.setCreateuserid(emprepo.findById(sector.createuserid()).orElse(null));
        }
        // Setting the head of household's employee ID for the sector
        sector1.setHohsemgrid(emprepo.findById(sector.hohsemgrid()).orElse(null));
        // Saving the sector to the database
        Sector result=sectorRepository.save(sector1);
        // Creating a response map with status, message, and data
        return result;
    }

    // Method to edit an existing sector
    @Override
    public Sector edit(SectorRequest sectorRequest) {
        // Finding the sector by its code
        Sector sector1 =sectorRepository.findById(sectorRequest.sectorcode()).orElse(null);
       if(sector1 !=null) {
           // Updating sector attributes
           sector1.setSectorname(sectorRequest.sectorname());
           sector1.setEditdate(LocalDate.now());
           if (sectorRequest.edituserid() != null) {
               // Setting the editor (employee) by ID
               sector1.setEdituserid(emprepo.findById(sectorRequest.edituserid()).orElse(null));
           }
           // Setting the head of household's employee ID for the sector
           sector1.setHohsemgrid(emprepo.findById(sectorRequest.hohsemgrid()).orElse(null));
           // Saving the changes to the sector
           return sectorRepository.save(sector1);

       }else{
           // If the sector is not found, return an error message
           return null;
       }
    }

    // Method to retrieve all sectors
     @Override
    public List<Sector> getall() {
         // Retrieving all sectors from the repository
        List<Sector> sectorList=sectorRepository.findAll();
        return sectorList;
    }

    // Method to delete a sector by its ID
    @Override
    public boolean delete(String id) {
        Map<String,Object> result=new HashMap<>();
        // Check if the sector exists
        Sector sector1 =sectorRepository.findById(id).orElse(null);
        if(sector1!=null){
            if(sectorRepository.findBySectorcodeAndIsdeleted(id,true)==null) {
                sector1.setIsdeleted(true);
                sector1.setEditdate(LocalDate.now());
                sectorRepository.save(sector1);
                return true;
            }else{
             return false;
            }
        }else {
            // If the sector is not found, return an error message
           return false;
        }

    }


}
