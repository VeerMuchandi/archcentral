package com.pinaka.archcentral.dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.pinaka.archcentral.exception.BadInputDataException;

@Stateless
@Local(CrudService.class)
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class CrudServiceBean implements CrudService {

	@PersistenceContext
	EntityManager em;
	
	@Override
	public <T> T create(T t) throws BadInputDataException {
		if(t.equals(null)) throw new BadInputDataException("Input is null");
		this.em.persist(t);
		this.em.flush();
		this.em.refresh(t);
		return t;
	}

	@Override
	public <T> T find(Object id, Class<T> type) throws BadInputDataException {
		if (id.equals(0)||id.equals(null)) throw new BadInputDataException("Id is Null or 0");
		return (T) this.em.find(type, id);
	}

	@Override
	public <T> T update(T t) throws BadInputDataException {
//		return (T)this.em.merge(t);
		if(t.equals(null)) throw new BadInputDataException("Input is null");
		T updated = this.em.merge(t);
		this.em.flush();
		return updated;
	}

	@Override
	public <T> void delete(Object id, Class<T> type) throws BadInputDataException {
		Object ref = this.find(id, type);
//		Object ref = this.em.getReference(t.getClass(), t);
		this.em.remove(ref);

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Object> findByNamedQuery(String namedQueryName) {
		return this.em.createNamedQuery(namedQueryName).getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Object> findByNamedQuery(String queryName, int resultLimit) {
		return this.em.createNamedQuery(queryName).
				setMaxResults(resultLimit).
				getResultList();
	}

	@Override
	public List<Object> findByNamedQuery(String namedQueryName,
			Map<String, Object> parameters) {
		return findByNamedQuery(namedQueryName, parameters, 0);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Object> findByNamedQuery(String namedQueryName,
			Map<String, Object> parameters, int resultLimit) {
		
			Set<Entry<String, Object>> rawParameters = parameters.entrySet();
			Query query = this.em.createNamedQuery(namedQueryName);
			if(resultLimit > 0)
				query.setMaxResults(resultLimit);
			for (Entry<String, Object> entry : rawParameters) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
			return query.getResultList();
	}

}
