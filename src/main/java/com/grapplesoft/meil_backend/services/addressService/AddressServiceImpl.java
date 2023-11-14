package com.grapplesoft.meil_backend.services.addressService;

import com.grapplesoft.meil_backend.builders.AddressBuilder;
import com.grapplesoft.meil_backend.models.Result;
import com.grapplesoft.meil_backend.models.entities.Address;
import com.grapplesoft.meil_backend.models.entities.Employee;
import com.grapplesoft.meil_backend.models.entities.State;
import com.grapplesoft.meil_backend.models.request.AddressRequest;
import com.grapplesoft.meil_backend.models.response.AddressResponse;
import com.grapplesoft.meil_backend.repositories.AddressRepository;
import com.grapplesoft.meil_backend.repositories.EmployeeRepository;
import com.grapplesoft.meil_backend.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AddressServiceImpl  implements AddressService{

    @Autowired
    public  AddressRepository addressRepository;

    @Autowired
    public EmployeeRepository employeeRepository;

    @Autowired
    public StateRepository stateRepository;

    @Override
    public Result<Address> insert(AddressRequest addressRequest) {

        Address address=new Address();
        address.setId(addressRequest.addressid());
        address.setAddressline1(addressRequest.addressline1());
        address.setAddressline2(addressRequest.addressline2());
        address.setCountryname(addressRequest.countryname());
        address.setDistrict(addressRequest.district());
        address.setPincode(addressRequest.pincode());
        address.setPlace(addressRequest.place());
        address.setCreatedate(LocalDate.now());
        address.setStatename(addressRequest.statename());

        State state=stateRepository.findById(addressRequest.statecode()).orElse(null);
        if (state==null){
            return Result.failure(new Throwable("No State Code Found"));
        }
        Employee employee=employeeRepository.findById(addressRequest.createuserid()).orElse(null);
        if (employee==null){
            return Result.failure(new Throwable("Created Employee is Not Found"));
        }

        Address address1=addressRepository.save(address);
        return Result.success(address1);

    }

    @Override
    public Result<Address> edit(AddressRequest addressRequest) {
        Address address = addressRepository.findById(addressRequest.addressid()).orElse(null);
        if (address != null) {
            address.setAddressline1(addressRequest.addressline1());
            address.setAddressline2(addressRequest.addressline2());
            address.setCountryname(addressRequest.countryname());
            address.setDistrict(addressRequest.district());
            address.setPincode(addressRequest.pincode());
            address.setPlace(addressRequest.place());
            address.setEditdate(LocalDate.now());
            address.setStatename(addressRequest.statename());
            State state = stateRepository.findById(addressRequest.statecode()).orElse(null);
            if (state == null) {
                return Result.failure(new Throwable("No State Code Found"));
            }
            Employee employee = employeeRepository.findById(addressRequest.edituserid()).orElse(null);
            if (employee == null) {
                return Result.failure(new Throwable("Created Employee is Not Found"));
            }
//            return addressRepository.save(address);
            return null;

        } else {
            return null;
        }
    }


    @Override
    public List<AddressResponse> getall() {
        List<Address> addressList=addressRepository.findAll();
        List<AddressResponse> ars=new ArrayList<>();
        for(Address ads:addressList){
            ars.add(AddressBuilder.buildaddressResponse(ads));
        }
        return ars;
    }

    @Override
    public boolean delete(int id) {
        Map<String,Object> stringObjectMap=new HashMap<>();
        Address address=addressRepository.findById(id).orElse(null);
        if (address !=null){
            if (addressRepository.findByIdAndIsdeleted(id,true)==null){
                address.setIsdeleted((true));
                address.setEditdate(LocalDate.now());
                addressRepository.save(address);
                return true;
            }else {
                return  false;
            }
        }else {
            return false;
        }
    }
}










