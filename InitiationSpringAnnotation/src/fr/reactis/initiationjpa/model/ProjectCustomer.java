package fr.reactis.initiationjpa.model;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class ProjectCustomer {

	// Correspond � une table sp�ciale ayant un ID compos� en BDD.
	// Il ne peut y avoir QUE 1 ID par Entity Hibernate.
	// Il a donc fallu cr�er l'objet PKProjectCustomer.
	// (il y a n projets par customer, n customers par projet. => cr�ation d'une table de jointure)
	// ( + il y a une date � laquelle un customer a commenc� dans tel projet. Champ date "startingDate" dans cette table)

	@EmbeddedId
	private PKProjectCustomer pk;
	private Date startingDate;


	public ProjectCustomer(PKProjectCustomer pk, Date startingDate) {
		super();
		this.pk = pk;
		this.startingDate = startingDate;
	}
	public ProjectCustomer() {}


	public PKProjectCustomer getPk() {
		return pk;
	}
	public void setPk(PKProjectCustomer pk) {
		this.pk = pk;
	}
	public Date getStartingDate() {
		return startingDate;
	}
	public void setStartingDate(Date startingDate) {
		this.startingDate = startingDate;
	}


}
