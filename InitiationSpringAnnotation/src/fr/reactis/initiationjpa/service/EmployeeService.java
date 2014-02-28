package fr.reactis.initiationjpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.reactis.initiationjpa.dao.EmployeeDAO;
import fr.reactis.initiationjpa.model.Employee;

//Classe dite de service.

@Service
// Service ou Repository = "tu fais new"
// (@Service et @Repository sont quasi identiques, sauf au niveau de certaines erreurs lancées)
//@Scope("prototype")
// Scope prototype : empêcher qu'il soit singleton
// Doit être utilisé dans le cas d'un log user par exemple, etc.
public class EmployeeService {

	@Autowired
	// = donne l'ordre : Injecte dans cette variable une classe que tu as instanciée, càd qui est annotée 
	// en Service ou Repository quelque part dans le code
	// (dans ce cas, EmployeeJPADAO qui implémente EmployeeDAO. Spring fait donc office de Factory)
	@Qualifier("employeeJPADAO")
	// Le qualifier sert d'ID : quelle classe doit être mise là dedans
	// Si jamais il en existe plus de 1.
	// (en l'occurrence : EmployeeJPADAO et EmployeeMemoryDAO possèdent tous deux
	// une annotation @Service/@Repository pour être instanciées. 
	// Spring, sans ce qualifier, ne saurait pas quelle classe choisir.
	private EmployeeDAO employeDAO1;
	
	@Autowired
	@Qualifier("totoMemory")
	private EmployeeDAO employeDAO2;
	// Il s'agit d'un EmployeeMemoryDAO. Cf Qualifier
	
	
	@Autowired
	@Qualifier("choisirEmployeeDAO")
	// Dans ce cas, le Qualifier ne pointe pas vers une classe mais vers une 
	// METHODE qui rend une classe. On appelle la méthode de DAOConfig
	// cf DAOConfig. Cette classe peut être un EmployeeJPADAO ou un EmployeeMemoryDAO
	// On ne sait pas
	private EmployeeDAO employeDAO3;
	
	

	// En d'autres termes :
	// @Service ou @Repository = fais un new XXX(); (instanciation sans params)
	// @Autowired = parmi tes instances créées, injecte


	@Transactional
	// = cette méthode est transactionnelle. (puisque Spring TX se charge de gérer le transactionnel)
	// démarre la transaction au début de la méthode, close à la fin. Ca permet les rollback automatiques 
	// (càd transaction)
	// popagation : quand un transactional appelle un transactionnal, faut-il considérer que c'est la même transaction
	// ou est-ce une nouvelle.
	// Cf google propagation transaction spring.
	// NOT_SUPPORTED : plante une erreur
	// MANDATORY : doit être appellée par une méthode qui crée la transaction
	// REQUIRE : crée une nouvelle transaction
	// SUPPORTS : 
	public Employee save(Employee employee){
		// Comme il y a l'annotation autowired, on peut déjà s'en servir, il n'est pas null.
		// (instancié sans params : la méthode doit bien sûr exister)
		return employeDAO1.save(employee);
	}
}
