package fr.reactis.initiationjpa.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.reactis.initiationjpa.dao.GenericDAO;
public class GenericJPADAO<T, K> implements GenericDAO<T, K> {

	@PersistenceContext
	// Sorte d'Autowired spécial EntityManager. C'est une annotation JPA.
	protected EntityManager entityManager;
	// Etait auparavant :
	//protected EntityManager entityManager = JPAUtils.getEntityManager();
	// On n'instancie plus. C'est Spring qui gère.
	private Class<T> type;

	public GenericJPADAO(Class<T> type)	{
		this.type = type;		
	}


	@Override
	public T save(T entite) {
		return entityManager.merge(entite);

	}



	@Override
	public T findById(K id) {
		return entityManager.find(type, id);
	}

	@Override
	public List<T> findAll() {
		TypedQuery<T> query = entityManager.createQuery("Select e From " + type.getName() + " e", type);
		return query.getResultList();
	}

	@Override
	public void remove(K id) {
		Query query = entityManager.createQuery("Delete From " + type.getName() + " e Where e.id = :id");
		query.setParameter("id", id);
		query.executeUpdate();

	}


	@Override
	public List<T> findByParameter(Map<String, Object> param) {
		// Create String QUERY
		StringBuilder queryBuilder = new StringBuilder();
		Set<String> keySet = param.keySet();
		for (String paramName : keySet) {
			queryBuilder.append(" e.").append(paramName).append("=:")
			.append(paramName).append(" AND ");
		}

		String query = queryBuilder.toString();

		query = query.substring(0, query.lastIndexOf("AND"));

		query = "Select e From " + type.getName() + " e Where " + query;

		TypedQuery<T> typedQuery = entityManager.createQuery(query, type);
		for (String paramName : keySet) {
			typedQuery.setParameter(paramName, param.get(paramName));
		}			


		return typedQuery.getResultList();


	}

}
