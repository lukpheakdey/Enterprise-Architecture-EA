package com.packt.webstore.service.impl;

import java.util.List;

import com.packt.webstore.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.packt.webstore.domain.Employee;
import com.packt.webstore.service.EmployeeService;

@Service
@Transactional 
public class EmployeeServiceImpl implements EmployeeService {
	
 	@Autowired
	private EmployeeRepository employeeRepository;

	public void save(Employee employee) {
		employeeRepository.save(employee);
	}
	public List<Employee> findAll() {
		return (List<Employee>)employeeRepository.findAll();
	}

	public Employee findByEmployeeNumber(Long employeeId) {
		return employeeRepository.findByEmployeeNumber(employeeId);
	}
 

}
