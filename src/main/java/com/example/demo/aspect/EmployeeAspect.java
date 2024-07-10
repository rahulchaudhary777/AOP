package com.example.demo.aspect;

import com.example.demo.entity.Employee;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class EmployeeAspect {
    @Before(value = "execution(* com.example.demo.Controller.EmployeeController.*(..))")
    public void beforeAdvice(JoinPoint joinPoint){
        System.out.println("Before :- Request to "+joinPoint.getSignature().getName()+" started at "+ new Date());
    }

    @After(value = "execution(* com.example.demo.Controller.EmployeeController.*(..))")
    public void afterAdvice(JoinPoint joinPoint){
        System.out.println("After :- Request to "+joinPoint.getSignature().getName()+" started at "+ new Date());
    }

    @Before(value = "execution(* com.example.demo.service.EmployeeService.*(..))")
    public void beforeServiceAdvice(JoinPoint joinPoint){
        System.out.println("Before :- Request to service layer "+joinPoint.getSignature().getName()+" started at "+ new Date());
    }

    @After(value = "execution(* com.example.demo.service.EmployeeService.*(..))")
    public void afterServiceAdvice(JoinPoint joinPoint){
        System.out.println("After :- Request to service layer "+joinPoint.getSignature().getName()+" started at "+ new Date());
    }

    @AfterReturning(value = "execution(* com.example.demo.service.EmployeeService.*(..))", returning = "employee")
    public void afterReturnServiceAdvice(JoinPoint joinPoint, Employee employee){
        System.out.println("After :- Request to service layer "+employee.getId()+" -> "+ employee.getName()+" ended at "+ new Date());
    }

    @AfterThrowing(value = "execution(* com.example.demo.service.EmployeeService.*(..))", throwing = "e")
    public void afterThrowingServiceAdvice(JoinPoint joinPoint, Exception e){
        System.out.println("Business logic to save an employee return an exception :- "+ e.getMessage());
    }

    @Around(value = "execution(* com.example.demo.service.EmployeeService.addEmployee(..))")
    public Employee aroundAdvice(ProceedingJoinPoint proceedingJoinPoint){
        System.out.println("Inside around advice");
        try{
            Employee emp = new Employee();
            emp.setName("doni");
            Employee[] emparr = new Employee[1];
            emparr[0] = emp;
            Employee temp = (Employee) proceedingJoinPoint.proceed(emparr);
            System.out.println(temp);
            return temp;
        } catch (Throwable e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
