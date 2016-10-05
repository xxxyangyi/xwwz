package com.hand.dao.common;

import org.hibernate.criterion.Criterion;

import java.io.Serializable;
import java.util.List;

public interface IOperation<T extends Serializable> {
	T FindOne(final Object id);
	List<T> FindAll();
	void Create(final T model);
	T Update(final T model);
	T Merge(final T model);
	List<T> FindBySQL(String str);
	void Delete(final T model);
	public List<T> findByCriteria(String[] JOIN,Criterion... criterions);
}
