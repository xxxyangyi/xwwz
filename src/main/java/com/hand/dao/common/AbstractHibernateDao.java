package com.hand.dao.common;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.hibernate.*;
import org.hibernate.criterion.Criterion;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;

public abstract class AbstractHibernateDao<T extends Serializable> implements
		IOperation<T> {
	private Class<T> clazz;
	//private final Logger log=Logger.getLogger(AbstractHibernateDao.class);
	
	
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	protected final Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	protected final void setClazz(final Class<T> clazzToSet) {
		this.clazz = clazzToSet;
	}
	

	public T FindOne(Object id) {
		return (T) getCurrentSession().get(clazz, (Serializable) id);
	}
	
	public List<T> FindAll() {
		return getCurrentSession().createQuery("from " + clazz.getName()).list();
	}
	
	public List<T> FindBySQL(String str){
		List<T> list = getCurrentSession().createSQLQuery(str).addEntity(clazz).list();
		return list;
	}

	public void Create(T model) {
		getCurrentSession().save(model);
	}

	public T Update(T model) {
		getCurrentSession().update(model);
		return model;
	}
	
	public T Merge(T model) {
		getCurrentSession().merge(model);
		return model;
	}

	public void Delete(T model) {
		getCurrentSession().delete(model);
	}

	public List<T> findByCriteria(String[] JOIN,Criterion... criterions) {
		try {
			Criteria criteria = this.getCurrentSession().createCriteria(clazz);
			if(JOIN!=null){
				for(String join:JOIN){
					criteria.setFetchMode(join, FetchMode.JOIN);
				}
			}
			if (criterions != null) {
				for (Criterion criterion : criterions) {
					if (criterion != null) {
						criteria.add(criterion);
					}
				}
			}
			return criteria.list();

		} catch (RuntimeException re) {
			throw re;
		}
	}
}
