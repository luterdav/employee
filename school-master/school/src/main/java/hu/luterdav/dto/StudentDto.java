package hu.luterdav.dto;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

public class StudentDto {
	
	private long id;
	@NotEmpty
	private String firstName;
	@NotEmpty
	private String lastName;
	@Positive
	private int age;

	public StudentDto() {
	}

	public StudentDto(String firstName, String lastName, int age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	

}
