package hu.luterdav.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import hu.luterdav.dto.DepartmentDto;
import hu.luterdav.dto.EmployeeNameDto;
import hu.luterdav.model.Department;
import hu.luterdav.model.Employee;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

	
	EmployeeNameDto employeeToEmployeeNameDto(Employee employee);
	
	List<EmployeeNameDto> employeesToEmployeeNameDtos (List<Employee> employee);
	
	DepartmentDto departmentToDepartmentDto(Department department);
	
	List<DepartmentDto> departmentsToDepartmentDtos(List<Department> department);
	
	
//	List<EmployeeDto> employeesToDtos(List<Employee> employees);
//
//	EmployeeDto employeeToDto(Employee employee);
//
//	Employee dtoToEmployee(EmployeeDto employeeDto);
//
//	List<Employee> dtosToEmployees(List<EmployeeDto> employeeDtos);
//	
	
//	@Mapping(target = "departments", ignore = true)
//	@Named("withoutDepartment")
//	EmployeeDto employeeToDtoWithoutDepartment(Employee employee);
//
//	@IterableMapping(qualifiedByName = "withoutDepartment")
//	List<EmployeeDto> employeesToDtosWithoutDepartment(List<Employee> employees);

}
