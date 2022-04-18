package hu.luterdav.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hu.luterdav.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

	Department findDistinctByName(String name);
	
	Department findByName (String name);
	
	@Query("SELECT DISTINCT d FROM Department d "
			+ "LEFT JOIN FETCH d.employeeList e "
			+ "ORDER BY d.name, e.firstName, e.lastName")
	List<Department> findAllSorted();

}

