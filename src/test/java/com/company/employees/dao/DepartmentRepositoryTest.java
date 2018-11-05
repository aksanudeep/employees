package com.company.employees.dao;

import com.company.employees.dao.model.Dept;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@DataJpaTest
public class DepartmentRepositoryTest {
    @Autowired
   private TestEntityManager entityManager;
    @Autowired
   private DepartmentRepository departmentRepository;

    @Test
    public void findByDept() {
        //given
       Dept krish = new Dept();
           krish.setName("IT");
        entityManager.persist(krish);
        entityManager.flush();
        // when
        Dept found = departmentRepository.findByDept(krish.getName());
        // then
        assertThat(found.getName())
                .isEqualTo(krish.getName());
    }
    }
