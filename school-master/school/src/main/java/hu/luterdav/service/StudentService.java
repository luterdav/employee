package hu.luterdav.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import hu.luterdav.dto.StudentFilterDto;
import hu.luterdav.model.Student;
import hu.luterdav.repositories.StudentRepository;

@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepository;

	public List<Student> findAll() {
		return studentRepository.findAll();
	}

	public Optional<Student> findById(long id) {
		return studentRepository.findById(id);
	}

	@Transactional
	public Student save(Student student) {
		return studentRepository.save(student);
	}

	@Transactional
	public Student update(Student newStudent, long id) {
		if (!studentRepository.existsById(id))
			throw new NoSuchElementException();
		newStudent.setId(id);
		return studentRepository.save(newStudent);
	}

	@Transactional
	public void delete(long id) {
		studentRepository.deleteById(id);
	}

	public Page<Student> findStudent(StudentFilterDto example, Pageable page) {

		String firstName = example.getFirstName();
		String lastName = example.getLastName();
		int age = example.getAge();

		Specification<Student> spec = Specification.where(null);

		if (StringUtils.hasText(firstName))
			spec = spec.and(StudentSpecifications.hasFirstName(firstName));
		if (StringUtils.hasText(lastName))
			spec = spec.and(StudentSpecifications.hasLastName(lastName));
		if (age > 0)
			spec = spec.and(StudentSpecifications.hasAge(age));

		return this.studentRepository.findAll(spec, page);
	}

}
