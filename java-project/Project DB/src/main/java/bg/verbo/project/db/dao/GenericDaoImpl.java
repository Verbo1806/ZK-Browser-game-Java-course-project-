package bg.verbo.project.db.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

public class GenericDaoImpl<T, ID extends Serializable> {
	public static final String _id = "id";
	
	@PersistenceContext
	private EntityManager entityManager;
	private Class<T> persistentClass;
	
	@SuppressWarnings("unchecked")
	protected GenericDaoImpl() {
		this.persistentClass =
			(Class<T>) ((ParameterizedType) getClass()
			.getGenericSuperclass())
			.getActualTypeArguments()[0];
	}

	public final T findById(ID id) {
		return entityManager.find(persistentClass, id);
	}

	public final List<T> findAll() {
		CriteriaBuilder cb = criteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(persistentClass);
		Root<T> root = cq.from(persistentClass);
		cq.select(root);
		
		return createQuery(cq).getResultList();
	}
	
	public T saveOrUpdate(T entity) {
		getSession().saveOrUpdate(entity);
		return entity;
	}
	
	public final EntityManager getEntityManager() {
		return entityManager;
	}
	
	public final CriteriaBuilder criteriaBuilder() {
		return entityManager.getCriteriaBuilder();
	}
	
	public final <U> TypedQuery<U> createQuery(CriteriaQuery<U> criteria) {
		return entityManager.createQuery(criteria);
	}
	
	private Session getSession() {
		return entityManager.unwrap(Session.class);
	}
}
