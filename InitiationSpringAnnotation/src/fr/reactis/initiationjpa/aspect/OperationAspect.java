package fr.reactis.initiationjpa.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.JoinPoint.StaticPart;

import fr.reactis.initiationjpa.model.Employee;

public class OperationAspect {

	public void doBefore(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		// Il faut �videmment �tre s�r qu'on a un Employee pour faire �a
		Employee employee = (Employee) args[0];
		System.out.println("Aspect : " + employee.getFirstName());
		// On peut aussi set ...
		// TRES DANGEREUX car �a change l'objet � la vol�e dans les m�thodes ...
		// Ne pas faire de bidouille avec dans une appli, peut vite aboutir � du gros d�sordre quasi impossible
		// � d�bugger

		System.out.println("Emplacement de la m�thode : ");
		System.out.println(joinPoint.getSignature().getDeclaringTypeName());

	}

	public void doAfter(StaticPart staticPart, Object result) {
		System.out.println("Classe : " + staticPart.getSignature().getDeclaringTypeName());
		System.out.println("Point cut : " + staticPart.toShortString());
		if(result instanceof Employee)	{
			System.out.println("Employee " + ((Employee) result).getId() + " was saved !");

		}

	}


}
