package com.example.demo.Controller;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/code")
//@RequiredArgsConstructor
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @GetMapping("/all")
    public ResponseEntity<?> fetchAllEmployee(){
        System.out.println("Hello");
        return new ResponseEntity<List<Employee>>(employeeService.fetchAllEmployee(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addEmployee(@RequestBody Employee e) throws Exception {
        return new ResponseEntity<Employee>(employeeService.addEmployee(e), HttpStatus.OK);
    }
}
