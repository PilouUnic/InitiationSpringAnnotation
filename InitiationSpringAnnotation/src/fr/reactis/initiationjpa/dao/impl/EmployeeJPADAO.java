package fr.reactis.initiationjpa.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import fr.reactis.initiationjpa.dao.EmployeeDAO;
import fr.reactis.initiationjpa.model.Employee;

@Repository("employeeJPADAO")
public class EmployeeJPADAO extends GenericJPADAO<Employee, Integer> implements EmployeeDAO {

	public EmployeeJPADAO() {
		super(Employee.class);
	}

	@Override
	public List<Employee> findWithProject() {
		TypedQuery<Employee> query = entityManager.createQuery("Select e from Employee e left join fetch e.projects p ", Employee.class);
		return query.getResultList();
	}

	@Override
	public List<Employee> findByProjectName(String name) {
		TypedQuery<Employee> query = entityManager.createQuery("Select e from Employee e join e.projects p Where p.nom=:nomProjet", Employee.class);
		query.setParameter("nomProjet", name);
		return query.getResultList();
	}

	@Override
	public List<Employee> findByStartDate(Date from, Date to) {
		TypedQuery<Employee> query = entityManager.createQuery("Select e from Employee e Where e.startDate Between :from And :to", Employee.class);
		query.setParameter("from", from);
		query.setParameter("to", to);
		return query.getResultList();
	}

	@Override
	public List<Employee> findAllWithoutProject() {
		TypedQuery<Employee> query = entityManager.createQuery("Select e from Employee e Where e.projects IS EMPTY", Employee.class);
		return query.getResultList();
	}

	@Override
	public Employee findLastEmployee() {
		TypedQuery<Employee> query = entityManager.createQuery("Select e from Employee e Where e.startDate = (Select max(emp.startDate) From Employee emp)", Employee.class);
		return query.getSingleResult();
	}


}
