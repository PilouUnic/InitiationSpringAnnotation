package fr.reactis.initiationjpa.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import fr.reactis.initiationjpa.dao.ProjectDAO;
import fr.reactis.initiationjpa.model.Project;
import fr.reactis.initiationjpa.model.QualityProject;

@Repository
public class ProjectJPADAO extends GenericJPADAO<Project, Integer> implements ProjectDAO {

	public ProjectJPADAO() {
		super(Project.class);
	}

	@Override
	public List<Project> findByDepartmentName(String name) {
		TypedQuery<Project> query = entityManager.createQuery("Select distinct p from Project p join p.employees e join e.department d Where d.nom = :name", Project.class);
		query.setParameter("name", name);
		return query.getResultList();
	}

	@Override
	public List<QualityProject> findAllQualityProject() {
		TypedQuery<QualityProject> query = entityManager.createQuery("Select p from QualityProject p", QualityProject.class);
		return query.getResultList();
	}

}
