package com.company.employees.controller;

import com.company.employees.model.Dept;
import com.company.employees.model.Employees;
import com.company.employees.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class EmployeeController {

 @Autowired
EmployeeService empservice;

 @PostMapping("/employees")
    Employees create(@RequestBody Employees employees){
     return  empservice.save(employees);//&& empservice.save(dept);

 }

    @GetMapping("/employees")
    Iterable<Employees> read(){
     return empservice.findAll();
    }

    @PutMapping("/employees")
    Employees update(@RequestBody Employees employees){
     return empservice.save(employees);
    }
    @DeleteMapping("/employees/{empid}")
     void delete(@PathVariable Integer empid){
         empservice.deleteById(empid);
    }

    @GetMapping("/employees/{empid}")
    Optional<Employees> findByID(@PathVariable Integer empid) {
     return  empservice.findById(empid);
    }
    @GetMapping("/employees/search")
    Iterable<Employees> findByQuery(
            @RequestParam(value = "first",required = false) String firstName, @RequestParam(value = "last", required = false) String lastname)
    {
        if(firstName!=null && lastname!=null)
        return empservice.findByFirstNameAndLastName(firstName, lastname);
        else if(firstName !=null)
            return empservice.findByFirstName(firstName);
        else if(lastname!=null)
            return empservice.findByLastName(lastname);
        else
            return empservice.findAll();
    }
}
