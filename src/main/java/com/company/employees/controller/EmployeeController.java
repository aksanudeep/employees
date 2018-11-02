package com.company.employees.controller;

import com.company.employees.dao.model.Dept;
import com.company.employees.dao.model.Employees;
import com.company.employees.intf.DepartmentRequestValidator;
import com.company.employees.intf.EmployeeRequest;
import com.company.employees.intf.EmployeeRequestValidator;
import com.company.employees.service.DeptService;
import com.company.employees.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@RestController
public class EmployeeController {
    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    EmployeeService employeeService;

    @Autowired
    DeptService deptService;

    @Autowired
    EmployeeRequestValidator employeeRequestValidator;

    @Autowired
    DepartmentRequestValidator departmentRequestValidator;

    @PostMapping("/employees")
    Employees create(@Valid @RequestBody EmployeeRequest employeeRequest) {

        log.info("**** Stating Post  Mapping ****");
        //validate request
        employeeRequestValidator.validateRequest(employeeRequest);

        //Find Department
        Dept dept = deptService.findByDept(employeeRequest.getDeptname());
        Employees employees = new Employees();
        BeanUtils.copyProperties(employeeRequest, employees);
        employees.setDeptname(dept);
        return employeeService.save(employees);
        //Success
    }

    @GetMapping("/employees")
    Iterable<Employees> read() {
        return employeeService.findAll();
    }

    @PutMapping("/employees")
    Employees update(@RequestBody Employees employees) {
        return employeeService.save(employees);
    }

    @DeleteMapping("/employees/{empid}")
    void delete(@PathVariable Integer empid)
    {
        employeeService.deleteById(empid);
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
