package hu.luterdav.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;

import hu.luterdav.dto.LoginDto;
import hu.luterdav.dto.StudentDto;
import hu.luterdav.dto.StudentFilterDto;
import hu.luterdav.model.SchoolUser;
import hu.luterdav.model.Student;
import hu.luterdav.repositories.CourseRepository;
import hu.luterdav.repositories.SchoolUserRepository;
import hu.luterdav.repositories.StudentRepository;
import hu.luterdav.repositories.TeacherRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class StudentControllerIT {

	private static final String BASE_URI = "/api/students";

	@Autowired
	WebTestClient webTestClient;

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	SchoolUserRepository schoolUserRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	private String username = "billw";
	private String password = "pass";
	private String jwt;

	@BeforeEach
	public void init() {
		if (schoolUserRepository.findByUsername(username).isEmpty()) {
			SchoolUser schoolUser = new SchoolUser();
			schoolUser.setUsername(username);
			schoolUser.setPassword(passwordEncoder.encode(password));
			schoolUser.setRoles(Set.of("TEACHER"));
			schoolUserRepository.save(schoolUser);
		}

		LoginDto body = new LoginDto();
		body.setUsername(username);
		body.setPassword(password);
		jwt = webTestClient
				.post()
				.uri("/api/login")
				.bodyValue(body)
				.exchange()
				.expectBody(String.class)
				.returnResult()
				.getResponseBody();

	}

	@Test
	void testThatNewValidEmployeeCanBeSaved() throws Exception {
		StudentDto student1 = saveStudent(new StudentDto("Peter", "Johnson", 16))
				.expectStatus().isOk()
				.expectBody(StudentDto.class)
				.returnResult()
				.getResponseBody();
		
		StudentDto student2 = saveStudent(new StudentDto("John", "Peterson", 14))
				.expectStatus().isOk()
				.expectBody(StudentDto.class)
				.returnResult()
				.getResponseBody();
		

		StudentFilterDto criteria = new StudentFilterDto();
		criteria.setFirstName("p");
		criteria.setLastName("j");

		List<StudentDto> students = getAllStudents(criteria);

		//assertThat(student1).usingRecursiveComparison().isIn(students);
		//assertThat(student2).usingRecursiveComparison().isNotIn(students);
		assertThat(students)
		    .usingRecursiveFieldByFieldElementComparator()
		    .contains(student1); 
		assertThat(students)
	        .usingRecursiveFieldByFieldElementComparator()
	        .doesNotContain(student2); 
	}

	
	private ResponseSpec saveStudent(StudentDto newStudent) {
		return webTestClient
				.post()
				.uri(BASE_URI)
				.headers(headers -> headers.setBearerAuth(jwt))
				.bodyValue(newStudent)
				.exchange();
	}

	private List<StudentDto> getAllStudents(StudentFilterDto criteria) {
		List<StudentDto> responseList = webTestClient
				.get()
				.uri(uriBuilder -> 
				 uriBuilder
				.path(BASE_URI)
				.queryParam("firstName", criteria.getFirstName())
				.queryParam("lastName", criteria.getLastName())
				.queryParam("age", criteria.getAge())
				.build())
				.headers(headers -> headers.setBearerAuth(jwt))
				.exchange()
				.expectStatus()
				.isOk()
				.expectBodyList(StudentDto.class)
				.returnResult()
				.getResponseBody();
		Collections.sort(responseList, Comparator.comparing(StudentDto::getId));
		return responseList;
	}

}
