package com.hand.dao.impl;

import java.util.Map;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.hand.dao.IUserDao;
import com.hand.dao.common.AbstractHibernateDao;
import com.hand.entity.User;

@Repository("userDao")
public class UserDao extends AbstractHibernateDao<User> implements IUserDao {
	public UserDao() {
		super();
		setClazz(User.class);
	}
	public Integer getManSum(){
		String sql="select count(*) as sumkey from user where sex=1";
		Query query=getCurrentSession().createSQLQuery(sql).addScalar("sumkey",StandardBasicTypes.INTEGER).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		Map m=(Map)query.list().get(0);
		return (Integer) m.get("sumkey");
	}
	
	public Integer getWomenSum(){
		String sql="select count(*) as sumkey from user where sex=0";
		Query query=getCurrentSession().createSQLQuery(sql).addScalar("sumkey",StandardBasicTypes.INTEGER).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		Map m=(Map)query.list().get(0);
		return (Integer) m.get("sumkey");
	}
	
	public Integer getExpertSum(){
		String sql="select count(*) as sumkey from user where identity=2";
		Query query=getCurrentSession().createSQLQuery(sql).addScalar("sumkey",StandardBasicTypes.INTEGER).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		Map m=(Map)query.list().get(0);
		return (Integer) m.get("sumkey");
	}
	
	public Integer getUserSum(){
		String sql="select count(*) as sumkey from user where identity=1";
		Query query=getCurrentSession().createSQLQuery(sql).addScalar("sumkey",StandardBasicTypes.INTEGER).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		Map m=(Map)query.list().get(0);
		return (Integer) m.get("sumkey");
	}
	
}
