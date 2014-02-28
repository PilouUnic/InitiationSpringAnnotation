package fr.reactis.initiationjpa.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({
	@NamedQuery(name="Employee.findAll", query="Select e from Employee e")
})

public class Employee {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="nom", length=50)
	private String lastName;

	@Column(name="prenom", length=50)
	private String firstName;

	@Temporal(TemporalType.DATE)
	private Date startDate;

	@OneToOne(mappedBy="employee", fetch=FetchType.LAZY)
	private Phone phone;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Department department;
	
	@ManyToMany(mappedBy="employees")
	private List<Project> projects;
	

	public Employee() {

	}

	public Employee(String lastName, String firstName) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
	}

	public Employee(Integer id, String lastName, String firstName, Date startDate) {
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.startDate = startDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Phone getPhone() {
		return phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

}