package hu.luterdav.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.luterdav.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
	
	
}

