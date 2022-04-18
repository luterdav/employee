package hu.luterdav.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hu.luterdav.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

//    @EntityGraph(attributePaths = "departmentList")	
//	@EntityGraph(attributePaths = "departmentList")
//	@Query("SELECT e FROM Employee e")
	Page<Employee> findAll(Pageable pageable);
	
	
	@EntityGraph(attributePaths = {"departmentList"})
	@Query("SELECT e FROM Employee e")
//	Page<Employee> findAllByDepartment(Pageable pageable);
	List<Employee> findAllByDepartment();

	@Query("SELECT e FROM Employee e JOIN e.departmentList d WHERE d.name = :department")
	Page<Employee> findByDepartment(String department, Pageable pageable);

}

