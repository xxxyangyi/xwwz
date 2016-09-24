package com.hand.actions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hand.entity.Category;
import com.hand.entity.News;
import com.hand.entity.User;
import com.hand.paging.Pager;
import com.hand.paging.PagingService;
import com.hand.service.ICategoryService;
import com.hand.service.INewsService;
import com.hand.service.IUserService;

import javax.annotation.Resource;
import java.io.PrintWriter;
import java.util.*;

import static com.hand.commonKey.CommonKey.PAGESIZE;

public class NewsAction extends BaseAction {

    @Resource(name = "newsService")
    private INewsService newsService;

    @Resource(name = "categoryService")
    private ICategoryService categoryService;

    @Resource(name = "pagingService")
    private PagingService<News> pagingNewsService;

    public void createNews() throws Exception {
        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println("---》createNews 方法");
        if(session.get("user")==null || ((User)session.get("user")).getIdentity() != 2){
            System.out.println("您还没有登陆，请先登陆--------ps: 只有记者才能创建新闻！ ");
            out.print(0);
            return;
        }
        News news = new News();
        String title = request.getParameter("title");
        String context = request.getParameter("context");
        String category_id = request.getParameter("category_id");
        if (title == null || context == null || category_id == null)
        {
            System.out.println("由于参数导致创建新闻失败");
            out.print(0);
            return;
        }
        news.setTitle(title);
        news.setContext(context);
        String[] category_ids =category_id.split("_");
        Set<Category> categorySet = new HashSet<>();
        for(String str : category_ids){
            Category category = categoryService.FindByID(Integer.parseInt(str));
            if(category==null){
                System.out.println("查无此类别");
                out.print("查无此类别");
                return;
            }
            categorySet.add(category);
        }

        news.setUser_id((User)session.get("user"));

        news.setCategory(categorySet);
        news.setCreateTime(new Date());
        try {
            newsService.createNews(news);
        } catch (Exception e) {
            System.out.println("深层调用导致创建失败");
            out.print(0);
            e.printStackTrace();
            return;
        }
        System.out.println("创建新闻成功");
        out.print(1);   // 创建成功
    }

}