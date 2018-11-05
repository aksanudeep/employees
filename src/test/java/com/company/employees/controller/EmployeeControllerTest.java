package com.company.employees.controller;

import com.company.employees.dao.model.Employees;
import com.company.employees.intf.DepartmentRequestValidator;
import com.company.employees.intf.EmployeeRequestValidator;
import com.company.employees.service.DeptService;
import com.company.employees.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Arrays;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static net.bytebuddy.matcher.ElementMatchers.isGetter;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(value = EmployeeController.class, secure = false)
public class EmployeeControllerTest {


    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EmployeeService employeeService;
    @MockBean
    private EmployeeRequestValidator employeeRequestValidator;
    @MockBean
    private DepartmentRequestValidator departmentRequestValidator;
    @MockBean
    private DeptService deptService;

/*
    {
         mockEmployee = new Employees("Kri", "sai");
    }

     String exampleCourseJson = "{\"firstName\":\"Kri\",\"lastName\":\"sai\"}";
*/
    @Test
    public  void givenEmployeesReturnJsonArray() throws Exception {
        Employees krish = new Employees();
       // krish.setFirstName("kri");
       // krish.setLastName("sai");
        krish.setEmpid(1);


        List<Employees> allEmployees = Arrays.asList(krish);

        given(employeeService.findAll()).willReturn(allEmployees);

        mockMvc.perform(get("/employees")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(1)));


    }
    @Test
    public void create() {
    }

    @Test
    public void read() {
        //Mockito.when(employeeService.findById())
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void findByID() {
    }

    @Test
    public void findByQuery() {
    }
}