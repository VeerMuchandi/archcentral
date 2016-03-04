package com.pinaka.archcentral.dao;

import java.util.List;
import java.util.Map;

import com.pinaka.archcentral.exception.BadInputDataException;

public interface CrudService {
		<T> T create(T t) throws BadInputDataException;
		<T> T find(Object id,Class<T> type) throws BadInputDataException;
		<T> T update(T t) throws BadInputDataException;
		<T> void delete(Object id, Class<T> type) throws BadInputDataException;
		@SuppressWarnings("rawtypes")
		List findByNamedQuery(String queryName);
		@SuppressWarnings("rawtypes")
		List findByNamedQuery(String queryName,int resultLimit);
		@SuppressWarnings("rawtypes")
		List findByNamedQuery(String namedQueryName, Map<String,Object> parameters);
		@SuppressWarnings("rawtypes")
		List findByNamedQuery(String namedQueryName, Map<String,Object> parameters,int resultLimit);
}
