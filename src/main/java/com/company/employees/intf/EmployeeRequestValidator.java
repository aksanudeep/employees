package com.company.employees.intf;

import com.company.employees.dao.model.Dept;
import com.company.employees.exceptions.BadRequestException;
import com.company.employees.service.DeptService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EmployeeRequestValidator {
    private static final Logger log = LoggerFactory.getLogger(EmployeeRequestValidator.class);
    @Autowired
    DeptService deptService;
    Gson gsonBuilder = new GsonBuilder().create();

    public void validateRequest(EmployeeRequest employeeRequest) {

        Optional.ofNullable(employeeRequest.getFirstName()).orElseThrow(() -> new BadRequestException("Invalid FirstName"));
        Optional.ofNullable(employeeRequest.getLastName()).orElseThrow(() -> new BadRequestException("Invalid LastName"));

        if (employeeRequest.getDeptname().isEmpty()) {
            log.info(employeeRequest.getDeptname());
            throw new BadRequestException("Department Name Mandatory");
        } else {
            Dept dept = deptService.findByDept(employeeRequest.getDeptname());
            if (null == dept) {
                //throw exception
                String klst = gsonBuilder.toJson(deptService.findAll());
                log.error("No rows retrieved from Dept Attached are the available values" + klst);
                throw new BadRequestException("Invalid Department Name , Please check Dept Details");
            }
        }
    }
}
