package com.company.employees.service.impl;

import com.company.employees.dao.EmployeeRepository;
import com.company.employees.dao.model.Employees;
import com.company.employees.service.DeptService;
import com.company.employees.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository empRepository;

    @Autowired
    DeptService deptService;

    @Override
    public  Employees save(Employees employees) {
        return empRepository.save(employees);
    }

    @Override
    public Iterable<Employees> findAll() {
        return empRepository.findAll();
    }

    @Override
    public void deleteById(Integer empid) {

    }

    @Override
    public Optional<Employees> findById(Integer empid)
    {
        return empRepository.findById(empid);
    }

    @Override
    public Iterable<Employees> findByFirstNameAndLastName(String firstName, String lastname)
    {
        return empRepository.findByFirstNameAndLastName(firstName, lastname);
    }

    @Override
    public Iterable<Employees> findByFirstName(String firstName)
    {
        return empRepository.findByFirstName(firstName);
    }

    @Override
    public Iterable<Employees> findByLastName(String lastname)
    {
        return empRepository.findByLastName(lastname);
    }
}
