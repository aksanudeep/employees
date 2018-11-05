package com.company.employees.dao;

import com.company.employees.dao.model.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employees, Integer> {

    Iterable<Employees> findByFirstNameAndLastName(String firstName, String lastName);

    Iterable<Employees> findByFirstName(String firstName);

    Iterable<Employees> findByLastName(String lastName);

    //Iterable<Employees>findByDeptName(String deptid );


}

