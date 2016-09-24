package com.hand.dao.impl;

import com.hand.dao.ICategoryDao;
import com.hand.dao.common.AbstractHibernateDao;
import com.hand.entity.Category;
import org.springframework.stereotype.Repository;

@Repository("categoryDao")
public class CategoryDao extends AbstractHibernateDao<Category> implements ICategoryDao {
	public CategoryDao() {
		super();
		setClazz(Category.class);
	}
}
