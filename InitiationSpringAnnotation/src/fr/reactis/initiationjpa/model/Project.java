package fr.reactis.initiationjpa.model;

import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
@Inheritance (strategy=InheritanceType.SINGLE_TABLE)
// Cette classe a des h�riters. QualityProject etc.
// Hibernate peut enregistrer les h�ritiers dans des classes totalement diff�rentes (en effet il y a des champs en plus)
// Ou dans la m�me table � tel ou tel champ. Si peu de champs diff�rents on peut tout mettre dans la m�me table.
// Dans ce cas les champs non concern�s (refQuality pour un Project de base par ex) seront null.
// cf valeurs possibles pour @Inheritance(strategy= xxx )
// Strat�gie par d�faut : SINGLE_TABLE.
@DiscriminatorValue("P")
//On ajoute un champ qui contient "P" pour Project, "Q" pour entr�e QualityProject, etc.
// Par d�faut ce champ se nomme DTYPE et varchar 31 mais on peut changer avec les annotations DiscriminatorColumn, etc.
@DiscriminatorColumn(name="TypeProject")

public class Project {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String nom;
	
	// n projets pour n employ�s. Il y aura une table de jointure cr��e avec les 2 ID dedans.
	@ManyToMany
	// Renomme la table de jointure et ses entr�es
	@JoinTable(name="ProjectEmployee", joinColumns=@JoinColumn(name="projectId"), inverseJoinColumns=@JoinColumn(name="employeeId"))
	// Si on veut ajouter un champ "date � laquelle un employ� est entr� sur un projet" il faudra cr�er une classe pour �a.
	private List<Employee> employees;

	public Project(Integer id, String nom, List<Employee> employees) {
		super();
		this.id = id;
		this.nom = nom;
		this.employees = employees;
	}
	public Project() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public List<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
	
}
