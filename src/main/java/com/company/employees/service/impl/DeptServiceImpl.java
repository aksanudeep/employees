package com.company.employees.service.impl;

import com.company.employees.dao.DepartmentRepository;
import com.company.employees.dao.model.Dept;
import com.company.employees.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import javax.transaction.Transactional;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public Dept findByDept(String deptname) {
        return departmentRepository.findByDept(deptname);
    }

    @Override
    public Dept findByDeptname(String name) {
        return departmentRepository.findByDept(name);
    }

    @Override
    public Iterable<Dept> findAll() {
        return departmentRepository.findAll();
    }
}
