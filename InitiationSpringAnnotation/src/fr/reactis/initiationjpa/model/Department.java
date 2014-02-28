package fr.reactis.initiationjpa.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Department {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String nom;
	
	@OneToMany(mappedBy="department")
	// Si on avait laiss� le OneToMany sans mappedBy, la cr�ation de BDD aurait �t� fausse. Cr�ation d'une table en trop et incoh�rence en pratique.
	// ( = un employ� est dans 1 d�partement, mais le d�partement poss�de n employ�s sans tenir compte des ID employ�s !) 
	private List<Employee> employees;
	
	
	public Department(Integer id, String nom) {
		this.id = id;
		this.nom = nom;
	}

	public Department() {
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

}
