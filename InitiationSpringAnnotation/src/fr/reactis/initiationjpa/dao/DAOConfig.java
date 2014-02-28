package fr.reactis.initiationjpa.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.reactis.initiationjpa.dao.impl.EmployeeJPADAO;
import fr.reactis.initiationjpa.dao.impl.EmployeeMemoryDAO;

// Joue le rôle d'une factory.
// La méthode getEmployeeDAO rend une instance de la classe EmployeeDAO
// (est une classe héritière vu qu'il s'agit d'une interface)
// et la place dans le code PARTOUT là où il existe le Qualifier équivalent. Ici "choisirEmployeeDAO"

@Configuration
public class DAOConfig {

	@Autowired
	private EmployeeJPADAO employeeJPADAO;

	@Autowired
	private EmployeeMemoryDAO employeeMemoryDAO;

	@Value("#{confdao.typedao}")
	// nom de l'ID du fichier de properties recensé dans application-context.xml
	// Suivi de la clé qu'on veut dans le fichier de config
	// Sa valeur sera la valeur du String.
	private String confDAOStr;

	@Bean(name="choisirEmployeeDAO")
	// Grâce à ce Bean, la méthode est considérée comme un composant Spring.
	public EmployeeDAO getEmployeeDAO()	{
		// Verif la valeur du fichier de properties 
		// (cf application-context une balise évoque cela)
		if("jpa".equalsIgnoreCase(confDAOStr))	{
			return employeeJPADAO;
		} 
		return employeeMemoryDAO;
	}

}
