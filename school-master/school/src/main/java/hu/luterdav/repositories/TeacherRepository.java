package hu.luterdav.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.luterdav.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
	
	
}

