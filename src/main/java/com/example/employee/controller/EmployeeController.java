package com.example.employee.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee.model.Employee;
import com.example.employee.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private EmployeeService service;

	@PostMapping("/")
	public Employee createEmployee(@RequestBody Employee employee) {
		return service.addEmployee(employee);
	}

	@GetMapping("/{id}")
	public Employee getEmployee(@PathVariable int id) {
		return service.getEmployeeById(id);
	}

	@GetMapping("/all")
	public List<Employee> getAll() {
		return service.getAllEmployees();
	}

	@DeleteMapping("/{id}")
	public String deleteEmployee(@PathVariable int id) {
		boolean deleted = service.deleteEmployee(id);
		return deleted ? "Employee deleted" : "Employee not found";
	}

	@PutMapping("/update-salary")
	public Employee updateSalary(@RequestParam int id, @RequestParam double newSalary) {
		return service.updateSalary(id, newSalary);
	}

	@GetMapping("/average-salary")
	public Map<String, Double> getAverageSalary() {
		return service.getAverageSalaryPerDepartment();
	}

	@GetMapping("/highest-salary")
	public Employee getHighestSalary() {
		return service.getHighestPaidEmployee();
	}

	@GetMapping("/above-average")
	public List<Employee> getAboveAverage() {
		return service.getEmployeesAboveAverage();
	}
}
