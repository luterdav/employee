package hu.webuni.hr.luterdav.service;

import java.util.List;
import java.util.Optional;


import hu.webuni.hr.luterdav.model.Employee;

public interface EmployeeService {
	
	public int getPayRaisePercent(Employee employee);
	
	public Employee save(Employee employee);
	
	public Employee update(Employee employee);
	
	public List<Employee> findAll();
	
	public Optional<Employee> findById(long id);
	
	public void delete(long id);

}
