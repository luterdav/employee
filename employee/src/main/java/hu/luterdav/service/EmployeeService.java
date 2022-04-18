package hu.luterdav.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import hu.luterdav.model.Employee;
import hu.luterdav.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	

	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}
	
	public Page<Employee> findAll(Pageable pageable) {
		
//		Sort sortedPage = pageable.getSortOr(Sort.by("name"));
		
		return employeeRepository.findAll(pageable);
	}
	
//	public Page<Employee> findAllByDepartment(Pageable pageable) {
//		return employeeRepository.findAllByDepartment(pageable);
//	}
	
	public List<Employee> findAllByDepartment() {
		return employeeRepository.findAllByDepartment();
	}

	public Optional<Employee> findById(long id) {
		return employeeRepository.findById(id);
	}
	
	public Page<Employee> findByDepartment(String department, Pageable pageable){
		return employeeRepository.findByDepartment(department, pageable);
	}

	@Transactional
	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Transactional
	public Employee update(Employee newEmployee, long id) {
		if (!employeeRepository.existsById(id))
			throw new NoSuchElementException();
		newEmployee.setId(id);
		return employeeRepository.save(newEmployee);
	}

	@Transactional
	public void delete(long id) {
		employeeRepository.deleteById(id);
	}

//	public Page<Employee> findEmployee(EmployeeFilterDto example, Pageable page) {
//
//		String firstName = example.getFirstName();
//		String lastName = example.getLastName();
//		int age = example.getAge();
//
//		Specification<Employee> spec = Specification.where(null);
//
//		if (StringUtils.hasText(firstName))
//			spec = spec.and(EmployeeSpecifications.hasFirstName(firstName));
//		if (StringUtils.hasText(lastName))
//			spec = spec.and(EmployeeSpecifications.hasLastName(lastName));
//		if (age > 0)
//			spec = spec.and(EmployeeSpecifications.hasAge(age));
//
//		return this.employeeRepository.findAll(spec, page);
//	}

}
