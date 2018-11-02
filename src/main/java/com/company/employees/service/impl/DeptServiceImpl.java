package com.company.employees.service.impl;

import com.company.employees.dao.DepartmentRepository;
import com.company.employees.dao.model.Dept;
import com.company.employees.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeptServiceImpl implements DeptService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public Dept findByDept(String deptname) {
        return departmentRepository.findByDept(deptname);
    }
}
