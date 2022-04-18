package hu.luterdav.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.luterdav.model.Department;
import hu.luterdav.model.Employee;
import hu.luterdav.repository.DepartmentRepository;
import hu.luterdav.repository.EmployeeRepository;

@Service
public class InitDBService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	DepartmentRepository departmentRepository;

	@Transactional
	public void initDB() {
		try {
			File file = new File("src/main/resources/employee.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Employee.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			Employee employeesFromXml = (Employee) unmarshaller.unmarshal(file);
			
			List<Employee> employees = new ArrayList<>();
			Set<Department> departments = new HashSet<>();
//			Department department = new Department();
			for(Employee e : employeesFromXml.getEmployees()) {
				String[] fullName = e.getName().split(" ");				
				e.setFirstName(fullName[0]);
				e.setLastName(fullName[1]);
				
				
				e.getDepartment().forEach(d ->{
					Department department = new Department(d);
//					Department savedDepartment;
//					departments.add(department);
//					departmentRepository.save(department);
//					e.addDepartment(department);
//			    	dp.addEmployee(savedEmp);
//					department.setName(d);
					if(departmentRepository.findDistinctByName(d) == null) {
						departmentRepository.save(department);
			    	}
					
					e.addDepartment(departmentRepository.findByName(d));
				
					
//					System.out.println(e.getName() + ", " + e.getDepartmentList());
//				    System.out.println(department.getName() + ", " + department.getEmployeeList());
			    });
//				employees.add(e);
				employeeRepository.save(e);
				
			   
			}
//			System.out.println(departments);
//			System.out.println(employees);
			
//			departmentRepository.saveAll(departments);
//			employeeRepository.saveAll(employees);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	public void init() {
//		employeeService.save(new Employee(null, "peter", "smith", null, null, null));
//		departmentRepository.save(new Department("it", null ));
//	}


}
