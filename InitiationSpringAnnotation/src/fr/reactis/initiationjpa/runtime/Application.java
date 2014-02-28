package fr.reactis.initiationjpa.runtime;

import java.text.ParseException;
import java.util.Date;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.reactis.initiationjpa.model.Employee;
import fr.reactis.initiationjpa.service.EmployeeService;

public class Application {

	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {

		// On fait là ce que fait classiquement Tomcat au démarrage
		// (se configure dans le web.xml du Tomcat)
		// Tomcat le fait même mieux car on peut mettre des * dans la chaîne de caractères.
		// Démarre Spring
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		
		// Injection
		EmployeeService employeeService = context.getBean(EmployeeService.class);
		
		Employee employee = new Employee(null, "Linus", "Torval", new Date());
		employeeService.save(employee);
	
		context.close();
	}
}
