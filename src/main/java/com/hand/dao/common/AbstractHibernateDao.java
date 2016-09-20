package com.hand.dao.common;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
	//  赵天龙 分页
	@Override
	public Integer GetSum(String sql) {
		Query query=getCurrentSession().createSQLQuery(sql).addScalar("sumkey",StandardBasicTypes.INTEGER).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		Map m=(Map)query.list().get(0);
		return (Integer) m.get("sumkey");
	}
	
	@Override
	public Integer GetTotal(Integer sum,Integer numPage) {
		if(sum==null)return 0;
		sum=sum%numPage==0?sum/numPage:sum/numPage+1;
		if(sum==0)sum=1;
		//this.total=total;
		return sum;
	}

	
	@Override
	public Integer GetPre(Integer page,Integer total, Integer numPage) {
		page=page<1?1:page;
		page=page>total?total:page;
		Integer pre=(page-1)*numPage;
		return pre;
	}

	public List<T> FindList(String sql,Integer pre,Integer numPage){
		Query query = getCurrentSession().createSQLQuery(sql).addEntity(clazz);
		query.setFirstResult(pre);
		query.setMaxResults(numPage);
		return query.list();
	}
	
	// 赵天龙分页结束
	
}
