package fr.reactis.initiationjpa.dao;

import java.util.List;
import java.util.Map;

public interface GenericDAO<T, K> {

	// T aurait pu s'appeller Z. Norme : T
	T save(T entite);
	// Find by ID : on aurait pu mettre Integer. Mais non car pour ProjectCustomer la cl� n'est pas
	// un ID mais un objet PKProjectCustomer. (qui lui m�me repr�sente une table de jointure c�d 2 ID)
	// Donc on ne conna�t pas � l'avance. D'o� le K.
	T findById(K id);
	List<T> findAll();
	// On aurait pu faire un remove sur l'objet mais pas forc�ment rattach�
	// Il aurait fallu faire un find et remove pour passer l'objet en param.
	void remove(K id);
	// G�n�re une requ�te avec des AND en se servant des cl�s valeurs
	List<T> findByParameter(Map<String, Object> param);


}
