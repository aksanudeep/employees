package com.company.employees.service.impl;

import com.company.employees.controller.LoggingController;
import com.company.employees.dao.EmployeeRepository;
import com.company.employees.dao.model.Dept;
import com.company.employees.dao.model.Employees;
import com.company.employees.exceptions.BadRequestException;
import com.company.employees.intf.EmployeeRequest;
import com.company.employees.intf.EmployeeRequestValidator;
import com.company.employees.service.DeptService;
import com.company.employees.service.EmployeeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
@Component
public class EmployeeServiceImpl implements EmployeeService {
    private final Logger log = LogManager.getLogger(LoggingController.class);

    @Autowired
    EmployeeRepository empRepository;

    @Autowired
    DeptService deptService;

    @Autowired
    EmployeeRequestValidator employeeRequestValidator;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = false)
    public Employees save(Employees employees) {
        log.info("***********Saving the Data**********");
        return empRepository.save(employees);
    }

    @Override
    public Iterable<Employees> findAll() {
        return empRepository.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = false)
    public void deleteById(Integer empid) {
        empRepository.deleteById(empid);

    }

    @Override
    public Optional<Employees> findById(Integer empid) {
        return empRepository.findById(empid);
    }

    @Override
    public Iterable<Employees> findByFirstNameAndLastName(String firstName, String lastname) {
        return empRepository.findByFirstNameAndLastName(firstName, lastname);
    }

    @Override
    public Iterable<Employees> findByFirstName(String firstName) {
        return empRepository.findByFirstName(firstName);
    }

    @Override
    public Iterable<Employees> findByLastName(String lastname) {
        return empRepository.findByLastName(lastname);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public Employees updateRecord(EmployeeRequest employeeRequest) {
        log.info("  Updating the Employees");
        employeeRequestValidator.validateRequest(employeeRequest);
        //first get the existing record
        Optional<Employees> existingrecord = findById(employeeRequest.getEmpid());
        if (existingrecord.isPresent()) {
            //good
            Dept dept = deptService.findByDept(employeeRequest.getDeptname());
            Employees newEmployeeRecord = new Employees();
            BeanUtils.copyProperties(employeeRequest, newEmployeeRecord);
            //newEmployeeRecord.setEmpid(employeeRequest.getEmpid());
            newEmployeeRecord.setDeptname(dept);
            return save(newEmployeeRecord);
        } else {
            //no record found to update //exception
            throw new BadRequestException("No user found with employee id :" + employeeRequest.getEmpid());
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public Employees saveRecord(EmployeeRequest employeeRequest) {
        log.info("**** Stating Post  Mapping ****");
        //validate request
        employeeRequestValidator.validateRequest(employeeRequest);
        //Find Department
        Dept dept = deptService.findByDept(employeeRequest.getDeptname());
        Employees employees = new Employees();
        BeanUtils.copyProperties(employeeRequest, employees);
        employees.setDeptname(dept);
        return save(employees);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public void deleteRecord(Integer empid) {
        Optional<Employees> existingrecord = findById(empid);
        if (existingrecord.isPresent()){
            empRepository.deleteById(empid);
            log.info("Deleting the Employyee with empid" + empid);
        }

            else {
            //no record found to update //exception
            log.error("Url not valid");
            throw new BadRequestException("No user found with employee id to Delete :" + empid);

        }
    }


}
