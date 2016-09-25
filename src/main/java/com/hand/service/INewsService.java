package com.hand.service;

import com.hand.entity.News;
import com.hand.entity.User;

import java.util.List;

/**
 * Created by tuberose on 16-9-20.
 */
public interface INewsService {
    /*
    *	创建新闻
    */
    public void createNews(News news) throws Exception;

    /*
    *	删除新闻
    */
    public void deleteNews(News news) throws Exception;

    /*
    *	更新新闻
    */
    public void updateNews(News news) throws Exception;

    /*
    *	通过SQL 查找 新闻 返回新闻
    */
    public List<News> FindBySQL(String sql) throws Exception;

    /*
    *	通过 ID 查找 新闻 返回新闻实体
    */
    public News FindByID(int newsId) throws Exception;
}
