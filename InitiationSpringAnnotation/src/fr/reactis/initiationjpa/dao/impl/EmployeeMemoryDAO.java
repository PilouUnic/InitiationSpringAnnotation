package fr.reactis.initiationjpa.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import fr.reactis.initiationjpa.dao.EmployeeDAO;
import fr.reactis.initiationjpa.model.Employee;

//Cette classe créée en vitesse sert à montrer que Spring crashe s'il existe dans le code 1
//variable EmployeeDAO en autowired ( = doit recevoir une instance de classe par injection)
//et PLUS DE 1 classe qui implémente EmployeeDAO et qui possède un @Repository ou
//un @Service. (dans le cas présent, EmployeeJPADAO et EmployeeMemoryDAO)
//Il ne peut injecter 2 variables dans 1 donc forcément erreur à la compilation.
@Repository("totoMemory")
// Pour éviter cela, on donne des "value" (comme des ID) aux annotations donc aux classes.
// Note : @Repository("xxx") = @Repository(value="xxx")
public class EmployeeMemoryDAO implements EmployeeDAO {

	private List<Employee> employees;
	
	@Override
	public Employee save(Employee entite) {
		employees.add(entite);
		return entite;
	}

	@Override
	public Employee findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Employee> findByParameter(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> findWithProject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> findByProjectName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> findByStartDate(Date from, Date to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> findAllWithoutProject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee findLastEmployee() {
		// TODO Auto-generated method stub
		return null;
	}

}
