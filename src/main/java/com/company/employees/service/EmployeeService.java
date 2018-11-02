package com.company.employees.service;

import com.company.employees.dao.model.Employees;
import org.springframework.stereotype.Component;

import java.util.Optional;


public interface EmployeeService {

    Employees save(Employees employees);

    Iterable<Employees> findAll();

    void deleteById(Integer empid);

    Optional<Employees> findById(Integer empid);

    Iterable<Employees> findByFirstNameAndLastName(String firstName, String lastname);

    Iterable<Employees> findByFirstName(String firstName);

    Iterable<Employees> findByLastName(String lastname);


}
