package hu.luterdav.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.luterdav.dto.DepartmentDto;
import hu.luterdav.dto.EmployeeNameDto;
import hu.luterdav.mapper.MapStructMapper;
import hu.luterdav.model.Department;
import hu.luterdav.model.Employee;
import hu.luterdav.repository.DepartmentRepository;
import hu.luterdav.service.EmployeeService;


@RestController
@RequestMapping("/rest/employees")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	@Autowired
	MapStructMapper mapStructMapper;

	
	@GetMapping
	public List<EmployeeNameDto> getAllEmployees(
			@PageableDefault(sort = { "firstName", "lastName" }, direction = Sort.Direction.ASC) Pageable pageable) {
		Page<Employee> page = employeeService.findAll(pageable);
		return	mapStructMapper.employeesToEmployeeNameDtos(page.getContent());
	}
	
//	@JsonView(View.BaseData1.class)
	@GetMapping(params = "department")
	public List<EmployeeNameDto> getEmployeesByDepartment(@RequestParam String department, 
			@PageableDefault(sort = { "firstName", "lastName" }, direction = Sort.Direction.ASC) Pageable pageable) {
		Page<Employee> page = employeeService.findByDepartment(department, pageable);
		return	mapStructMapper.employeesToEmployeeNameDtos(page.getContent());
	}

//	@JsonView(View.BaseData2.class)
//	@GetMapping("/groupby/department")
//	public List<EmployeeDto> getEmployeesGroupedByDepartment( 
//			@PageableDefault(sort = { "firstName", "lastName" }, direction = Sort.Direction.ASC) Pageable pageable) {
//		Page<Employee> page = employeeService.findAllByDepartment(pageable);
//		return	employeeMapper.employeesToDtos(page.getContent());
//	}

	@GetMapping("/groupby/department")
	public List<DepartmentDto> getEmployeesGroupedByDepartment() {
		List<Department> departments = departmentRepository.findAllSorted();
		return	mapStructMapper.departmentsToDepartmentDtos(departments);
	}



	

//	@GetMapping("/groupby/department")
//	public List<EmployeeDto> getAllEmployees(
//			@PageableDefault(sort = { "firstName", "lastName" }, direction = Sort.Direction.ASC) Pageable pageable) {
//		Page<Employee> page = employeeService.findAll(pageable);
//		return	employeeMapper.employeesToDtos(page.getContent());
//	}


//	@GetMapping("/{id}")
//	public EmployeeDto getById(@PathVariable long id) {
//		Employee employee = employeeService.findById(id)
//				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//		return employeeMapper.employeeToDto(employee);
//	}
	
//	@PostMapping
//	@PreAuthorize("hasAuthority('TEACHER')")
//	public EmployeeDto createEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
//		return employeeMapper.employeeToDto(employeeService.save(employeeMapper.dtoToEmployee(employeeDto)));
//	}
//	
//	@PutMapping("/{id}")
//	@PreAuthorize("hasAuthority('TEACHER')")
//	public EmployeeDto updateEmployee(@PathVariable long id, @RequestBody @Valid EmployeeDto employeeDto) {
//		Employee employee;
//		try {
//			employee = employeeService.update(employeeMapper.dtoToEmployee(employeeDto), id);
//		} catch (NoSuchElementException e) {
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//		}
//		return employeeMapper.employeeToDto(employee);
//	}
//
//	@DeleteMapping("/{id}")
//	@PreAuthorize("hasAuthority('TEACHER')")
//	public void deleteEmployee(@PathVariable long id) {
//		employeeService.delete(id);
//	}

}
