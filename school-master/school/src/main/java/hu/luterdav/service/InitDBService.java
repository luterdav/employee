package hu.luterdav.service;

import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import hu.luterdav.model.Course;
import hu.luterdav.model.SchoolUser;
import hu.luterdav.model.Student;
import hu.luterdav.model.Teacher;
import hu.luterdav.repositories.CourseRepository;
import hu.luterdav.repositories.SchoolUserRepository;
import hu.luterdav.repositories.StudentRepository;
import hu.luterdav.repositories.TeacherRepository;

@Service
public class InitDBService {
	
	StudentRepository studentRepository;
	TeacherRepository teacherRepository;
	CourseRepository courseRepository;
	SchoolUserRepository schoolUserRepository;
	PasswordEncoder passwordEncoder;
	
	public InitDBService(StudentRepository studentRepository, TeacherRepository teacherRepository,
			CourseRepository courseRepository, SchoolUserRepository schoolUserRepository,
			PasswordEncoder passwordEncoder) {
		this.studentRepository = studentRepository;
		this.teacherRepository = teacherRepository;
		this.courseRepository = courseRepository;
		this.schoolUserRepository = schoolUserRepository;
		this.passwordEncoder = passwordEncoder;
	}



	public void init() {
		Student student1 = studentRepository.save(new Student("Peter", "Johnson", 16));
		Student student2 = studentRepository.save(new Student("John", "Peterson", 14));
		
		Teacher teacher1 = teacherRepository.save(new Teacher("Patrick", "Wilson", "Math teacher"));
		Teacher teacher2 = teacherRepository.save(new Teacher("Bill", "Merrick", "Grammar teacher"));
		
		Course course1 = courseRepository.save(new Course("Math", student1, teacher1));
		Course course2 = courseRepository.save(new Course("Grammar", student2, teacher2));
		
		SchoolUser userStudent = schoolUserRepository.save(new SchoolUser("peter", passwordEncoder.encode("pass"), Set.of("STUDENT")));
		SchoolUser userTeacher = schoolUserRepository.save(new SchoolUser("billw", passwordEncoder.encode("pass"), Set.of("TEACHER")));
		
		
	}

}
