package com.hand.service.impl;

import com.hand.dao.ICategoryDao;
import com.hand.entity.Category;
import com.hand.service.ICategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service("categoryService")
public class CategoryService implements ICategoryService {

    @Resource(name = "categoryDao")
    private ICategoryDao categoryDao;

    @Override
    public void createCategory(Category category) throws Exception {
        categoryDao.Create(category);
    }

    @Override
    public void deleteCategory(Category category) throws Exception {
        categoryDao.Delete(category);
    }

    @Override
    public void updateCategory(Category category) throws Exception {
        categoryDao.Update(category);
    }

    @Override
    public List<Category> FindBySQL(String sql) throws Exception {
       return categoryDao.FindBySQL(sql);
    }

    @Override
    public Category FindByID(int categoryId) throws Exception {
        return categoryDao.FindOne(categoryId);
    }


}
