package com.example.demo.service;

import com.example.demo.entity.Employee;
import com.example.demo.repo.EmployeeRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
//@RequiredArgsConstructor
public class EmployeeService {
//    @Autowired
//    private EmployeeRepo employeeRepo;
    @PersistenceContext
    private EntityManager entityManager;

    public List<Employee> fetchAllEmployee(){
        return entityManager.createNamedQuery("Employee.findAll", Employee.class).getResultList();
    }
    public Employee addEmployee(Employee e) throws Exception {
        if(e.getName().length() > 5){
            throw new Exception("Sorry please reduce size of your name");
        }
        entityManager.persist(e);
        System.out.println(e);
        return e;
    }
}
