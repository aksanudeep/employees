package com.company.employees.service;

import com.company.employees.dao.model.Dept;
import org.springframework.stereotype.Component;


public interface DeptService  {

    Dept findByDept(String deptname);
}
