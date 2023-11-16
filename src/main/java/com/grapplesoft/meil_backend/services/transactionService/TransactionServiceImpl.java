package com.grapplesoft.meil_backend.services.transactionService;

import com.grapplesoft.meil_backend.builders.TransactionsBuilder;
import com.grapplesoft.meil_backend.enums.ActionTypeEnum;
import com.grapplesoft.meil_backend.models.Result;
import com.grapplesoft.meil_backend.models.entities.*;
import com.grapplesoft.meil_backend.models.request.transactions.*;
import com.grapplesoft.meil_backend.repositories.*;
import com.grapplesoft.meil_backend.services.employeeService.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.boot.model.internal.DelayedParameterizedTypeBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private EmployeeRepository employeerepository;

    @Autowired
    private DepartmentRepository departmentrepository;

    @Autowired
    private HsefunctionRepository hserepo;

    @Autowired
    private ProjectSiteRepository projsiterepo;

    @Autowired
    private StatusRepository stsrepo;
    @Autowired
    public TransactionServiceImpl(@Qualifier("transactionRepository") TransactionRepository transactionRepository,
                                  @Qualifier("projectSiteRepository") ProjectSiteRepository projectSiteRepository,
                                  @Qualifier("projectRepository") ProjectRepository projectRepository,
                                  @Qualifier("employeeServiceImpl") EmployeeService employeeService,
                                  @Qualifier("addressRepository") AddressRepository addressRepository,
                                  @Qualifier("actionTypeRepository") ActionTypeRepository actionTypeRepository) {
        this.transactionRepository = transactionRepository;
        this.projectSiteRepository = projectSiteRepository;
        this.projectRepository = projectRepository;
        this.employeeService = employeeService;
        this.addressRepository = addressRepository;

        actionTypes.addAll(actionTypeRepository.findAll());
    }


    /**
     * Allot Project Site - creates a new transaction for a project site with Actiontype T101
     *
     * @param request {@link AllotProjectSiteRequestDto} - request body
     * @return {@link Transaction}
     * @Author Vishwesh Shukla
     * @see AllotProjectSiteRequestDto
     */
    @Override
    public Result<Transaction> allotProjectsite(AllotProjectSiteRequestDto request) {
        /*
              - here the allocated project is to be linked to a projectsite fetched from the database
              - this is not implemented yet as project sites are to be seeded via Master Management functions
              - the master managment is not implemented yet
         */

        // if the transactions list is not empty for search by project ID and Employee ID, return null
        if (!transactionRepository.findByProjectIdAndActionId(request.projectId(), 1).isEmpty()) {
            return Result.failure(new Exception("Project already allotted to an employee"));
        }

        Project project = this.projectRepository.findById(request.projectId()).orElse(null);
        Employee siteManager = this.employeeService.getEmployeeById(request.siteManagerId());
        Employee projCoordinator = this.employeeService.getEmployeeById(request.projectCoordinatorId());
        Employee courierP = this.employeeService.getEmployeeById(request.courierPcode());
        Address address = this.addressRepository.findById(request.addressId()).orElse(null);

        // field validations
        if (projCoordinator != null) {

            // get action type for current transaction
            Actiontype action = this.getActionType(ActionTypeEnum.T101);

            // add transaction
            return Result.success(this.addTransaction(Transaction.builder()
                    .actiontypeid(action)
                    .hsecoordid(projCoordinator)
                    .fromprojectid(project)
                    .actiondate(LocalDate.now())
                    .createdate(LocalDate.now())
                    .remarks(request.remarks())
                    .build()));
        } else {
            return Result.failure(new Exception("Project Coordinator not found"));
        }
    }

    /**
     * Deallot a project site - creates a new transaction for existing project site with Actiontype T102.
     * If transactions exist for same project site, project and employee, and its action type is T102 then return null.
     *
     * @param request {@link DeallotProjectSiteRequest}
     * @return {@link Transaction}
     * @Author Vishwesh Shukla
     * @see DeallotProjectSiteRequest
     */
    @Override
    public Result<Transaction> deallotProjectSite(DeallotProjectSiteRequest request) {
        // fetch all existing transactions by project id and action Type 2 to check if the project has already been dealloted
        var existingTransactionsT101 = this.transactionRepository.findByProjectIdAndActionId(request.projectId(), 1);
        var existingTransactionsT102 = this.transactionRepository.findByProjectIdAndActionId(request.projectId(), 2);

        if (existingTransactionsT101.isEmpty()) {
            return Result.failure(new Exception("No matching project found to deallot."));
        } else if (!existingTransactionsT102.isEmpty()) {
            return Result.failure(new Exception("Project has already been dealloted."));
        } else {
            // if the project site exists, create a new transaction with action type t102. ELSE return null.
            Project project = this.projectRepository.findById(request.projectId()).orElse(null);
            Employee projCoordinator = this.employeeService.getEmployeeById(request.projectCoordinatorId());

            // build transaction and return
            Actiontype action = this.getActionType(ActionTypeEnum.T102);

            return Result.success(this.addTransaction(Transaction.builder()
                    .actiontypeid(action)
                    .hsecoordid(projCoordinator)
                    .fromprojectid(project)
                    .actiondate(LocalDate.now())
                    .createdate(LocalDate.now())
                    .remarks(request.remarks())
                    .build()));
        }

//        var latestTransaction = existingTransactions.stream().max(Comparator.comparing(Transaction::getCreatedate));
//        && Objects.equals(latestTransaction.get().getActiontypeid().getAction(), ActionTypeEnum.T102.getValue())
    }

    @Override
    public List<Transaction> getAllTransactions() {

        return this.transactionRepository.findAll();
    }

    @Override
    public void deleteTransaction(Long id) {

    }

    private Transaction addTransaction(Transaction transaction) {
        if (transaction != null) {
            return this.transactionRepository.save(transaction);
        } else return null;
    }

    private Actiontype getActionType(ActionTypeEnum type) {
        return actionTypes.stream().filter(it -> it.getAction().equals(type.getValue())).findAny().orElse(null);
//        return actionTypes.stream().takeWhile(it -> it.getAction().equals(type.getValue())).findFirst().orElse(null);
    }

    @Override
    /**
     * This method is used to change the department of an employee within a project.
     *
     * @param cdept A ChangeDepartment object containing information about the department change request.
     * @return ResponseEntity with a response containing status, message, and optional data.
     */
    public Result<Transaction> changedepartment(ChangeDepartment cdept) {
        // Find transactions related to the employee and project
        List<Transaction> trs = transactionRepository.findByProjectIdAndEmployeeId(cdept.fromProjectId(), cdept.employeeId());

        // Initialize a variable to track if a specific action type (ID 2) was found in the transactions
        boolean result = false;

        // Check each transaction for the desired action type
        for (Transaction tr : trs) {
            result = tr.getActiontypeid().getId() == 2;
        }

        if (result) {
            // If the desired action type was found, set an error response
            return Result.failure(new Exception("This Employee's project is already de-alloted."));
        } else {
            // If the action type is not found, proceed with the department change
            Employee emp = employeeService.getEmployeeById(cdept.employeeId());

            if (emp != null) {
                // Find the new department by its ID
                Department deptt = departmentrepository.findById(cdept.deptIdTo()).orElse(null);

                // Update the employee's department and edit date
                emp.setDeptCode(deptt);
                emp.setEditDate(LocalDate.now());

                // Save the updated employee information
                employeerepository.save(emp);

                // Find the project site by its ID
                Projectsite projectSite = this.projectSiteRepository.findById(cdept.fromProjectId()).orElse(null);

                if (projectSite != null) {
                    // Build a transaction based on ActionTypeEnum.T103 and other information
                    Actiontype action = this.getActionType(ActionTypeEnum.T103);
                    Transaction tresult = this.addTransaction(TransactionsBuilder.forT103(action, projectSite, emp, departmentrepository.findById(cdept.deptIdFrom()).orElse(null), departmentrepository.findById(cdept.deptIdTo()).orElse(null)));

                    if (tresult == null) {
                        // Set an error response if the transaction is not found
                        return Result.failure(new Exception("Action type not found."));
                    } else {
                        // Set a success response with the transaction data
                        return Result.success(tresult);
                    }
                } else {
                    // Set an error response if the project site is not found
                    return Result.failure(new Exception("Project-site not found"));
                }
            } else {
                // Set an error response if the employee is not found

                return Result.failure(new Exception("Employee not found"));
            }
        }

    }


    @Override
    /**
     * This method is used to transfer an employee from one project to another.
     *
     * @param empt An EmployeeTransfer object containing information about the employee transfer request.
     * @return ResponseEntity with a response containing status, message, and optional data.
     */
    public Result<Transaction> employeetransfer(EmployeeTransfer empt) {
        // Find transactions related to the employee and project
        List<Transaction> trs = transactionRepository.findByProjectIdAndEmployeeId(empt.fromprojectid(), empt.employeeid());

        // Initialize a variable to track if a specific action type (ID 2) was found in the transactions
        boolean result = false;

        // Check each transaction for the desired action type
        for (Transaction tr : trs) {
            result = tr.getActiontypeid().getId() == 2;
        }

        if (result) {
            // If the desired action type was found, set an error response
            return Result.failure(new Exception("This Employee's project is already de-allotted"));
        } else {
            // If the action type is not found, proceed with the employee transfer
            Employee emp = employeeService.getEmployeeById(empt.employeeid());

            if (emp != null) {

                // Find the source and target project sites by their IDs
                Project fromproject = this.projectRepository.findById(empt.fromprojectid()).orElse(null);
                Project toproject = this.projectRepository.findById(empt.toprojectid()).orElse(null);

                // Find the action type (T104)
                Actiontype action = this.getActionType(ActionTypeEnum.T104);

                // Find Hsefunctions for the source and target functions
                Hsefunction fun1 = hserepo.findById(empt.function1()).orElse(null);
                Hsefunction fun2 = hserepo.findById(empt.function2()).orElse(null);

                if (fromproject == null) {
                    return Result.failure(new Exception("No record found in fromproject."));
                }

                if (toproject == null) {

                    return Result.failure(new Exception("No record found in  toproject."));
                }

                if (action == null) {

                    return Result.failure(new Exception("No record found in  action"));
                }

                if (fun1 == null) {

                    return Result.failure(new Exception("No record found in  hsefunction1"));
                }

                if (fun2 == null) {

                    return Result.failure(new Exception("No record found in  hsefunction1"));
                }

                emp.setHseFunctionId(fun2);
                emp.setProjCode(toproject);
                emp.setEditDate(LocalDate.now());
                employeerepository.save(emp);
                // Create a new transaction for the employee transfer
                Transaction trsanc = this.addTransaction(Transaction.builder()
                        .actiontypeid(action)
                        .function1(fun1)
                        .function2(fun2)
                        .fromprojectid(fromproject)
                        .toprojectid(toproject)
                        .createuserid(emp)
                        .employeeid(emp)
                        .date1(empt.date1())
                        .date2(empt.date2())
                        .actiondate(LocalDate.now())
                        .createdate(LocalDate.now())
                        .build());

                // Set a success response with the transaction data
                return Result.success(trsanc);
            }else{
                return Result.failure(new Exception("No Employee found"));
            }

        }

    }

    @Override
    public Result<Transaction> employeetempdepu(EmployeeTransfer empt) {
        // Find transactions related to the employee and project
        List<Transaction> trs = transactionRepository.findByProjectIdAndEmployeeId(empt.fromprojectid(), empt.employeeid());

        // Initialize a variable to track if a specific action type (ID 2) was found in the transactions
        boolean result = false;

        // Check each transaction for the desired action type
        for (Transaction tr : trs) {
            result = tr.getActiontypeid().getId() == 2;
        }

        if (result) {
            // If the desired action type was found, set an error response
            return Result.failure(new Exception("This Employee's project is already de-allotted"));
        } else {
            // If the action type is not found, proceed with the employee transfer
            Employee emp = employeeService.getEmployeeById(empt.employeeid());

            if (emp != null) {

                // Find the source and target project sites by their IDs
                Project fromproject = this.projectRepository.findById(empt.fromprojectid()).orElse(null);
                Project toproject = this.projectRepository.findById(empt.toprojectid()).orElse(null);

                // Find the action type (T104)
                Actiontype action = this.getActionType(ActionTypeEnum.T115);

                // Find Hsefunctions for the source and target functions
                Hsefunction fun1 = hserepo.findById(empt.function1()).orElse(null);
                Hsefunction fun2 = hserepo.findById(empt.function2()).orElse(null);

                if (fromproject == null) {
                    return Result.failure(new Exception("No record found in fromproject."));
                }

                if (toproject == null) {

                    return Result.failure(new Exception("No record found in  toproject."));
                }

                if (action == null) {

                    return Result.failure(new Exception("No record found in  action"));
                }

                if (fun1 == null) {

                    return Result.failure(new Exception("No record found in  hsefunction1"));
                }

                if (fun2 == null) {

                    return Result.failure(new Exception("No record found in  hsefunction1"));
                }

                emp.setHseFunctionId(fun2);
                emp.setProjCode(toproject);
                emp.setEditDate(LocalDate.now());
                employeerepository.save(emp);
                // Create a new transaction for the employee transfer
                Transaction trsanc = this.addTransaction(Transaction.builder()
                        .actiontypeid(action)
                        .function1(fun1)
                        .function2(fun2)
                        .fromprojectid(fromproject)
                        .toprojectid(toproject)
                        .createuserid(emp)
                        .employeeid(emp)
                        .date1(empt.date1())
                        .date2(empt.date2())
                        .actiondate(LocalDate.now())
                        .createdate(LocalDate.now())
                        .build());

                // Set a success response with the transaction data
                return Result.success(trsanc);
            }else{
                return Result.failure(new Exception("No Employee found"));
            }

        }
    }

    @Override
    public Result<Transaction> rejointempdepu(EmployeeTransfer empt) {
        // Find transactions related to the employee and project
        List<Transaction> trs = transactionRepository.findByProjectIdAndEmployeeId(empt.fromprojectid(), empt.employeeid());

        // Initialize a variable to track if a specific action type (ID 2) was found in the transactions
        boolean result = false;

        // Check each transaction for the desired action type
        for (Transaction tr : trs) {
            result = tr.getActiontypeid().getId() == 2;
        }

        if (result) {
            // If the desired action type was found, set an error response
            return Result.failure(new Exception("This Employee's project is already de-allotted"));
        } else {
            // If the action type is not found, proceed with the employee transfer
            Employee emp = employeeService.getEmployeeById(empt.employeeid());

            if (emp != null) {

                // Find the source and target project sites by their IDs
                Project fromproject = this.projectRepository.findById(empt.fromprojectid()).orElse(null);
                Project toproject = this.projectRepository.findById(empt.toprojectid()).orElse(null);

                // Find the action type (T104)
                Actiontype action = this.getActionType(ActionTypeEnum.T119);

                // Find Hsefunctions for the source and target functions
                Hsefunction fun1 = hserepo.findById(empt.function1()).orElse(null);
                Hsefunction fun2 = hserepo.findById(empt.function2()).orElse(null);

                if (fromproject == null) {
                    return Result.failure(new Exception("No record found in fromproject."));
                }

                if (toproject == null) {

                    return Result.failure(new Exception("No record found in  toproject."));
                }

                if (action == null) {

                    return Result.failure(new Exception("No record found in  action"));
                }

                if (fun1 == null) {

                    return Result.failure(new Exception("No record found in  hsefunction1"));
                }

                if (fun2 == null) {

                    return Result.failure(new Exception("No record found in  hsefunction1"));
                }

                emp.setHseFunctionId(fun2);
                emp.setProjCode(toproject);
                emp.setEditDate(LocalDate.now());
                employeerepository.save(emp);
                // Create a new transaction for the employee transfer
                Transaction trsanc = this.addTransaction(Transaction.builder()
                        .actiontypeid(action)
                        .function1(fun1)
                        .function2(fun2)
                        .fromprojectid(fromproject)
                        .toprojectid(toproject)
                        .createuserid(emp)
                        .employeeid(emp)
                        .date1(empt.date1())
                        .date2(empt.date2())
                        .actiondate(LocalDate.now())
                        .createdate(LocalDate.now())
                        .build());

                // Set a success response with the transaction data
                return Result.success(trsanc);
            }else{
                return Result.failure(new Exception("No Employee found"));
            }

        }
    }

    @Override
    public Result<Transaction> T107andT110(Employeejoinsite empjoin,String acttype) {
        // Find transactions related to the employee and project
        List<Transaction> trs = transactionRepository.findByProjectIdAndEmployeeId(empjoin.fromprojectid(), empjoin.employeeid());

        // Initialize a variable to track if a specific action type (ID 2) was found in the transactions
        boolean result = false;

        // Check each transaction for the desired action type
        for (Transaction tr : trs) {
            result = tr.getActiontypeid().getId() == 2;
        }

        if (result) {
            // If the desired action type was found, set an error response
            return Result.failure(new Exception("This Employee's project is already de-allotted"));
        } else {
            // If the action type is not found, proceed with the employee transfer
            Employee emp = employeeService.getEmployeeById(empjoin.employeeid());

            if (emp != null) {

                // Find the source and target project sites by their IDs
                Project fromproject = this.projectRepository.findById(empjoin.fromprojectid()).orElse(null);
                Projectsite projsite = this.projsiterepo.findById(empjoin.projectsiteid()).orElse(null);

                Actiontype action =new Actiontype();
                if(Objects.equals(acttype, "T107")) {
                     action = this.getActionType(ActionTypeEnum.T107);
                }
                if(Objects.equals(acttype, "T110")) {
                     action = this.getActionType(ActionTypeEnum.T110);
                }

                if (fromproject == null) {
                    return Result.failure(new Exception("No record found in fromproject."));
                }

                if (projsite == null) {

                    return Result.failure(new Exception("No record found in  toproject."));
                }

                if (action == null) {

                    return Result.failure(new Exception("No record found in  action"));
                }

                emp.setEditDate(LocalDate.now());
                employeerepository.save(emp);
                // Create a new transaction for the employee transfer
                Transaction trsanc = this.addTransaction(Transaction.builder()
                        .actiontypeid(action)
                        .fromprojectid(fromproject)
                        .employeeid(employeerepository.findById(empjoin.employeeid()).orElse(null))
                        .projectsite(projsite)
                        .createuserid(emp)
                        .date1(empjoin.date1())
                        .actiondate(LocalDate.now())
                        .createdate(LocalDate.now())
                        .build());

                // Set a success response with the transaction data
                return Result.success(trsanc);
            }else{
                return Result.failure(new Exception("No Employee found"));
            }

        }
    }

    @Override
    public Result<Transaction> T116andT117andT118(T116andT117andT1118 empt, String acttype) {
        // Find transactions related to the employee and project
        List<Transaction> trs = transactionRepository.findByProjectIdAndEmployeeId(empt.fromprojectid(), empt.employeeid());

        // Initialize a variable to track if a specific action type (ID 2) was found in the transactions
        boolean result = false;

        // Check each transaction for the desired action type
        for (Transaction tr : trs) {
            result = tr.getActiontypeid().getId() == 2;
        }

        if (result) {
            // If the desired action type was found, set an error response
            return Result.failure(new Exception("This Employee's project is already de-allotted"));
        } else {
            // If the action type is not found, proceed with the employee transfer
            Employee emp = employeeService.getEmployeeById(empt.employeeid());

            if (emp != null) {

                // Find the source and target project sites by their IDs



                Actiontype action =new Actiontype();
                if(Objects.equals(acttype, "T116")) {
                    // Find the action type (T104)
                    action = this.getActionType(ActionTypeEnum.T116);
                }
                if(Objects.equals(acttype, "T117")) {
                    // Find the action type (T104)
                    action = this.getActionType(ActionTypeEnum.T117);
                }
                if(Objects.equals(acttype, "T118")) {
                    // Find the action type (T104)
                    action = this.getActionType(ActionTypeEnum.T118);
                }
                Project fromproject=null;
        if(!Objects.equals(acttype, "T118")) {
             fromproject = this.projectRepository.findById(empt.fromprojectid()).orElse(null);
            if (fromproject == null) {
                return Result.failure(new Exception("No record found in fromproject."));
            }

        }

                if (action == null) {

                    return Result.failure(new Exception("No record found in  action"));
                }

                emp.setEditDate(LocalDate.now());
                employeerepository.save(emp);
                // Create a new transaction for the employee transfer
                Transaction trsanc =new Transaction();
                if(!Objects.equals(acttype, "T118")) {
                    trsanc = this.addTransaction(Transaction.builder()
                            .actiontypeid(action)
                            .actiondate(LocalDate.now())
                            .fromprojectid(fromproject)
                            .employeeid(emp)
                            .createuserid(emp)
                            .date1(empt.date1())
                            .date2(empt.date2())
                            .createdate(LocalDate.now())
                            .build());
                }else{
                    trsanc = this.addTransaction(Transaction.builder()
                            .actiontypeid(action)
                            .actiondate(LocalDate.now())
                            .employeeid(emp)
                            .createuserid(emp)
                            .date1(empt.date1())
                            .date2(empt.date2())
                            .createdate(LocalDate.now())
                            .build());
                }

                // Set a success response with the transaction data
                return Result.success(trsanc);
            }else{
                return Result.failure(new Exception("No Employee found"));
            }

        }
    }

    @Override
    public Result<Transaction> T109(T109 emp) {
        // Find transactions related to the employee and project
        List<Transaction> trs = transactionRepository.findByProjectIdAndEmployeeId(emp.fromProjectId(), emp.employeeId());

        // Initialize a variable to track if a specific action type (ID 2) was found in the transactions
        boolean result = false;

        // Check each transaction for the desired action type
        for (Transaction tr : trs) {
            result = tr.getActiontypeid().getId() == 2;
        }

        if (result) {
            // If the desired action type was found, set an error response
            return Result.failure(new Exception("This Employee's project is already de-alloted."));
        } else {
            // If the action type is not found, proceed with the department change
            Employee emp1 = employeeService.getEmployeeById(emp.employeeId());

            if (emp1 != null) {
                emp1.setEditDate(LocalDate.now());

                // Save the updated employee information
                employeerepository.save(emp1);

                // Find the project site by its ID
                Projectsite projectSite = this.projectSiteRepository.findById(emp.fromProjectId()).orElse(null);

                if (projectSite != null) {
                    // Build a transaction based on ActionTypeEnum.T103 and other information
                    Actiontype action = this.getActionType(ActionTypeEnum.T109);
                    Transaction tresult = this.addTransaction(TransactionsBuilder.forT109(action, projectSite, emp1));

                    if (tresult == null) {
                        // Set an error response if the transaction is not found
                        return Result.failure(new Exception("Action type not found."));
                    } else {
                        // Set a success response with the transaction data
                        return Result.success(tresult);
                    }
                } else {
                    // Set an error response if the project site is not found
                    return Result.failure(new Exception("Project-site not found"));
                }
            } else {
                // Set an error response if the employee is not found
                return Result.failure(new Exception("Employee not found"));
            }
        }
    }

    @Override
    public Result<Transaction> T111(T111 emp) {
      Actiontype action = this.getActionType(ActionTypeEnum.T111);
      Project proj=projectRepository.findById(emp.fromprojectid()).orElse(null);
      Status sts=stsrepo.findById(emp.newstatus()).orElse(null);
      Employee em=employeerepository.findById(emp.createdby()).orElse(null);
      if(proj==null){
          return Result.failure(new Throwable("No prject found"));
      }
        if(sts==null){
            return Result.failure(new Throwable("No Status found"));
        }
        if(em==null){
            return Result.failure(new Throwable("No Employee found"));
        }

      Transaction trs=new Transaction();
      trs.setActiontypeid(action);
      trs.setActiondate(LocalDate.now());
      trs.setFromprojectid(proj);
      trs.setNewstatus(sts);
      trs.setCreatedate(LocalDate.now());
      trs.setCreateuserid(em);
     return  Result.success(this.addTransaction(trs));
    }


    private final TransactionRepository transactionRepository;
    private final ProjectSiteRepository projectSiteRepository;
    private final ProjectRepository projectRepository;
    private final AddressRepository addressRepository;
    private final EmployeeService employeeService;

    private final List<Actiontype> actionTypes = new ArrayList<>();
}
