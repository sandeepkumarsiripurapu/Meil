package com.grapplesoft.meil_backend.controllers;

import com.grapplesoft.meil_backend.builders.ApiResponseBuilder;
import com.grapplesoft.meil_backend.models.Result;
import com.grapplesoft.meil_backend.models.entities.Address;
import com.grapplesoft.meil_backend.models.request.AddressRequest;
import com.grapplesoft.meil_backend.services.addressService.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;
import org.springframework.boot.autoconfigure.session.RedisSessionProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Address")
public class AddressController {


    @Autowired
    public AddressService addressService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody AddressRequest addressRequest){
        Result result=addressService.insert(addressRequest);
        if (result.isSuccess()){
            return  ResponseEntity.ok(ApiResponseBuilder.success(result.value(),"Address Added Sucessfully"));
        }else {
            return ResponseEntity.badRequest().body(ApiResponseBuilder.badRequest(result.error().getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<?> edit(@RequestBody AddressRequest addressRequest){
        if (addressService.edit(addressRequest)!=null){
            return ResponseEntity.ok(ApiResponseBuilder.success(null,"Address Updated Sucessfully"));
        }else {
            return ResponseEntity.badRequest().body(ApiResponseBuilder.badRequest("Something Went Wrong"));
        }
    }

    @GetMapping
    public ResponseEntity<?> getall(){
        return ResponseEntity.ok(addressService.getall());
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody Address address){
        if (addressService.delete(address.getId())){
            return  ResponseEntity.ok(ApiResponseBuilder.success(null,"Address Deleted Sucessfully"));
        }else {
            return  ResponseEntity.badRequest().body(ApiResponseBuilder.badRequest("Something Went Wrong"));
        }
    }

}
