package com.hand.dao.common;

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
	
	List<T> FindList(String sql,Integer pre,Integer numPage);
    Integer GetSum(String sql);
    Integer GetTotal(Integer sum,Integer numPage);
    Integer GetPre(Integer page,Integer total, Integer numPage);
}
