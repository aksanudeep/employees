package com.company.employees.service;

import com.company.employees.model.Dept;
import com.company.employees.model.Employees;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeService extends CrudRepository <Employees, Integer> {
   Iterable<Employees> findByFirstNameAndLastName(String firstName, String lastName);
    Iterable<Employees> findByFirstName(String firstName);
    Iterable<Employees> findByLastName(String lastName);
   // Iterable<Dept> findByDept(Integer deptid);

}
