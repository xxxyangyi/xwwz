package com.hand.service.impl;

import com.hand.dao.INewsDao;
import com.hand.entity.News;
import com.hand.service.INewsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service("newsService")
public class NewsService implements INewsService {

    @Resource(name = "newsDao")
    private INewsDao newsDao;

    @Override
    public void createNews(News news) throws Exception {
        newsDao.Create(news);
    }

    @Override
    public void deleteNews(News News) throws Exception {
        newsDao.Delete(News);
    }

    @Override
    public void updateNews(News News) throws Exception {
        newsDao.Update(News);
    }

    @Override
    public List<News> FindBySQL(String sql) throws Exception {
       return newsDao.FindBySQL(sql);
    }

    @Override
    public News FindByID(int newsId) throws Exception {
        return newsDao.FindOne(newsId);
    }


}
