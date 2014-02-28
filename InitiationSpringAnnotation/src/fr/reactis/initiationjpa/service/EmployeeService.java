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
// (@Service et @Repository sont quasi identiques, sauf au niveau de certaines erreurs lanc�es)
//@Scope("prototype")
// Scope prototype : emp�cher qu'il soit singleton
// Doit �tre utilis� dans le cas d'un log user par exemple, etc.
public class EmployeeService {

	@Autowired
	// = donne l'ordre : Injecte dans cette variable une classe que tu as instanci�e, c�d qui est annot�e 
	// en Service ou Repository quelque part dans le code
	// (dans ce cas, EmployeeJPADAO qui impl�mente EmployeeDAO. Spring fait donc office de Factory)
	@Qualifier("employeeJPADAO")
	// Le qualifier sert d'ID : quelle classe doit �tre mise l� dedans
	// Si jamais il en existe plus de 1.
	// (en l'occurrence : EmployeeJPADAO et EmployeeMemoryDAO poss�dent tous deux
	// une annotation @Service/@Repository pour �tre instanci�es. 
	// Spring, sans ce qualifier, ne saurait pas quelle classe choisir.
	private EmployeeDAO employeDAO1;
	
	@Autowired
	@Qualifier("totoMemory")
	private EmployeeDAO employeDAO2;
	// Il s'agit d'un EmployeeMemoryDAO. Cf Qualifier
	
	
	@Autowired
	@Qualifier("choisirEmployeeDAO")
	// Dans ce cas, le Qualifier ne pointe pas vers une classe mais vers une 
	// METHODE qui rend une classe. On appelle la m�thode de DAOConfig
	// cf DAOConfig. Cette classe peut �tre un EmployeeJPADAO ou un EmployeeMemoryDAO
	// On ne sait pas
	private EmployeeDAO employeDAO3;
	
	

	// En d'autres termes :
	// @Service ou @Repository = fais un new XXX(); (instanciation sans params)
	// @Autowired = parmi tes instances cr��es, injecte


	@Transactional
	// = cette m�thode est transactionnelle. (puisque Spring TX se charge de g�rer le transactionnel)
	// d�marre la transaction au d�but de la m�thode, close � la fin. Ca permet les rollback automatiques 
	// (c�d transaction)
	// popagation : quand un transactional appelle un transactionnal, faut-il consid�rer que c'est la m�me transaction
	// ou est-ce une nouvelle.
	// Cf google propagation transaction spring.
	// NOT_SUPPORTED : plante une erreur
	// MANDATORY : doit �tre appell�e par une m�thode qui cr�e la transaction
	// REQUIRE : cr�e une nouvelle transaction
	// SUPPORTS : 
	public Employee save(Employee employee){
		// Comme il y a l'annotation autowired, on peut d�j� s'en servir, il n'est pas null.
		// (instanci� sans params : la m�thode doit bien s�r exister)
		return employeDAO1.save(employee);
	}
}
