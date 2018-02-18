package daosjpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


import org.springframework.transaction.annotation.*;

import clasesDao.GenericDao;

@EnableTransactionManagement
public class GenericDAOHibernateJPA<T> implements GenericDao<T> {
	
	EntityManager entityManager;

	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	public void setEntityManager(EntityManager em) {
		this.entityManager = em;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public T persistir(T entity) {
		//this.getEntityManager().persist(entity);
		this.getEntityManager().merge(entity);
		return entity;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public T persistirLibre(T entity) {
		this.getEntityManager().persist(entity);
		//this.getEntityManager().merge(entity);
		return entity;
	}

	protected Class<T> persistentClass;

	public GenericDAOHibernateJPA(Class<T> class1) {
		persistentClass = class1;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public T actualizar(T entity) {
		this.getEntityManager().merge(entity);
		return entity;
	}

	public List<T> traerCosas() {
		Query consulta = this.getEntityManager()
				.createQuery("select r from Usuario r");
		@SuppressWarnings("unchecked")
		List<T> resultado = (List<T>) consulta.getResultList();
		return resultado;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public void borrar(T entity) {
		try {
			this.getEntityManager().remove(entity);
		} catch (RuntimeException e) {
			throw e; // escribir en un log o mostrar un mensaje
		}

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public T borrar(Serializable id) {
		@SuppressWarnings("unchecked")
		T entity = this.getEntityManager().find((Class<T>) this.getPersistentClass(), id);
		if (entity != null) {
			this.borrar(entity);
		}
		return entity;
	}

	@SuppressWarnings("unchecked")
	private T getPersistentClass() {
		return (T) persistentClass;
	}

	@Override
	public boolean existe(Serializable id) {
		T entity= this.recuperar(id);
		return entity != null;
	}

	@Override
	public T recuperar(Serializable id) {
		@SuppressWarnings("unchecked")
		T entity = this.getEntityManager().find((Class<T>) this.getPersistentClass(), id);
		return entity;

	}


}