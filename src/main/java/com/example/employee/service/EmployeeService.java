package com.example.employee.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.employee.model.Employee;

@Service
public class EmployeeService {
	private final List<Employee> employeeList = new ArrayList<>();

	public Employee addEmployee(Employee employee) {
		employeeList.add(employee);
		return employee;
	}

	public Employee getEmployeeById(int id) {
		return employeeList.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
	}

	public List<Employee> getAllEmployees() {
		return employeeList;
	}

	public boolean deleteEmployee(int id) {
		return employeeList.removeIf(e -> e.getId() == id);
	}

	public Employee updateSalary(int id, double newSalary) {
		for (Employee e : employeeList) {
			if (e.getId() == id) {
				e.setSalary(newSalary);
				return e;
			}
		}
		return null;
	}

	public Map<String, Double> getAverageSalaryPerDepartment() {
		return employeeList.stream().collect(
				Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
	}

	public Employee getHighestPaidEmployee() {
		return employeeList.stream().max(Comparator.comparingDouble(Employee::getSalary)).orElse(null);
	}

	public List<Employee> getEmployeesAboveAverage() {
		double avg = employeeList.stream().mapToDouble(Employee::getSalary).average().orElse(0);
		return employeeList.stream().filter(e -> e.getSalary() > avg).collect(Collectors.toList());
	}
}
