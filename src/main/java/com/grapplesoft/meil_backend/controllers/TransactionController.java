package com.grapplesoft.meil_backend.controllers;

import com.grapplesoft.meil_backend.builders.ApiResponseBuilder;
import com.grapplesoft.meil_backend.builders.TransactionsBuilder;
import com.grapplesoft.meil_backend.models.Result;
import com.grapplesoft.meil_backend.models.TransactionMapped;
import com.grapplesoft.meil_backend.models.entities.Transaction;
import com.grapplesoft.meil_backend.models.request.transactions.*;
import com.grapplesoft.meil_backend.models.response.ApiResponse;
import com.grapplesoft.meil_backend.services.transactionService.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController extends BaseController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(@Qualifier("transactionServiceImpl") TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @PostMapping(value = "/t101", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ApiResponse<String>> allocateProject(
            @RequestBody AllotProjectSiteRequestDto request
    ) {
        Result<Transaction> result = this.transactionService.allotProjectsite(request);

        if (result.isSuccess()) {
            return ResponseEntity.ok(ApiResponseBuilder.success(null, "T101 successfully executed"));
        } else {
            return ResponseEntity.badRequest().body(ApiResponseBuilder.badRequest(result.error().getMessage()));
        }
    }

    @PutMapping(value = "/t102", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ApiResponse<String>> deallotProject(
            @RequestBody DeallotProjectSiteRequest request
    ) {
        Result<Transaction> result = this.transactionService.deallotProjectSite(request);

        if (result.isSuccess()) {
            return ResponseEntity.ok(ApiResponseBuilder.success(null, "T102 successfully executed"));
        } else {
            return ResponseEntity.badRequest().body(ApiResponseBuilder.badRequest(result.error().getMessage()));
        }
    }

    @PostMapping(value = "/t103", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ApiResponse<String>> changedepartment(@RequestBody ChangeDepartment cdept) {
        Result<Transaction> result = transactionService.changedepartment(cdept);
        if (result.isSuccess()) {
            return ResponseEntity.ok(ApiResponseBuilder.success(null, "Employee department changed."));
        } else {
            return ResponseEntity.badRequest().body(ApiResponseBuilder.badRequest(result.error().getMessage()));
        }
    }

    @PostMapping(value = "/t104", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ApiResponse<String>> employeetransaction(@RequestBody EmployeeTransfer empt) {
        Result<Transaction> result = transactionService.employeetransfer(empt);
        if (result.isSuccess()) {
            return ResponseEntity.ok(ApiResponseBuilder.success(null, "Employee Transfered sucessfully"));
        } else {
            return ResponseEntity.badRequest().body(ApiResponseBuilder.badRequest(result.error().getMessage()));
        }
    }

    @PostMapping(value = "/t115",consumes = "application/json", produces = "application/json")
    public ResponseEntity<ApiResponse<String>> employeedeputation(@RequestBody EmployeeTransfer empt) {
        Result<Transaction> result = transactionService.employeetempdepu(empt);
        if (result.isSuccess()) {
            return ResponseEntity.ok(ApiResponseBuilder.success(null, "Employee Temporary Deputation sucessfully"));
        } else {
            return ResponseEntity.badRequest().body(ApiResponseBuilder.badRequest(result.error().getMessage()));
        }
    }
    @PostMapping(value = "/t119", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ApiResponse<String>> transferdepuation(@RequestBody EmployeeTransfer empt) {
        Result<Transaction> result = transactionService.rejointempdepu(empt);
        if (result.isSuccess()) {
            return ResponseEntity.ok(ApiResponseBuilder.success(null, "Rejoining Temporary Deputation sucessfully"));
        } else {
            return ResponseEntity.badRequest().body(ApiResponseBuilder.badRequest(result.error().getMessage()));
        }
    }


    @PostMapping(value = "/t107", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ApiResponse<String>> EmployeeJoiningatSite(@RequestBody Employeejoinsite empt) {
        Result<Transaction> result = transactionService.T107andT110(empt,"T107");
        if (result.isSuccess()) {
            return ResponseEntity.ok(ApiResponseBuilder.success(null, "Employee Joining at Site sucessfully"));
        } else {
            return ResponseEntity.badRequest().body(ApiResponseBuilder.badRequest(result.error().getMessage()));
        }
    }

    @PostMapping(value = "/t110", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ApiResponse<String>> ChangeofProjectManager(@RequestBody Employeejoinsite empt) {
        Result<Transaction> result = transactionService.T107andT110(empt,"T110");
        if (result.isSuccess()) {
            return ResponseEntity.ok(ApiResponseBuilder.success(null, "Change of Project Manager sucessfully"));
        } else {
            return ResponseEntity.badRequest().body(ApiResponseBuilder.badRequest(result.error().getMessage()));
        }
    }

    @PostMapping(value = "/t111", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ApiResponse<String>> ChangeofProjectStatus(@RequestBody T111 empt) {
        Result<Transaction> result = transactionService.T111(empt);
        if (result.isSuccess()) {
            return ResponseEntity.ok(ApiResponseBuilder.success(null, "Change of Project Status sucessfully"));
        } else {
            return ResponseEntity.badRequest().body(ApiResponseBuilder.badRequest(result.error().getMessage()));
        }
    }

    @PostMapping(value = "/t109", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ApiResponse<String>> DataChangeRequest(@RequestBody T109 empt) {
        Result<Transaction> result = transactionService.T109(empt);
        if (result.isSuccess()) {
            return ResponseEntity.ok(ApiResponseBuilder.success(null, "Data Change Request Status sucessfully"));
        } else {
            return ResponseEntity.badRequest().body(ApiResponseBuilder.badRequest(result.error().getMessage()));
        }
    }

    @PostMapping(value = "/t116", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ApiResponse<String>> EmployeeTerminationAbsconded(@RequestBody T116andT117andT1118 empt) {
        Result<Transaction> result = transactionService.T116andT117andT118(empt,"T116");
        if (result.isSuccess()) {
            return ResponseEntity.ok(ApiResponseBuilder.success(null, "Employee Termination Absconded Status sucessfully"));
        } else {
            return ResponseEntity.badRequest().body(ApiResponseBuilder.badRequest(result.error().getMessage()));
        }
    }
    @PostMapping(value = "/t117", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ApiResponse<String>> EmployeeDisciplinaryTermination(@RequestBody T116andT117andT1118 empt) {
        Result<Transaction> result = transactionService.T116andT117andT118(empt,"T117");
        if (result.isSuccess()) {
            return ResponseEntity.ok(ApiResponseBuilder.success(null, "Employee Disciplinary Termination Status sucessfully"));
        } else {
            return ResponseEntity.badRequest().body(ApiResponseBuilder.badRequest(result.error().getMessage()));
        }
    }
    @PostMapping(value = "/t118", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ApiResponse<String>> EmployeeResignation(@RequestBody T116andT117andT1118 empt) {
        Result<Transaction> result = transactionService.T116andT117andT118(empt,"T118");
        if (result.isSuccess()) {
            return ResponseEntity.ok(ApiResponseBuilder.success(null, "Employee Resignation sucessfully"));
        } else {
            return ResponseEntity.badRequest().body(ApiResponseBuilder.badRequest(result.error().getMessage()));
        }
    }


    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<ApiResponse<List<TransactionMapped>>> getAllTransactions() {
        List<TransactionMapped> transactionsMapped = new ArrayList<>();
        List<Transaction> transactions = this.transactionService.getAllTransactions();

        for (Transaction transaction : transactions) {
            transactionsMapped.add(TransactionsBuilder.buildMapped(transaction));
        }

        return ResponseEntity.ok(
                ApiResponseBuilder.success(transactionsMapped, "Transactions fetched successfully")
        );
    }

}
