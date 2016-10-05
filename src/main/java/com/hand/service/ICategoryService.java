package com.hand.service;

import com.hand.entity.Category;
import org.hibernate.criterion.Criterion;

import java.util.List;

/**
 * Created by tuberose on 16-9-20.
 */
public interface ICategoryService {
    /*
    *	创建类别
     */
    public void createCategory(Category Category) throws Exception;

    /*
    *	删除类别
     */
    public void deleteCategory(Category Category) throws Exception;

    /*
    *	更新类别
     */
    public void updateCategory(Category Category) throws Exception;

    /*
    *	通过SQL 查找 类别 返回新闻
    */
    public List<Category> FindBySQL(String sql) throws Exception;

    /*
    *	通过ID 查找 类别 返回 类别
    */
    public Category  FindByID(int categoryId) throws Exception;

    public List<Category> findByCriteria(String[] JOIN,Criterion... criterions) throws Exception;
}
