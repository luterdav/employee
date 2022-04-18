package hu.luterdav.web;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import hu.luterdav.dto.StudentDto;
import hu.luterdav.dto.StudentFilterDto;
import hu.luterdav.mapper.StudentMapper;
import hu.luterdav.model.Student;
import hu.luterdav.service.StudentService;


@RestController
@RequestMapping("/api/students")
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	StudentMapper studentMapper;
	

	
	@GetMapping
	public List<StudentDto> getAll(StudentFilterDto studentFilterDto, 
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
		Page<Student> page = studentService.findStudent(studentFilterDto, pageable);
		return	studentMapper.studentsToDtos(page.getContent());
	}

	@GetMapping("/{id}")
	public StudentDto getById(@PathVariable long id) {
		Student student = studentService.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		return studentMapper.studentToDto(student);
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('TEACHER')")
	public StudentDto createStudent(@RequestBody @Valid StudentDto studentDto) {
		return studentMapper.studentToDto(studentService.save(studentMapper.dtoToStudent(studentDto)));
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('TEACHER')")
	public StudentDto updateStudent(@PathVariable long id, @RequestBody @Valid StudentDto studentDto) {
		Student student;
		try {
			student = studentService.update(studentMapper.dtoToStudent(studentDto), id);
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return studentMapper.studentToDto(student);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('TEACHER')")
	public void deleteStudent(@PathVariable long id) {
		studentService.delete(id);
	}

}
