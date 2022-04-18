package hu.luterdav.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import hu.luterdav.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {
	
//	@EntityGraph(attributePaths = "courses")
//	Page<Student> findAll(Specification<Student> spec, Pageable pageable);
//	
//	@EntityGraph(attributePaths = "courses")
//	Optional<Student> findById(long id);
	
}

