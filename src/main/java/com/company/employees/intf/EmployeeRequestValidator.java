package com.company.employees.intf;

import com.company.employees.exceptions.BadRequestException;
import com.company.employees.service.DeptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Component
public class EmployeeRequestValidator {
    private static final Logger log = LoggerFactory.getLogger(EmployeeRequestValidator.class);
    @Autowired
    DeptService deptService;

    public void validateRequest(EmployeeRequest employeeRequest) {

        Optional.ofNullable(employeeRequest.getFirstName()).orElseThrow(() -> new BadRequestException("Invalid FirstName"));
        Optional.ofNullable(employeeRequest.getLastName()).orElseThrow(() -> new BadRequestException("Invalid LastName"));

        if (!StringUtils.isEmpty(employeeRequest.getDeptname())){
            throw new BadRequestException( "DepartmentName Mandatory");
        }/* else {
            //validate DepartmentName
            if( !deptService.validateDeptName()) {
                throw new BadRequestException( "Invalid DepartmentName");
            }
        }*/
    }
}
