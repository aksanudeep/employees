package com.company.employees.controller;

import com.company.employees.dao.model.Dept;
import com.company.employees.dao.model.Employees;
import com.company.employees.intf.DepartmentRequestValidator;
import com.company.employees.intf.EmployeeRequest;
import com.company.employees.intf.EmployeeRequestValidator;
import com.company.employees.service.DeptService;
import com.company.employees.service.EmployeeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class EmployeeController {

    private final Logger log = LogManager.getLogger(LoggingController.class);
    @Autowired
    EmployeeService employeeService;

    @Autowired
    DeptService deptService;

    @Autowired
    EmployeeRequestValidator employeeRequestValidator;

    @Autowired
    DepartmentRequestValidator departmentRequestValidator;

    @PostMapping("/employees")
    Employees create(@RequestBody EmployeeRequest employeeRequest) {


        return  employeeService.saveRecord(employeeRequest);
        //Success
    }

    @GetMapping("/employees")
    Iterable<Employees> read() {
        log.info("**** Retriving the employees data ****");
        return employeeService.findAll();

    }

    @PutMapping("/employees")
    Employees update(@RequestBody EmployeeRequest employeeRequest) {
        return employeeService.updateRecord(employeeRequest);

    }

    @DeleteMapping("/employees/{empid}")
    void delete(@PathVariable Integer empid) {
        log.debug("A DEBUG Message test to delete");
        employeeService.deleteRecord(empid);
        log.info("Deleting the Employyee with empid" + empid);
    }

    @GetMapping("/employees/{empid}")
    Optional<Employees> findByID(@PathVariable Integer empid) {
        return employeeService.findById(empid);
    }

    @GetMapping("/employees/search")
    Iterable<Employees> findByQuery(
            @RequestParam(value = "first", required = false) String firstName, @RequestParam(value = "last", required = false) String lastname) {
        if (firstName != null && lastname != null)
            return employeeService.findByFirstNameAndLastName(firstName, lastname);
        else if (firstName != null)
            return employeeService.findByFirstName(firstName);
        else if (lastname != null)
            return employeeService.findByLastName(lastname);
        else
            return employeeService.findAll();
    }
}
