package hu.luterdav.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

@XmlRootElement(name = "list")
@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Transient
	private String name;

	private String firstName;
	private String lastName;

	@Transient
	private List<Employee> employees = new ArrayList<>();
	@Transient
	private List<String> department = new ArrayList<>();

//    @OneToMany()
//	private List<Department> departmentList;

//	@JsonIgnore
	@ManyToMany
	@JoinTable(
      name = "employee_department", 
	  joinColumns = @JoinColumn(name = "employee_id"), 
	  inverseJoinColumns = @JoinColumn(name = "department_id"))
	private List<Department> departmentList;

//	@ElementCollection(fetch = FetchType.EAGER)
//	private List<String> department = new ArrayList<>();

	public Employee() {
	}

	public Employee(String firstName, String lastName, List<Department> departmentList) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.departmentList = departmentList;
	}

	@XmlElement(name = "employee")
	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@XmlElement(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	@XmlElement(name = "department")
	public List<String> getDepartment() {
		return department;
	}

	public void setDepartment(List<String> department) {
		this.department = department;
	}

	public List<Department> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<Department> departmentList) {
		this.departmentList = departmentList;
	}

	public void addDepartment(Department d) {
		if(this.departmentList == null)
			this.departmentList = new ArrayList<>();
		this.departmentList.add(d);
		d.getEmployeeList().add(this);
	}

	@Override
	public String toString() {
		return "Employee [firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
	

}
