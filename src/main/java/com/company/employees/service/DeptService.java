package com.company.employees.service;

import com.company.employees.dao.model.Dept;


public interface DeptService {

    Dept findByDept(String deptname);

    Dept findByDeptname(String name);

    Iterable<Dept> findAll();
}
