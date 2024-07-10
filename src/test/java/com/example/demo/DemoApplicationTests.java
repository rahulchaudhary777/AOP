package com.example.demo;

import com.example.demo.entity.Employee;
import com.example.demo.repo.EmployeeRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
class DemoApplicationTests {
	@Autowired
	private EmployeeRepo employeeRepo;

	@Test
	void contextLoads() {
	}

	static Stream<Employee> employeeStream(){
		return Stream.of(
				new Employee(1l, "Mani", "moni rani"),
				new Employee(2l, "Mohit", "mohit rao")
		);
	}
	@ParameterizedTest
	@MethodSource("employeeStream")
	public void addEmployee(Employee e) throws Exception {
		if(e.getName().length() > 5){
			throw new Exception("Sorry please reduce size of your name");
		}
		employeeRepo.save(e);
	}

	@Test
	public void fetchAllEmployee(){
		employeeRepo.findAll();
	}
}
