package hu.luterdav.service;

import org.springframework.data.jpa.domain.Specification;

import hu.luterdav.model.Student;
import hu.luterdav.model.Student_;

public class StudentSpecifications {

	public static Specification<Student> hasFirstName(String firstName) {
		return (root, cq, cb) -> cb.like(cb.lower(root.get(Student_.firstName)), ("%" + firstName + "%").toLowerCase());
	}

	public static Specification<Student> hasLastName(String lastName) {
		return (root, cq, cb) -> cb.like(cb.lower(root.get(Student_.lastName)), ("%" + lastName + "%").toLowerCase());
	}
	
	public static Specification<Student> hasAge(int age) {
		return (root, cq, cb) -> cb.equal(root.get(Student_.age), age);
	}

}
