package com.company.employees.dao;

import com.company.employees.dao.model.Dept;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends CrudRepository<Dept, Integer> {

    @Query("select d from Dept d where d.name= :name")
    Dept findByDept(@Param("name") String name );
}
