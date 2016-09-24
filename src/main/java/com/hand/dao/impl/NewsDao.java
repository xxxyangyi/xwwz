package com.hand.dao.impl;

import com.hand.dao.INewsDao;
import com.hand.dao.common.AbstractHibernateDao;
import com.hand.entity.News;
import org.springframework.stereotype.Repository;

@Repository("newsDao")
public class NewsDao extends AbstractHibernateDao<News> implements INewsDao {
	public NewsDao() {
		super();
		setClazz(News.class);
	}
}
