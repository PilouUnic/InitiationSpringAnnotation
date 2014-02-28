package fr.reactis.initiationjpa.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.JoinPoint.StaticPart;

import fr.reactis.initiationjpa.model.Employee;

public class OperationAspect {

	public void doBefore(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		// Il faut évidemment être sûr qu'on a un Employee pour faire ça
		Employee employee = (Employee) args[0];
		System.out.println("Aspect : " + employee.getFirstName());
		// On peut aussi set ...
		// TRES DANGEREUX car ça change l'objet à la volée dans les méthodes ...
		// Ne pas faire de bidouille avec dans une appli, peut vite aboutir à du gros désordre quasi impossible
		// à débugger

		System.out.println("Emplacement de la méthode : ");
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
